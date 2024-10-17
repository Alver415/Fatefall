package com.alver.fatefall.poker.plugin.template;

import com.alver.fatefall.fx.app.view.entity.card.template.TemplateController;
import com.alver.fatefall.poker.plugin.PokerCard;
import com.alver.fsfx.util.Converter;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.Parent;

@FXMLPrototype
public class PokerCardFrontController implements TemplateController<PokerCard.Front> {

	@FXML
	private Parent root;

	public PokerCardFrontController() {
		Converter<String, Rank> rankConverter = Converter.of(Rank::fromSymbol, Rank::getSymbol);
		Converter<String, Suit> suitConverter = Converter.of(Suit::fromSymbol, Suit::getSymbol);
		modelProperty().subscribe(face -> {
			if (face == null) return;
			rankConverter.bindBidirectional(this.rankProperty(), face.rankProperty());
			suitConverter.bindBidirectional(this.suitProperty(), face.suitProperty());

			face.suitProperty().subscribe(suit -> {
				if (root != null && suit != null){
					root.getChildrenUnmodifiable().forEach(child -> {
						child.setStyle("-fx-suit-color: " + suit.getColor());
					});
				}
			});
		});
	}

	private final ObjectProperty<PokerCard> card = new SimpleObjectProperty<>(this, "card");

	public ObjectProperty<PokerCard> cardProperty() {
		return this.card;
	}

	public PokerCard getCard() {
		return this.cardProperty().get();
	}

	public void setCard(PokerCard value) {
		this.cardProperty().set(value);
	}

	private final StringProperty rank = new SimpleStringProperty(this, "rank");

	public StringProperty rankProperty() {
		return this.rank;
	}

	public String getRank() {
		return this.rankProperty().get();
	}

	public void setRank(String value) {
		this.rankProperty().set(value);
	}

	private final StringProperty suit = new SimpleStringProperty(this, "suit");

	public StringProperty suitProperty() {
		return this.suit;
	}

	public String getSuit() {
		return this.suitProperty().get();
	}

	public void setSuit(String value) {
		this.suitProperty().set(value);
	}

	private final ObjectProperty<PokerCard.Front> model = new SimpleObjectProperty<>(this, "model");

	@Override
	public Property<PokerCard.Front> modelProperty() {
		return model;
	}
}
