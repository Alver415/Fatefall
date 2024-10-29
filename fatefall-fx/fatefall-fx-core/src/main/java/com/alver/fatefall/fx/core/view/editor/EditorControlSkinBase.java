package com.alver.fatefall.fx.core.view.editor;

import javafx.scene.control.SkinBase;

import java.util.Objects;

public class EditorControlSkinBase<C extends EditorControl<T>, T> extends SkinBase<C> {
	private static final String STYLE_CLASS = "editor-control";
	private static final String STYLE_SHEET = Objects.requireNonNull(
			Editor.class.getResource("EditorControl.css")).toExternalForm();

	public EditorControlSkinBase(C control) {
		super(control);
		control.getStyleClass().add(STYLE_CLASS);
		control.getStylesheets().add(STYLE_SHEET);
	}
}
