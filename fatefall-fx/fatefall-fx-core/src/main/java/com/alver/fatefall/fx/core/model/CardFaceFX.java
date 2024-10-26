package com.alver.fatefall.fx.core.model;

import com.alver.fatefall.core.entity.CardFace;
import com.alver.fatefall.fx.core.view.EditorInfo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CardFaceFX<T extends TemplateFX> extends EntityFX implements CardFace {

	private final SimpleObjectProperty<CardFX<?,?>> cardProperty = new SimpleObjectProperty<>(this, "card");
	private final SimpleObjectProperty<T> templateProperty = new SimpleObjectProperty<>(this, "template");

	public CardFaceFX(CardFX<?,?> cardFX){
		cardProperty.set(cardFX);
	}

	@EditorInfo(ignore = true)
	public ObjectProperty<CardFX<?,?>> cardProperty(){
		return cardProperty;
	}
	@Override
	public CardFX<?,?> getCard() {
		return cardProperty().get();
	}

	public void setCard(CardFX<?,?> card) {
		cardProperty().set(card);
	}

	@EditorInfo(ignore = true)
	public ObjectProperty<T> templateProperty(){
		return templateProperty;
	}

	@Override
	public T getTemplate() {
		return templateProperty().get();
	}

	public void setTemplate(T template) {
		templateProperty().set(template);
	}
}
