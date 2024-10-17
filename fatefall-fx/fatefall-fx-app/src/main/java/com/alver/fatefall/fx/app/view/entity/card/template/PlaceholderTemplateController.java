package com.alver.fatefall.fx.app.view.entity.card.template;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLPrototype(location = "PlaceholderTemplate.fxml")
public class PlaceholderTemplateController extends TemplateControllerBase<CardFaceFX> {

	@Autowired
	public PlaceholderTemplateController(FatefallProperties properties) {
		super(properties);
	}

	private final ObjectProperty<CardFaceFX> model = new SimpleObjectProperty<>(this, "model");
	@Override
	public Property<CardFaceFX> modelProperty() {
		return model;
	}
}
