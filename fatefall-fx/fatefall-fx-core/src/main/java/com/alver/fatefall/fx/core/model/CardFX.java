package com.alver.fatefall.fx.core.model;

import com.alver.fatefall.core.entity.Card;
import com.alver.fatefall.fx.core.view.EditorInfo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CardFX<Front extends CardFaceFX, Back extends CardFaceFX> extends EntityFX implements Card {

	public CardFX() {
		super();
	}
	public CardFX(Long id) {
		super(id);
	}

	private final ObjectProperty<Front> front = new SimpleObjectProperty<>(this, "front");

	@EditorInfo(displayName = "Front Face", order = Integer.MIN_VALUE)
	public ObjectProperty<Front> frontProperty() {
		return this.front;
	}

	public Front getFront() {
		return this.frontProperty().get();
	}

	public void setFront(Front value) {
		this.frontProperty().set(value);
	}

	private final ObjectProperty<Back> back = new SimpleObjectProperty<>(this, "back");

	@EditorInfo(displayName = "Back Face", order = Integer.MIN_VALUE + 1)
	public ObjectProperty<Back> backProperty() {
		return this.back;
	}

	public Back getBack() {
		return this.backProperty().get();
	}

	public void setBack(Back value) {
		this.backProperty().set(value);
	}
}
