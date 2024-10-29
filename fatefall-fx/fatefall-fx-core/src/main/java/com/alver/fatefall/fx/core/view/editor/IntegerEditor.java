package com.alver.fatefall.fx.core.view.editor;

import com.sun.javafx.scene.control.IntegerField;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;

public class IntegerEditor extends EditorControl<Integer> {

	public IntegerEditor(String name, IntegerProperty property) {
		this(name, property.asObject());
	}

	public IntegerEditor(String name, Property<Integer> property) {
		super(name, property);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new IntegerFieldSkin(this);
	}

	private class IntegerFieldSkin extends SkinBase<IntegerEditor> {
		protected IntegerFieldSkin(IntegerEditor control) {
			super(control);
			IntegerField integerField = new IntegerField();
			integerField.valueProperty().asObject().bindBidirectional(getProperty());
			getChildren().setAll(integerField);
		}
	}
}
