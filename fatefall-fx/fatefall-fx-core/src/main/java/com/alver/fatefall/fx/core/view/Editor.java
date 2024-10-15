package com.alver.fatefall.fx.core.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

public interface Editor<T> {

	StringProperty nameProperty();
	default String getName(){
		return nameProperty().get();
	}
	default void setName(String name){
		nameProperty().set(name);
	}

	ObjectProperty<Node> nodeProperty();
	default Node getNode(){
		return nodeProperty().get();
	}
	default void setNode(Node node){
		nodeProperty().set(node);
	}

}