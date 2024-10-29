package com.alver.fatefall.fx.core.view.editor;

import javafx.scene.Node;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.VBox;
import javafx.util.Subscription;

public abstract class BeanEditorSkinBase<B> extends SkinBase<BeanEditor<B>> {

	private final Subscription subscription;

	public BeanEditorSkinBase(BeanEditor<B> control) {
		super(control);

		subscription = control.editorControlsProperty().subscribe(editors -> {
			VBox vBox = new VBox();
			for (EditorControl<?> editor : editors) {
				Node node = new Editor<>(editor.nameProperty(), editor);
				vBox.getChildren().add(node);
			}
			getChildren().setAll(vBox);
		});
	}

	@Override
	public void dispose() {
		super.dispose();
		subscription.unsubscribe();
	}
}
