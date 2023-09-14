package com.alver.fatefall.app.fx.model.entity;

import com.alver.fatefall.data.entity.CardFace;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CardFaceFX extends EntityFX implements CardFace<TemplateFX> {

	private final ObjectProperty<CardFX> cardProperty = new SimpleObjectProperty<>(this, "card");
	private final ObjectProperty<TemplateFX> templateProperty = new SimpleObjectProperty<>(this, "template", new TemplateFX());

	public CardFaceFX() {
		super();
	}
	public CardFaceFX(Long id) {
		super(id);
	}

	public ObjectProperty<CardFX> cardProperty(){
		return cardProperty;
	}
	@Override
	public CardFX getCard() {
		return cardProperty().get();
	}

	public void setCard(CardFX card) {
		cardProperty().set(card);
	}

	public ObjectProperty<TemplateFX> templateProperty(){
		return templateProperty;
	}

	@Override
	public TemplateFX getTemplate() {
		return templateProperty().get();
	}

	public void setTemplate(TemplateFX template) {
		templateProperty().set(template);
	}
}
