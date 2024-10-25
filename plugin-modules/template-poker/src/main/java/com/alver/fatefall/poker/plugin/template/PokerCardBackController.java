package com.alver.fatefall.poker.plugin.template;

import com.alver.fatefall.fx.app.view.entity.card.template.TemplateController;
import com.alver.fatefall.poker.plugin.model.PokerCard;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLPrototype
public class PokerCardBackController implements TemplateController<PokerCard.Back> {

	@Autowired
	public PokerCardBackController() {
		modelProperty().subscribe(face -> {
			if (face == null) return;
			imageProperty().bindBidirectional(face.imageProperty());
		});
	}

	private final ObjectProperty<PokerCard.Back> model = new SimpleObjectProperty<>(this, "model");

	@Override
	public Property<PokerCard.Back> modelProperty() {
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
