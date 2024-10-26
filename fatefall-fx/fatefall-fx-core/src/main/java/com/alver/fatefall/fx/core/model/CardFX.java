package com.alver.fatefall.fx.core.model;

import com.alver.fatefall.core.entity.Card;
import com.alver.fatefall.fx.core.view.EditorInfo;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CardFX<F extends CardFaceFX<?>, B extends CardFaceFX<?>> extends EntityFX implements Card {

	private final ObjectProperty<F> front = new SimpleObjectProperty<>(this, "front");

	@EditorInfo(displayName = "Front Face", order = Integer.MIN_VALUE)
	public ObjectProperty<F> frontProperty() {
		return this.front;
	}

	public F getFront() {
		return this.frontProperty().get();
	}

	public void setFront(F value) {
		this.frontProperty().set(value);
	}

	private final ObjectProperty<B> back = new SimpleObjectProperty<>(this, "back");

	@EditorInfo(displayName = "Back Face", order = Integer.MIN_VALUE + 1)
	public ObjectProperty<B> backProperty() {
		return this.back;
	}

	public B getBack() {
		return this.backProperty().get();
	}

	public void setBack(B value) {
		this.backProperty().set(value);
	}


	private final DoubleProperty width = new SimpleDoubleProperty(this, "width", 250);

	public DoubleProperty widthProperty() {
		return this.width;
	}

	public Double getWidth() {
		return this.widthProperty().get();
	}

	public void setWidth(Double value) {
		this.widthProperty().set(value);
	}

	private final DoubleProperty height = new SimpleDoubleProperty(this, "height", 350);

	public DoubleProperty heightProperty() {
		return this.height;
	}

	public Double getHeight() {
		return this.heightProperty().get();
	}

	public void setHeight(Double value) {
		this.heightProperty().set(value);
	}

	private final DoubleProperty arcWidth = new SimpleDoubleProperty(this, "arcWidth", 20);

	public DoubleProperty arcWidthProperty() {
		return this.arcWidth;
	}

	public Double getArcWidth() {
		return this.arcWidthProperty().get();
	}

	public void setArcWidth(Double value) {
		this.arcWidthProperty().set(value);
	}

	private final DoubleProperty arcHeight = new SimpleDoubleProperty(this, "arcHeight", 20);

	public DoubleProperty arcHeightProperty() {
		return this.arcHeight;
	}

	public Double getArcHeight() {
		return this.arcHeightProperty().get();
	}

	public void setArcHeight(Double value) {
		this.arcHeightProperty().set(value);
	}

}
