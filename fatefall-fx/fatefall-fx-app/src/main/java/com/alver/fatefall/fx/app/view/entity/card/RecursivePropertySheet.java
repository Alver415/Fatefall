package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.app.editor.components.image.ImageSelectionEditor;
import javafx.application.Platform;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanProperty;
import org.controlsfx.property.editor.AbstractPropertyEditor;
import org.controlsfx.property.editor.DefaultPropertyEditorFactory;
import org.controlsfx.property.editor.PropertyEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.controlsfx.property.BeanProperty.CATEGORY_LABEL_KEY;

public class RecursivePropertySheet<T> extends PropertySheet implements PropertyEditor<T> {

	private static final Logger log = LoggerFactory.getLogger(RecursivePropertySheet.class);

	private final ObjectProperty<T> value = new SimpleObjectProperty<>(this, "value");

	public ObjectProperty<T> valueProperty() {
		return this.value;
	}

	@Override
	public T getValue() {
		return valueProperty().get();
	}

	@Override
	public void setValue(T value) {
		valueProperty().set(value);
	}

	@Override
	public Node getEditor() {
		return this;
	}

	public RecursivePropertySheet() {
		this(null);
	}

	public RecursivePropertySheet(T value) {
		setPropertyEditorFactory(new EditorFactory());
		setValue(value);
		valueProperty().subscribe(v -> getItems().setAll(buildProperties(v)));
	}

	private ObservableList<Item> buildProperties(Object bean) {
		ObservableList<Item> list = FXCollections.observableArrayList();
		if (bean == null) return list;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
			for (PropertyDescriptor p : beanInfo.getPropertyDescriptors()) {
				Method writeMethod = p.getWriteMethod();
				Method readMethod = p.getReadMethod();
				if (writeMethod != null && readMethod != null) {
					p.setValue(CATEGORY_LABEL_KEY, writeMethod.getDeclaringClass().toString());
				} else {
					p.setValue(CATEGORY_LABEL_KEY, "Not Editable");
				}
				list.add(new BeanProperty(bean, p));
			}
		} catch (IntrospectionException e) {
			log.error(e.getMessage(), e);
		}

