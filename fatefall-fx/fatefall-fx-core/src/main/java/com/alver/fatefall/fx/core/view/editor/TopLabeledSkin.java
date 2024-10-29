package com.alver.fatefall.fx.core.view.editor;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TopLabeledSkin<T> extends PropertyEditorSkinBase<T> {

	public TopLabeledSkin(PropertyEditor<T> control) {
		super(control);
	}

	@Override
	protected <T> Node buildEditor(EditorControl<T> editor) {
		Label label = new Label();
		label.textProperty().bind(editor.nameProperty());
		HBox hBox = new HBox(editor);
		HBox.setHgrow(editor, Priority.ALWAYS);
		return new VBox(label, hBox);
	}
}
