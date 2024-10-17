package com.alver.fatefall.fx.core.view;

import com.alver.fsfx.util.Converter;
import javafx.beans.property.Property;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class ImageSelectionEditor extends BaseEditor<Image> {

	private final TextField urlTextField;

	ImageSelectionEditor(String name, Property<Image> property) {
		super(name);
		this.urlTextField = new TextField();

		Converter<String, Image> convert = Converter.of(Image::new, Image::getUrl);
		convert.bindBidirectional(urlTextField.textProperty(), property);
		setNode(urlTextField);
	}
}
