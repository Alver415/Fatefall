package com.alver.fatefall.fx.core.view.editor;

import javafx.beans.property.Property;
import javafx.scene.control.Skin;
import javafx.scene.control.ToggleButton;
import org.controlsfx.control.ToggleSwitch;

public class BooleanEditor extends EditorControl<Boolean> {

	public BooleanEditor(Property<Boolean> property) {
		super(property);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new ToggleSwitchSkin(this);
	}

	public static abstract class BooleanEditorSkinBase extends EditorControlSkinBase<BooleanEditor, Boolean> {
		public BooleanEditorSkinBase(BooleanEditor control) {
			super(control);
		}
	}
	public static class ToggleButtonSkin extends BooleanEditorSkinBase {
		private ToggleButtonSkin(BooleanEditor control) {
			super(control);
			ToggleButton toggleButton = new ToggleButton();
			toggleButton.textProperty().bind(toggleButton.selectedProperty().map(b -> b ? "Enabled" : "Disabled"));
			toggleButton.selectedProperty().bindBidirectional(control.getProperty());
			getChildren().setAll(toggleButton);
		}
	}
	public static class ToggleSwitchSkin extends EditorControlSkinBase<BooleanEditor, Boolean> {
		private ToggleSwitchSkin(BooleanEditor control) {
			super(control);
			ToggleSwitch toggleSwitch = new ToggleSwitch();
			toggleSwitch.textProperty().bind(toggleSwitch.selectedProperty().map(b -> b ? "Enabled" : "Disabled"));
			toggleSwitch.selectedProperty().bindBidirectional(control.getProperty());
			getChildren().setAll(toggleSwitch);
		}
	}


}
