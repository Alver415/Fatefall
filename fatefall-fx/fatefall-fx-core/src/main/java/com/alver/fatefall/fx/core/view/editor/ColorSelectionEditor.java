package com.alver.fatefall.fx.core.view.editor;

import javafx.beans.property.Property;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.paint.Color;

public class ColorSelectionEditor extends EditorControl<Color> {

	public ColorSelectionEditor(String name, Property<Color> property) {
		super(name, property);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new ColorPickerSkin();
	}

	private class ColorPickerSkin extends SkinBase<ColorSelectionEditor> {

		private ColorPickerSkin() {
			super(ColorSelectionEditor.this);
			ColorPicker colorPicker = new ColorPicker();
			colorPicker.valueProperty().bindBidirectional(getProperty());
			getChildren().setAll(colorPicker);
		}
	}
}
