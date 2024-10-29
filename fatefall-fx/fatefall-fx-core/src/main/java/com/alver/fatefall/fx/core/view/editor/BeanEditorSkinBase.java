package com.alver.fatefall.fx.core.view.editor;

import javafx.scene.control.SkinBase;
import javafx.scene.layout.VBox;
import javafx.util.Subscription;

public abstract class BeanEditorSkinBase<B> extends SkinBase<BeanEditor<B>> {

	private final Subscription subscription;

	public BeanEditorSkinBase(BeanEditor<B> control) {
		super(control);

		subscription = control.editorsProperty().subscribe(editors -> {
			VBox vBox = new VBox();
			vBox.getChildren().setAll(editors);
			getChildren().setAll(vBox);
		});
	}

	@Override
	public void dispose() {
		super.dispose();
		subscription.unsubscribe();
	}
}
