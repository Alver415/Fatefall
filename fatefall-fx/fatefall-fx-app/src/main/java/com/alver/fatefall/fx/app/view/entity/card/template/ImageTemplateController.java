package com.alver.fatefall.fx.app.view.entity.card.template;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLPrototype(location = "ImageTemplate.fxml")
public class ImageTemplateController extends TemplateControllerBase<CardFaceFX<ImageTemplate>> {
	@Autowired
	public ImageTemplateController(FatefallProperties properties) {
		super(properties);

		modelProperty().subscribe(face -> {
			if (face == null) return;
			imageProperty().bind(face.getTemplate().imageProperty());
		});
	}

	private final ObjectProperty<CardFaceFX<ImageTemplate>> model = new SimpleObjectProperty<>(this, "model");

	@Override
	public Property<CardFaceFX<ImageTemplate>> modelProperty() {
		return model;
	}

	private final ObjectProperty<Image> image = new SimpleObjectProperty<>(this, "image");

	public ObjectProperty<Image> imageProperty() {
		return this.image;
	}

	public Image getImage() {
		return this.imageProperty().get();
	}

	public void setImage(Image value) {
		this.imageProperty().set(value);
	}
}
