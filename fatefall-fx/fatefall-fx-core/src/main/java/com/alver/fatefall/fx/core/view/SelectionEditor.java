package com.alver.fatefall.fx.core.view;

import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class SelectionEditor<T> extends BaseEditor<T> {

	private final ComboBox<T> comboBox;

	SelectionEditor(String name, Property<T> property, ObservableList<T> options) {
		super(name);
		this.comboBox = new ComboBox<>(options);
		comboBox.valueProperty().bindBidirectional(property);
		setNode(comboBox);
	}
}
