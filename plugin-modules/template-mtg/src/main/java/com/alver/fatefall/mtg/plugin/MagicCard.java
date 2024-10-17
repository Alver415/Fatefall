package com.alver.fatefall.mtg.plugin;

import com.alver.fatefall.fx.core.model.CardFX;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MagicCard extends CardFX {

	private final StringProperty cardName = new SimpleStringProperty(this, "cardName");

	public StringProperty cardNameProperty() {
		return this.cardName;
	}

	public String getCardName() {
		return this.cardNameProperty().get();
	}

	public void setCardName(String value) {
		this.cardNameProperty().set(value);
	}
}
