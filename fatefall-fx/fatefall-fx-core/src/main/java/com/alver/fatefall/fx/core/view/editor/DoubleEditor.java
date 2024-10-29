package com.alver.fatefall.fx.core.view.editor;

import com.sun.javafx.scene.control.DoubleField;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.control.Slider;

public class DoubleEditor extends EditorControl<Double> {


	public DoubleEditor(DoubleProperty property) {
		this(property.asObject());
	}

	public DoubleEditor(Property<Double> property) {
		super(property);
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
