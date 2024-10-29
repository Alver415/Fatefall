package com.alver.fatefall.fx.core.view.editor;

import javafx.scene.Node;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.VBox;
import javafx.util.Subscription;

public abstract class PropertyEditorSkinBase<T> extends SkinBase<PropertyEditor<T>> {

	private final Subscription subscription;

	public PropertyEditorSkinBase(PropertyEditor<T> control) {
		super(control);

		subscription = control.editorsProperty().subscribe(editors -> {
			VBox vBox = new VBox();
			for (EditorControl<?> editor : editors) {
				Node node = buildEditor(editor);
				node.getStyleClass().add("editor-control");
				vBox.getChildren().add(node);
			}
			getChildren().setAll(vBox);
		});
	}

	protected abstract <T> Node buildEditor(EditorControl<T> editor);

	@Override
	public void dispose() {
		super.dispose();
		subscription.unsubscribe();
	}
}
