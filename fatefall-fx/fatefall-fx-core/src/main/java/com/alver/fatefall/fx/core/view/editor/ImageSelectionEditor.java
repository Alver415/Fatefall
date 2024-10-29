package com.alver.fatefall.fx.core.view.editor;

import com.alver.fsfx.util.Converter;
import javafx.beans.property.Property;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class ImageSelectionEditor extends EditorControl<Image> {

	public ImageSelectionEditor(Property<Image> property) {
		super(property);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new ImageSelectionSkin();
	}

	private class ImageSelectionSkin extends SkinBase<ImageSelectionEditor> {

		protected ImageSelectionSkin() {
			super(ImageSelectionEditor.this);
			TextField urlTextField = new TextField();

			Converter<String, Image> convert = Converter.of(this::toImage, this::fromUrl);
			convert.bindBidirectional(urlTextField.textProperty(), getProperty());
			getChildren().setAll(urlTextField);
		}

		private Image toImage(String url) {
			try {
				return new Image(url, true);
			} catch (Exception e) {
				return null;
			}
		}

		private String fromUrl(Image image) {
			return image == null ? null : image.getUrl();
		}
	}
}
