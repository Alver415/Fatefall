package com.alver.fatefall.app.fx.components.cardinfo;

import com.alver.fatefall.app.fx.components.FxComponent;
import com.alver.fatefall.plugin.interfaces.CardView;
import com.alver.fatefall.plugin.models.Card;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanPropertyUtils;

public class CardInfo extends BorderPane implements FxComponent {

	@FXML
	protected CardView cardView;
	@FXML
	protected PropertySheet propertySheet;

	/**
	 * === Selected Property ==
	 */
	protected ObjectProperty<Card> cardProperty = new SimpleObjectProperty<>();

	public final void setCard(Card value) {
		cardProperty.set(value);
	}

	public final Card getCard() {
		return cardProperty.get();
	}

	public final ObjectProperty<Card> cardProperty() {
		return cardProperty;
	}

	public CardInfo() {
		initFxml();
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
