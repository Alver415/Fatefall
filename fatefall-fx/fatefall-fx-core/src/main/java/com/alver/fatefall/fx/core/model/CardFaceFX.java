package com.alver.fatefall.fx.core.model;

import com.alver.fatefall.core.entity.CardFace;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CardFaceFX extends EntityFX implements CardFace {

	private final SimpleObjectProperty<CardFX> cardProperty = new SimpleObjectProperty<>(this, "card");
	private final SimpleObjectProperty<TemplateFX> templateProperty = new SimpleObjectProperty<>(this, "template", new TemplateFX());

	public CardFaceFX() {
		super();
	}
	public CardFaceFX(Long id) {
		super(id);
	}

	public CardFaceFX(CardFX cardFX){
		super();
		cardProperty.set(cardFX);
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
