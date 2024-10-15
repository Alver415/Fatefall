package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.core.model.CardFX;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanPropertyUtils;

import java.util.Collection;
import java.util.List;

public class DataEditorView extends PropertySheet {

	private final ObjectProperty<CardFX> card = new SimpleObjectProperty<>(this, "card");

	public ObjectProperty<CardFX> cardProperty() {
		return this.card;
	}

	public CardFX getCard() {
		return this.cardProperty().get();
	}

	public void setCard(CardFX value) {
		this.cardProperty().set(value);
	}

	public DataEditorView() {
		cardProperty().subscribe(card -> getItems().setAll(getPropertySheetItems(card)));
	}

	private Collection<? extends Item> getPropertySheetItems(CardFX card) {
		if (card == null) return List.of();
		return BeanPropertyUtils.getProperties(card);
	}
}
