package com.alver.fatefall.fx.core.view;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.ToggleButton;

public class BooleanEditor extends BaseEditor<String> {

	private final ToggleButton toggleButton;

	BooleanEditor(String name, BooleanProperty property) {
		super(name);
		this.toggleButton = new ToggleButton();
		toggleButton.textProperty().bind(toggleButton.selectedProperty().map(selected -> name + ": " + selected));
		toggleButton.selectedProperty().bindBidirectional(property);
		setNode(toggleButton);
	}

}
