package com.alver.fatefall.fx.core.view;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyEditor<T> extends Control implements Editor<T>{

	private static final Logger log = LoggerFactory.getLogger(PropertyEditor.class);

	public PropertyEditor(String name, Property<T> property){
		setName(name);
		if (property != null) {
			valueProperty().bind(property);
		}
		setNode(this);
		getStylesheets().add("com/alver/fatefall/fx/core/view/PropertyEditor.css");
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new PropertyEditorSkin(this);
	}

	private final ObjectProperty<T> value = new SimpleObjectProperty<>(this, "value");

	public ObjectProperty<T> valueProperty() {
		return this.value;
	}

	public T getValue() {
		return this.valueProperty().get();
	}

	public void setValue(T value) {
		this.valueProperty().set(value);
	}

	private final ListProperty<Editor<?>> editors = new SimpleListProperty<>(this, "editors", FXCollections.observableArrayList());
	public ListProperty<Editor<?>> editorsProperty(){
	    return this.editors;
	}
	public ObservableList<Editor<?>> getEditors(){
	    return this.editorsProperty().get();
	}
	public void setEditors(ObservableList<Editor<?>> value){
	    this.editorsProperty().set(value);
	}

	private final StringProperty name = new SimpleStringProperty(this, "name");
	@Override
	public StringProperty nameProperty(){
	    return this.name;
	}

	private final ObjectProperty<Node> node = new SimpleObjectProperty<>(this, "node");
	@Override
	public ObjectProperty<Node> nodeProperty(){
	    return this.node;
	}

}
