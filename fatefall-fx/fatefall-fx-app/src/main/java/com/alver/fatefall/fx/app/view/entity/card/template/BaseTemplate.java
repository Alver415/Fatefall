package com.alver.fatefall.fx.app.view.entity.card.template;

import com.alver.fatefall.fx.core.model.CardFX;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

public class BaseTemplate {


	protected final ObjectProperty<CardFX> card = new SimpleObjectProperty<>(this, "card");
	public ObjectProperty<CardFX> cardProperty() {
		return card;
	}
	public CardFX getCard() {
		return cardProperty().get();
	}
	public void setCard(CardFX card) {
		cardProperty().set(card);
	}


	protected DoubleProperty width = new SimpleDoubleProperty(this, "width", 250);
	public DoubleProperty widthProperty() {
		return width;
	}
	public double getWidth() {
		return widthProperty().get();
	}
	public void setWidth(double widthProperty) {
		widthProperty().set(widthProperty);
	}


	protected DoubleProperty height = new SimpleDoubleProperty(this, "height", 350);
	public DoubleProperty heightProperty() {
		return height;
	}
	public double getHeight() {
		return heightProperty().get();
	}
	public void setHeight(double heightProperty) {
		heightProperty().set(heightProperty);
	}


	protected DoubleProperty arcWidth = new SimpleDoubleProperty(this, "arcWidth", 25);
	public DoubleProperty arcWidthProperty() {
		return arcWidth;
	}
	public double getArcWidth() {
		return arcWidthProperty().get();
	}
	public void setArcWidth(double arcWidthProperty) {
		arcWidthProperty().set(arcWidthProperty);
	}


	protected DoubleProperty arcHeight = new SimpleDoubleProperty(this, "arcHeight", 25);
	public DoubleProperty arcHeightProperty() {
		return arcHeight;
	}
	public double getArcHeight() {
		return arcHeightProperty().get();
	}
	public void setArcHeight(double arcHeightProperty) {
		arcHeightProperty().set(arcHeightProperty);
	}

}
