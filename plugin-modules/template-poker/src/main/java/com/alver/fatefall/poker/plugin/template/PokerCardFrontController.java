package com.alver.fatefall.poker.plugin.template;

import com.alver.fatefall.fx.app.view.entity.card.template.TemplateController;
import com.alver.fatefall.poker.plugin.model.PokerCard;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

@FXMLPrototype
public class PokerCardFrontController implements TemplateController<PokerCard.Front> {

	private final ObjectProperty<PokerCard.Front> model = new SimpleObjectProperty<>(this, "model");

	@Override
	public Property<PokerCard.Front> modelProperty() {
		return model;
	}

	@Override
	public PokerCard.Front getModel() {
		return TemplateController.super.getModel();
	}

	@Override
	public void setModel(PokerCard.Front value) {
		TemplateController.super.setModel(value);
	}
}
