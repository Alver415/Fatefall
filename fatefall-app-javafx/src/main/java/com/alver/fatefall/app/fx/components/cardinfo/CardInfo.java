package com.alver.fatefall.app.fx.components.cardinfo;

import com.alver.fatefall.api.Card;
import com.alver.fatefall.app.fx.components.FxComponent;
import com.alver.fatefall.app.fx.components.cardview.CardFace;
import com.alver.fatefall.app.fx.components.cardview.CardPane;
import com.alver.fatefall.app.fx.components.cardview.CardView;
import com.alver.fatefall.app.fx.components.cardview.ImageBlock;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanPropertyUtils;

public class CardInfo extends BorderPane implements FxComponent {

	@FXML
	protected CardView cardEditorView;
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
		cardEditorView.cardProperty().bind(cardProperty);
		cardProperty.addListener((observable, oldValue, newValue) -> {
			propertySheet.getItems().clear();
			if (newValue != null) {
				propertySheet.getItems().setAll(BeanPropertyUtils.getProperties(newValue));
			}
		});
	}
}
