package com.alver.fatefall.fx.core.view.editor;

import javafx.beans.property.*;
import javafx.scene.control.Control;

public abstract class EditorControl<T> extends Control {

	private EditorControl(Property<T> property) {
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

	public ObjectProperty<Property<T>> propertyProperty() {
		return this.property;
	}

	public Property<T> getProperty() {
		return this.propertyProperty().get();
	}

	public void setProperty(Property<T> value) {
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