		return list;
	}


	private static class EditorFactory extends DefaultPropertyEditorFactory {

		private static final Class<?>[] numericTypes = new Class[]{
				byte.class, Byte.class,
				short.class, Short.class,
				int.class, Integer.class,
				long.class, Long.class,
				float.class, Float.class,
				double.class, Double.class,
				BigInteger.class, BigDecimal.class
		};

		// there should be better ways to do this
		private static boolean isNumber(Class<?> type) {
			if (type == null) return false;
			for (Class<?> cls : numericTypes) {
				if (type == cls) return true;
			}
			return false;
		}

		@Override
		public PropertyEditor<?> call(Item item) {
			Class<?> type = item.getType();


			if (/*type != null &&*/ isNumber(type)) {
				return createNumericEditor(item);
			}
			PropertyEditor<?> editor = super.call(item);
			if (type == Image.class) {
				editor = new ImageSelectionEditor(item);
			}
			if (editor == null && i++ < 5) {
				editor = new RecursivePropertySheet<>(item.getValue());
			}
			return editor;
		}
	}

	static int i = 0;
	@SuppressWarnings("unchecked")
	public static PropertyEditor<?> createNumericEditor(Item property) {

		return new AbstractPropertyEditor<Number, NumericField>(
				property, new NumericField((Class<? extends Number>) property.getType())) {

			private Class<? extends Number> sourceClass = (Class<? extends Number>) property.getType();

			{enableAutoSelectAll(getEditor());}

			@Override
			protected ObservableValue<Number> getObservableValue() {
				return getEditor().valueProperty();
			}

			@Override
			public Number getValue() {
				if (sourceClass == Byte.class) {
					return Byte.valueOf(getEditor().getText());
				} else if (sourceClass == Short.class) {
					return Short.valueOf(getEditor().getText());
				} else if (sourceClass == Integer.class) {
					return Integer.valueOf(getEditor().getText());
				} else if (sourceClass == Long.class) {
					return Long.valueOf(getEditor().getText());
				} else if (sourceClass == Float.class) {
					return Float.valueOf(getEditor().getText());
				} else if (sourceClass == Double.class) {
					return Double.valueOf(getEditor().getText());
				} else if (sourceClass == BigInteger.class) {
					return new BigInteger(getEditor().getText());
				} else if (sourceClass == BigDecimal.class) {
					return new BigDecimal(getEditor().getText());
				}

				try {
					return sourceClass.getConstructor(String.class).newInstance(getEditor().getText());
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
						 InvocationTargetException
						 | NoSuchMethodException | SecurityException e) {
					log.warn(e.getMessage(), e);
					return null;
				}
			}

			@Override
			public void setValue(Number value) {
				if (value != null) sourceClass = value.getClass();
				getEditor().setValue(value);
				getEditor().setText(String.valueOf(value));
			}

		};
	}


	static class NumericField extends TextField {

		private final NumericValidator<? extends Number> value;

		public NumericField(Class<? extends Number> cls) {

			if (cls == byte.class || cls == Byte.class || cls == short.class || cls == Short.class ||
					cls == int.class || cls == Integer.class || cls == long.class || cls == Long.class ||
					cls == BigInteger.class) {
				value = new LongValidator(this);
			} else {
				value = new DoubleValidator(this);
			}

			focusedProperty().addListener((observable, oldValue, newValue) -> {
				if (!newValue) {
					value.setValue(value.toNumber(getText()));
				}
			});
		}

		public final ObservableValue<Number> valueProperty() {
			return value;
		}

		@Override
		public void replaceText(int start, int end, String text) {
			if (replaceValid(start, end, text)) {
				super.replaceText(start, end, text);
			}
		}

		@Override
		public void replaceSelection(String text) {
			IndexRange range = getSelection();
			if (replaceValid(range.getStart(), range.getEnd(), text)) {
				super.replaceSelection(text);
			}
		}

		/**
		 * Sets the value of the numeric value property.
		 *
		 * @param value The new value to assign.
		 */
		protected void setValue(Number value) {
			this.value.setValue(value);
		}

		private Boolean replaceValid(int start, int end, String fragment) {
			try {
				String newText = getText().substring(0, start) + fragment + getText().substring(end);
				if (newText.isEmpty()) return true;
				value.toNumber(newText);
				return true;
			} catch (Throwable ex) {
				return false;
			}
		}


		private interface NumericValidator<T extends Number> extends NumberExpression {
			void setValue(Number num);

			T toNumber(String s);

		}

		static class DoubleValidator extends SimpleDoubleProperty implements NumericField.NumericValidator<Double> {

			private final NumericField field;

			public DoubleValidator(NumericField field) {
				super(field, "value", 0.0);
				this.field = field;
			}

			@Override
			protected void invalidated() {
				field.setText(Double.toString(get()));
			}

			@Override
			public Double toNumber(String s) {
				if (s == null || s.trim().isEmpty()) return 0d;
				String d = s.trim();
				if (d.endsWith("f") || d.endsWith("d") || d.endsWith("F") || d.endsWith(
						"D")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					throw new NumberFormatException("There should be no alpha symbols"); //$NON-NLS-1$
				}
				return Double.parseDouble(d);
			}

			;

		}


		static class LongValidator extends SimpleLongProperty implements NumericField.NumericValidator<Long> {

			private final NumericField field;

			public LongValidator(NumericField field) {
				super(field, "value", 0L);
				this.field = field;
			}

			@Override
			protected void invalidated() {
				field.setText(Long.toString(get()));
			}

			@Override
			public Long toNumber(String s) {
				if (s == null || s.trim().isEmpty()) return 0L;
				String d = s.trim();
				return Long.valueOf(d);
			}

		}


	}
	private static void enableAutoSelectAll(final TextInputControl control) {
		control.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldValue, Boolean newValue) -> {
			if (newValue) {
				Platform.runLater(control::selectAll);
			}
		});
	}
}
