package com.alver.fatefall.app.fx.entity;

import com.alver.fatefall.data.entity.Card;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CardFX extends EntityFX implements Card<CardFaceFX> {

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
		return front.get();
	}

	public ObjectProperty<CardFaceFX> backProperty(){
		return back;
	}
	@Override
	public CardFaceFX getBack() {
		return back.get();
	}
}
