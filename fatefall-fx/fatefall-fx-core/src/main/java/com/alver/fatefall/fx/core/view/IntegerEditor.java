package com.alver.fatefall.fx.core.view;

import com.sun.javafx.scene.control.IntegerField;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;

public class IntegerEditor extends EditorControl<Integer> {

	private final Property<Integer> property;

	IntegerEditor(String name, IntegerProperty property) {
		this(name, property.asObject());
	}

	IntegerEditor(String name, Property<Integer> property) {
		super(name);
		this.property = property;
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new IntegerFieldSkin(this);
	}

	private class IntegerFieldSkin extends SkinBase<IntegerEditor> {
		protected IntegerFieldSkin(IntegerEditor control) {
			super(control);
			IntegerField integerField = new IntegerField();
			integerField.valueProperty().asObject().bindBidirectional(property);
			getChildren().setAll(integerField);
		}
	}
}
