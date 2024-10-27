package com.alver.fatefall.fx.core.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Control;

public abstract class EditorControl<T> extends Control implements Editor<T> {

	public EditorControl(String name){
		setName(name);
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

	@Override
	public ObjectProperty<Node> nodeProperty() {
		return new SimpleObjectProperty<>(this);
	}

}