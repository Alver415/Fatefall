package com.alver.fatefall.poker.plugin.model;

import com.alver.fatefall.fx.app.view.entity.card.template.FXMLTemplate;
import com.alver.fatefall.fx.app.view.entity.card.template.ImageTemplate;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.fatefall.poker.plugin.template.PokerCardBackController;
import com.alver.fatefall.poker.plugin.template.PokerCardFrontController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.Objects;

public class PokerCard extends CardFX<PokerCard.Front, PokerCard.Back> {

	public static final URL FRONT_FXML = Objects.requireNonNull(
			PokerCardFrontController.class.getResource("front.fxml"));

	public static final Image BACK_IMAGE = new Image(Objects.requireNonNull(
			PokerCardBackController.class.getResource("back.png")).toExternalForm());

	public PokerCard() {
		setFront(new Front());
		setBack(new Back());
	}

	public class Front extends CardFaceFX<FXMLTemplate> {

		private Front() {
			setCard(PokerCard.this);
			FXMLTemplate template = new FXMLTemplate();
			template.setFxml(FRONT_FXML);
			setTemplate(template);
		}

		private final ObjectProperty<Rank> rank = new SimpleObjectProperty<>(this, "rank", Rank.ACE);

		public ObjectProperty<Rank> rankProperty() {
			return this.rank;
		}

		public Rank getRank() {
			return this.rankProperty().get();
		}

		public void setRank(Rank value) {
			this.rankProperty().set(value);
		}

		private final ObjectProperty<Suit> suit = new SimpleObjectProperty<>(this, "suit", Suit.SPADE);

		public ObjectProperty<Suit> suitProperty() {
			return this.suit;
		}

		public Suit getSuit() {
			return this.suitProperty().get();
		}

		public void setSuit(Suit value) {
			this.suitProperty().set(value);
		}

	}

	public class Back extends CardFaceFX<ImageTemplate> {

		private Back() {
			setCard(PokerCard.this);
			ImageTemplate template = new ImageTemplate(BACK_IMAGE);
			setTemplate(template);
		}

		private final ObjectProperty<Image> image = new SimpleObjectProperty<>(
				this, "image", BACK_IMAGE);

		public ObjectProperty<Image> imageProperty() {
			return this.image;
		}

		public Image getImage() {
			return this.imageProperty().get();
		}

		public void setImage(Image value) {
			this.imageProperty().set(value);
		}

	}
}
