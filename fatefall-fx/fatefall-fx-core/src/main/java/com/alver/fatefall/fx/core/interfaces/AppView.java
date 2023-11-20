package com.alver.fatefall.fx.core.interfaces;

import javafx.scene.Node;

public interface AppView {

	String title();
	String description();
	Node node();

	static Simple of(String title, Node node){
		return of(title, null, node);
	}

	static Simple of(String title, String description, Node node){
		return new Simple(title, description, node);
	}

	record Simple(String title, String description, Node node) implements AppView{}
}
