package com.alver.fatefall.fx.core.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.VBox;

public class PropertyEditorSkin extends SkinBase<PropertyEditor<?>> {

	protected PropertyEditorSkin(PropertyEditor<?> control) {
		super(control);

		VBox vbox = new VBox();
		ScrollPane scrollPane = new ScrollPane(vbox);
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);

		for (Editor<?> editor : control.getEditors()) {
			Label name = new Label(editor.getName());
			Node node = editor.getNode();
			node.getStyleClass().add("editor");
			vbox.getChildren().add(new VBox(name, node));
		}

		getChildren().setAll(scrollPane);
	}

}
