package com.alver.fatefall.fx.core.view.editor;

import javafx.beans.property.Property;
import javafx.scene.control.*;

public class TextEditor extends EditorControl<String> {

	public TextEditor(Property<String> property) {
		super(property);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new TextFieldSkin(this);
	}

	private class TextInputControlSkin extends SkinBase<TextEditor> {
		protected TextInputControlSkin(TextEditor control, TextInputControl inputControl) {
			super(control);
			inputControl.textProperty().bindBidirectional(getProperty());
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
