package com.alver.fatefall.fx.app.component.cardinfo;

import com.alver.fatefall.fx.app.view.entity.card.CardView;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanPropertyUtils;

@FXMLPrototype
public class CardInfo  {

	@FXML
	protected CardView cardView;
	@FXML
	protected PropertySheet propertySheet;

	/**
	 * === Selected Property ==
	 */
	protected ObjectProperty<CardFX<?,?>> cardProperty = new SimpleObjectProperty<>();

	public final void setCard(CardFX<?,?> value) {
		cardProperty.set(value);
	}

	public final CardFX<?,?> getCard() {
		return cardProperty.get();
	}

	public final ObjectProperty<CardFX<?,?>> cardProperty() {
		return cardProperty;
	}

	@FXML
	public void initialize() {
		cardView.cardProperty().bind(cardProperty);
		cardProperty.addListener((observable, oldValue, newValue) -> {
			propertySheet.getItems().clear();
			if (newValue != null) {
				propertySheet.getItems().setAll(BeanPropertyUtils.getProperties(newValue));
			}
		});
	}
}
