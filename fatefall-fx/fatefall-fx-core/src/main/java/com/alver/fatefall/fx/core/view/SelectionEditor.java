package com.alver.fatefall.fx.core.view;

import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;

public class SelectionEditor<T> extends EditorControl<T> {

	private final ObservableList<T> options;

	SelectionEditor(String name, Property<T> property, ObservableList<T> options) {
		super(name, property);
		this.options = options;
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new ComboBoxSkin();
	}

	private class ComboBoxSkin extends SkinBase<SelectionEditor<T>> {
		private ComboBoxSkin() {
			super(SelectionEditor.this);
			ComboBox<T> comboBox = new ComboBox<>(options);
			comboBox.valueProperty().bindBidirectional(getProperty());
			getChildren().setAll(comboBox);

		}
	}
}
