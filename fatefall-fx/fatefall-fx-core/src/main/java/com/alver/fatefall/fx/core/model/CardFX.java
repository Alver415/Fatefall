package com.alver.fatefall.fx.core.model;

import com.alver.fatefall.core.entity.Card;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CardFX extends EntityFX implements Card{

	private final SimpleObjectProperty<CardFaceFX> front = new SimpleObjectProperty<>(this, "front", new CardFaceFX());
	private final SimpleObjectProperty<CardFaceFX> back = new SimpleObjectProperty<>(this, "back", new CardFaceFX());

	public CardFX() {
		super();
	}
	public CardFX(Long id) {
		super(id);
	}

	public ObjectProperty<CardFaceFX> frontProperty(){
		return front;
	}
	@Override
	public CardFaceFX getFront() {
		return frontProperty().get();
	}

	public ObjectProperty<CardFaceFX> backProperty(){
		return back;
	}
	@Override
	public CardFaceFX getBack() {
		return backProperty().get();
	}
}
