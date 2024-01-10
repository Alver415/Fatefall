package com.alver.fatefall.fx.app.view.entity.card.template;

import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

@FXMLPrototype
public class ImageTemplate extends BaseTemplate {

	@FXML
	protected final ObjectProperty<Image> image = new SimpleObjectProperty<>(this, "image");
	public ObjectProperty<Image> imageProperty() {
		return image;
	}
	public Image getImage() {
		return image.get();
	}
	public void setImage(Image image) {
		this.image.set(image);
	}
}
