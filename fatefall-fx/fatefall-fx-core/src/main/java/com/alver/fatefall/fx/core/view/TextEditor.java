package com.alver.fatefall.fx.core.view;

import javafx.beans.property.Property;
import javafx.scene.control.*;

public class TextEditor extends EditorControl<String> {

	private final Property<String> property;

	TextEditor(String name, Property<String> property) {
		super(name);
		this.property = property;
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new TextFieldSkin(this);
	}

	private class TextInputControlSkin extends SkinBase<TextEditor> {
		protected TextInputControlSkin(TextEditor control, TextInputControl inputControl) {
			super(control);
			inputControl.textProperty().bindBidirectional(property);
			getChildren().setAll(inputControl);
		}
	}

	private class TextFieldSkin extends TextInputControlSkin {
		protected TextFieldSkin(TextEditor control) {
			super(control, new TextField());
		}
	}

	private class TextAreaSkin extends TextInputControlSkin {
		protected TextAreaSkin(TextEditor control) {
			super(control, new TextArea());
		}
	}
}
