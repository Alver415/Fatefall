package com.alver.fatefall.fx.core.view;

import javafx.beans.property.Property;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class ColorSelectionEditor extends BaseEditor<Color> {

	private final ColorPicker colorPicker;

	ColorSelectionEditor(String name, Property<Color> property) {
		super(name);
		this.colorPicker = new ColorPicker();
		colorPicker.valueProperty().bindBidirectional(property);
		setNode(colorPicker);
	}
}
