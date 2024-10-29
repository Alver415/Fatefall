package com.alver.fatefall.fx.core.view.editor;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class IntrospectingPropertyEditor<T> extends PropertyEditor<T> {

	private static final Logger log = LoggerFactory.getLogger(IntrospectingPropertyEditor.class);

	public IntrospectingPropertyEditor() {
		this(null, new SimpleObjectProperty<>());
	}

	public IntrospectingPropertyEditor(String name, Property<T> property) {
		this(name, property, new PropertyIntrospector(), new EditorFactory());
	}

	public IntrospectingPropertyEditor(
			String name, Property<T> property, PropertyIntrospector introspector, EditorFactory factory) {
		super(name, property);

		setIntrospector(introspector);
		setEditorFactory(factory);

		ObservableValue<ObservableList<EditorControl<?>>> observable = propertyProperty()
				.flatMap(Function.identity())
				.map(this::buildEditors)
				.map(FXCollections::observableArrayList);
		editorsProperty().bind(observable.orElse(FXCollections.observableArrayList()));
	}

	private final ObjectProperty<PropertyIntrospector> introspector = new SimpleObjectProperty<>(this, "introspector");

	public ObjectProperty<PropertyIntrospector> introspectorProperty() {
		return this.introspector;
	}

	public PropertyIntrospector getIntrospector() {
		return this.introspectorProperty().get();
	}

	public void setIntrospector(PropertyIntrospector value) {
		this.introspectorProperty().set(value);
	}

	private final ObjectProperty<EditorFactory> editorFactory = new SimpleObjectProperty<>(this, "editorFactory");

	public ObjectProperty<EditorFactory> editorFactoryProperty() {
		return this.editorFactory;
	}

	public EditorFactory getEditorFactory() {
		return this.editorFactoryProperty().get();
	}

	public void setEditorFactory(EditorFactory value) {
		this.editorFactoryProperty().set(value);
	}


	@Override
	public ObservableList<EditorControl<?>> getEditors() {
		return super.getEditors();
	}

	private List<? extends EditorControl<?>> buildEditors(Object target) {
		return Stream.ofNullable(target)
				.map(Object::getClass)
				.map(getIntrospector()::getPropertyInfo)
				.flatMap(Collection::stream)
				.sorted(Comparator.comparingInt(PropertyInfo::order).thenComparing(PropertyInfo::displayName))
				.map(propertyInfo -> buildEditor(target, propertyInfo)).toList();
	}

	private EditorControl<Object> buildEditor(Object target, PropertyInfo propertyInfo) {
		return getEditorFactory().buildEditor(propertyInfo, invoke(target, propertyInfo.property()));
	}

	private static <T> T invoke(Object target, Method method) {
		try {
			//noinspection unchecked
			return (T) method.invoke(target);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
}
