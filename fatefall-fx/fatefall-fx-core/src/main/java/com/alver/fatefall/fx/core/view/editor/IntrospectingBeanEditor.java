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

public class IntrospectingBeanEditor<T> extends BeanEditor<T> {

	private static final Logger log = LoggerFactory.getLogger(IntrospectingBeanEditor.class);

	public IntrospectingBeanEditor() {
		this(null, new SimpleObjectProperty<>());
	}

	public IntrospectingBeanEditor(String name, Property<T> property) {
		this(name, property, new PropertyIntrospector(), new EditorControlFactory());
	}

	public IntrospectingBeanEditor(
			String name, Property<T> property, PropertyIntrospector introspector, EditorControlFactory factory) {
		super(name, property);

		setIntrospector(introspector);
		setEditorControlFactory(factory);

		ObservableValue<ObservableList<EditorControl<?>>> observable = propertyProperty()
				.flatMap(Function.identity())
				.map(this::buildEditorControls)
				.map(FXCollections::observableArrayList);
		editorControlsProperty().bind(observable.orElse(FXCollections.observableArrayList()));
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

	private final ObjectProperty<EditorControlFactory> editorControlFactory = new SimpleObjectProperty<>(this, "editorControlFactory");

	public ObjectProperty<EditorControlFactory> editorControlFactoryProperty() {
		return this.editorControlFactory;
	}

	public EditorControlFactory getEditorControlFactory() {
		return this.editorControlFactoryProperty().get();
	}

	public void setEditorControlFactory(EditorControlFactory value) {
		this.editorControlFactoryProperty().set(value);
	}

	private List<? extends EditorControl<?>> buildEditorControls(Object target) {
		return Stream.ofNullable(target)
				.map(Object::getClass)
				.map(getIntrospector()::getPropertyInfo)
				.flatMap(Collection::stream)
				.sorted(Comparator.comparingInt(PropertyInfo::order).thenComparing(PropertyInfo::displayName))
				.map(propertyInfo -> buildEditorControl(target, propertyInfo)).toList();
	}

	private EditorControl<Object> buildEditorControl(Object target, PropertyInfo propertyInfo) {
		return getEditorControlFactory().buildEditorControl(propertyInfo, invoke(target, propertyInfo.property()));
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
