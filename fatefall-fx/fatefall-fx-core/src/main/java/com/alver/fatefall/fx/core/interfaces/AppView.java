package com.alver.fatefall.fx.core.interfaces;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

public interface AppView {

	ObservableValue<String> title();
	ObservableValue<String> description();
	Node node();

	static Simple of(String title, Node node) {
		return of(new SimpleStringProperty(title), null, node);
	}

	static Simple of(String title, String description, Node node) {
		return of(new SimpleStringProperty(title), new SimpleStringProperty(description), node);
	}

	static Simple of(ObservableValue<String> title, Node node) {
		return of(title, null, node);
	}

	static Simple of(ObservableValue<String> title, ObservableValue<String> description, Node node) {
		return new Simple(title, description, node);
	}

	record Simple(ObservableValue<String> title, ObservableValue<String> description, Node node) implements AppView {
	}
}
