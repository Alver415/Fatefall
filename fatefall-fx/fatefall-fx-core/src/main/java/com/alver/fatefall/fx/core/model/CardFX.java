package com.alver.fatefall.fx.core.model;

import com.alver.fatefall.core.entity.Card;
import javafx.beans.property.*;

public class CardFX extends EntityFX implements Card {

	protected final SimpleObjectProperty<CardFaceFX> front = new SimpleObjectProperty<>(this, "front", new CardFaceFX());
	protected final SimpleObjectProperty<CardFaceFX> back = new SimpleObjectProperty<>(this, "back", new CardFaceFX());
	protected final DoubleProperty width = new SimpleDoubleProperty(this, "width", 250);
	protected final DoubleProperty height = new SimpleDoubleProperty(this, "height", 350);

	public CardFX() {
		this(null);
	}
	public CardFX(Long id) {
		super(id);
	}

	public ReadOnlyObjectProperty<CardFaceFX> frontProperty() {
		return front;
	}
	@Override
	public CardFaceFX getFront() {
		return front.get();
	}

	public ReadOnlyObjectProperty<CardFaceFX> backProperty() {
		return back;
	}
	@Override
	public CardFaceFX getBack() {
		return back.get();
	}

	public DoubleProperty widthProperty() {
		return width;
	}
	public double getWidth() {
		return width.get();
	}
	public void setWidth(double width) {
		this.width.set(width);
	}

	public DoubleProperty heightProperty() {
		return height;
	}
	public double getHeight() {
		return height.get();
	}
	public void setHeight(double height) {
		this.height.set(height);
	}
}
