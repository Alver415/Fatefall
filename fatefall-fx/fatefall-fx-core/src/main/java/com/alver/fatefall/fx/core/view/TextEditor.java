package com.alver.fatefall.fx.core.view;

import javafx.beans.property.Property;
import javafx.scene.control.TextField;

public class TextEditor extends BaseEditor<String> {

	private final TextField textField;

	TextEditor(String name, Property<String> property) {
		super(name);
		this.textField = new TextField();
		textField.textProperty().bindBidirectional(property);
		setNode(textField);
	}

}
