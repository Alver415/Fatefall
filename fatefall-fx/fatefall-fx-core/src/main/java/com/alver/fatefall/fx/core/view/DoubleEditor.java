package com.alver.fatefall.fx.core.view;

import com.sun.javafx.scene.control.DoubleField;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.control.Slider;

public class DoubleEditor extends EditorControl<Double> {

	private final Property<Double> property;

	DoubleEditor(String name, DoubleProperty property) {
		this(name, property.asObject());
	}

	DoubleEditor(String name, Property<Double> property) {
		super(name);
		this.property = property;
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new DoubleFieldSkin(this);
	}


	private class DoubleFieldSkin extends SkinBase<DoubleEditor> {
		protected DoubleFieldSkin(DoubleEditor control) {
			super(control);
			DoubleField doubleField = new DoubleField();
			doubleField.valueProperty().asObject().bindBidirectional(property);
			getChildren().setAll(doubleField);
		}
	}

	private class SliderSkin extends SkinBase<DoubleEditor> {
		protected SliderSkin(DoubleEditor control) {
			super(control);
			Slider slider = new Slider();
			slider.valueProperty().asObject().bindBidirectional(property);
			getChildren().setAll(slider);
		}
	}
}
