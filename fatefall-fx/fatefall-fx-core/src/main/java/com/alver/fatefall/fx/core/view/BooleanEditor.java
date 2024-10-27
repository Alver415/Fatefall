package com.alver.fatefall.fx.core.view;

import javafx.beans.property.Property;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.control.ToggleButton;
import org.controlsfx.control.ToggleSwitch;

public class BooleanEditor extends EditorControl<Boolean> {

	public BooleanEditor(String name, Property<Boolean> property) {
		super(name, property);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new ToggleButtonSkin();
	}

	private class ToggleButtonSkin extends SkinBase<BooleanEditor> {
		private ToggleButtonSkin() {
			super(BooleanEditor.this);
			ToggleButton toggleButton = new ToggleButton();
			toggleButton.textProperty().bind(toggleButton.selectedProperty().map(b -> b ? "Enabled" : "Disabled"));
			toggleButton.selectedProperty().bindBidirectional(getProperty());
			getChildren().setAll(toggleButton);
		}
	}
	private class ToggleSwitchSkin extends SkinBase<BooleanEditor> {
		private ToggleSwitchSkin() {
			super(BooleanEditor.this);
			ToggleSwitch toggleSwitch = new ToggleSwitch();
			toggleSwitch.textProperty().bind(toggleSwitch.selectedProperty().map(b -> b ? "Enabled" : "Disabled"));
			toggleSwitch.selectedProperty().bindBidirectional(getProperty());
			getChildren().setAll(toggleSwitch);
		}
	}


}
