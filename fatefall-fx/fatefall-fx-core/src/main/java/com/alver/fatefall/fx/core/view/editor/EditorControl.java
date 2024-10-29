package com.alver.fatefall.fx.core.view.editor;

import javafx.beans.property.*;
import javafx.scene.control.Control;

import java.util.Objects;

public abstract class EditorControl<T> extends Control {

	private static final String STYLE_CLASS = "editor-control";
	private static final String STYLE_SHEET = Objects.requireNonNull(
			Editor.class.getResource("EditorControl.css")).toExternalForm();

	private EditorControl(Property<T> property) {
		getStyleClass().add(STYLE_CLASS);
		getStylesheets().add(STYLE_SHEET);
		setProperty(property);
	}

	public EditorControl(String name, Property<T> property) {
		this(property);
		setName(name);
	}

	public EditorControl(Property<String> name, Property<T> property) {
		this(property);
		nameProperty().bind(name);
	}

	private final ObjectProperty<Property<T>> property = new SimpleObjectProperty<>(this, "property");
	public ObjectProperty<Property<T>> propertyProperty(){
	    return this.property;
	}
	public Property<T> getProperty(){
	    return this.propertyProperty().get();
	}
	public void setProperty(Property<T> value){
	    this.propertyProperty().set(value);
	}
	private final StringProperty name = new SimpleStringProperty(this, "name");

	public StringProperty nameProperty() {
		return this.name;
	}

	public String getName() {
		return this.nameProperty().get();
	}

	public void setName(String value) {
		this.nameProperty().set(value);
	}

}