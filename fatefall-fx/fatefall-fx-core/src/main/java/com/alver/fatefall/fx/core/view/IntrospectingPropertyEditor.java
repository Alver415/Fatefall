package com.alver.fatefall.fx.core.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class IntrospectingPropertyEditor<T> extends PropertyEditor<T>{

	public IntrospectingPropertyEditor(){
		this(null, null);
	}

	public IntrospectingPropertyEditor(String name, Property<T> property){
		this(name, property, new PropertyIntrospector());
	}

	public IntrospectingPropertyEditor(String name, Property<T> property, PropertyIntrospector introspector){
		super(name, property);
		setIntrospector(introspector);
		valueProperty().subscribe((value) -> {
			if (value == null) return;
			List<Editor<?>> list = (List<Editor<?>>) getIntrospector().buildEditors(value);
			ObservableList<Editor<?>> editors = FXCollections.observableArrayList(list);
			setEditors(editors);
		});
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

	@Override
	public ObservableList<Editor<?>> getEditors() {
		return super.getEditors();
	}
}
