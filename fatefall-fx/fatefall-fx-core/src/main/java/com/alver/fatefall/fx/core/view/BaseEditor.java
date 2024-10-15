package com.alver.fatefall.fx.core.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

public abstract class BaseEditor<T> implements Editor<T>{

	public BaseEditor(String name) {
		setName(name);
	}

	private final StringProperty name = new SimpleStringProperty(this, "name");
	@Override
	public StringProperty nameProperty(){
		return name;
	}


	private final ObjectProperty<Node> node = new SimpleObjectProperty<>(this, "node");
	@Override
	public ObjectProperty<Node> nodeProperty(){
		return node;
	}

}