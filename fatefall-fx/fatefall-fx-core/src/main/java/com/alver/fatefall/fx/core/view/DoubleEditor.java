package com.alver.fatefall.fx.core.view;

import com.sun.javafx.scene.control.DoubleField;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.control.Slider;

public class DoubleEditor extends EditorControl<Double> {


	public DoubleEditor(String name, DoubleProperty property) {
		this(name, property.asObject());
	}

	public DoubleEditor(String name, Property<Double> property) {
		super(name, property);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new DoubleFieldSkin();
	}


	private class DoubleFieldSkin extends SkinBase<DoubleEditor> {
		protected DoubleFieldSkin() {
			super(DoubleEditor.this);
			DoubleField doubleField = new DoubleField();
			doubleField.valueProperty().asObject().bindBidirectional(getProperty());
			getChildren().setAll(doubleField);
		}
	}

	private class SliderSkin extends SkinBase<DoubleEditor> {
		protected SliderSkin() {
			super(DoubleEditor.this);
			Slider slider = new Slider();
			slider.valueProperty().asObject().bindBidirectional(getProperty());
			getChildren().setAll(slider);
		}
	}
}
