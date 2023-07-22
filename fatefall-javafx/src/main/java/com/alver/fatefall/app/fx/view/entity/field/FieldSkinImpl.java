package com.alver.fatefall.app.fx.view.entity.field;

import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FieldSkinImpl extends SkinBase<FieldControl> {

	protected final HBox root;
	protected final TextField nameField;
	protected final TextField valueField;

	protected FieldSkinImpl(FieldControl control) {
		super(control);

		this.nameField = new TextField();
		this.nameField.setPromptText("name");

		this.valueField = new TextField();
		this.valueField.setPromptText("value");

		control.attributeProperty.addListener((observable, oldValue, newValue) -> {
			this.nameField.textProperty().set(newValue == null ? null : newValue.getName());
			this.valueField.textProperty().set(newValue == null ? null : newValue.getValue());
		});

		this.root = new HBox(nameField, valueField);

		getChildren().add(root);

	}
}
