package com.alver.fatefall.fx.core.view;

import com.alver.fatefall.fx.core.model.Source;
import com.alver.fatefall.fx.core.utils.TreeProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

public class IntrospectingPropertyEditor<T> extends PropertyEditor<T>{

	private static final Logger log = LoggerFactory.getLogger(IntrospectingPropertyEditor.class);

	public IntrospectingPropertyEditor(){
		this(null, null);
	}

	public IntrospectingPropertyEditor(String name, Property<T> property){
		this(name, property, new PropertyIntrospector(), new EditorFactory());
	}

	public IntrospectingPropertyEditor(String name, Property<T> property, PropertyIntrospector introspector, EditorFactory factory){
		super(name, property);
		setIntrospector(introspector);
		setEditorFactory(factory);

		if (property instanceof TreeProperty<T> treeProperty){
			ObservableList<Editor<?>> editors = FXCollections.observableArrayList();

			for (Map.Entry<String, TreeProperty<Object>> child : treeProperty.getChildrenMap().entrySet()) {
				String key = child.getKey();
				TreeProperty<Object> value = child.getValue();
				Source source = value.sourceProperty().get();
				Optional<Property<Object>> property1 = value.getProperty(source);
				if (property1.isPresent()) {
					Property<Object> objectProperty = property1.get();
					editors.add(new IntrospectingPropertyEditor<>(key, objectProperty));
				}
			}

			setEditors(editors);
		} else {
			valueProperty().subscribe((value) -> {
				if (value == null) return;
				setEditors(FXCollections.observableArrayList(buildEditors(value)));
			});
		}
	}

	private final ObjectProperty<PropertyIntrospector> introspector = new SimpleObjectProperty<>(this, "introspector");
	public ObjectProperty<PropertyIntrospector> introspectorProperty(){
	    return this.introspector;
	}
	public PropertyIntrospector getIntrospector(){
	    return this.introspectorProperty().get();
	}
	public void setIntrospector(PropertyIntrospector value){
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
	public ObservableList<Editor<?>> getEditors() {
		return super.getEditors();
	}

	public List<? extends Editor<?>> buildEditors(Object target) {
		return Stream.ofNullable(target)
				.map(Object::getClass)
				.map(getIntrospector()::getPropertyInfo)
				.flatMap(Collection::stream)
				.sorted(Comparator.comparingInt(PropertyInfo::order))
				.map(propertyInfo -> {
					Method propertyMethod = propertyInfo.property();
					try {
						Property<?> property = (Property<?>) propertyMethod.invoke(target);
						return getEditorFactory().buildEditor(propertyInfo, property);
					} catch (Exception e) {
						log.error(e.getMessage(), e);
						return getEditorFactory().buildEditor(propertyInfo, null);
					}
				})
				.toList();
	}
}
