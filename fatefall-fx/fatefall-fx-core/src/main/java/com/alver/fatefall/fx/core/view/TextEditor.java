package com.alver.fatefall.fx.core.view;

import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

public class TextEditor extends BaseEditor<String> {

	private final TextField textField;

	TextEditor(String name, StringProperty property) {
		super(name);
		this.textField = new TextField();
		textField.textProperty().bindBidirectional(property);
		setNode(textField);
	}

}
