package com.alver.fatefall.app.fx.components.cardcollection;

import com.alver.fatefall.api.Card;
import com.alver.fatefall.api.CardCollection;
import com.alver.fatefall.app.fx.components.FxComponent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

public class CardCollectionPane extends CardGridPane implements FxComponent {

	public CardCollectionPane() {
		this(CardGridPane.class);
	}
	protected CardCollectionPane(Class<?> clazz) {
		initFxml(clazz);
		cardCollectionProperty.addListener((observable, oldValue, newValue) -> {
			redraw(newValue.getCards());
		});
	}

	protected ObjectProperty<CardCollection> cardCollectionProperty = new SimpleObjectProperty<>();
	public final ObjectProperty<CardCollection> cardCollectionProperty() {
		return cardCollectionProperty;
	}
	public final CardCollection getCardCollection() {
		return cardCollectionProperty.get();
	}
	public final void setCardCollection(CardCollection value) {
		cardCollectionProperty.set(value);
	}
	public ObservableList<Card> getCards() {
		return FXCollections.observableList(getCardCollection().getCards());
	}

	@Override
	public void search(String query) {
		runFx(() -> redraw(getCards().filtered(c -> c.getName().matches(query))));
	}
}