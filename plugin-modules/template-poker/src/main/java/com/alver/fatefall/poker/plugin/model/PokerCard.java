package com.alver.fatefall.poker.plugin.model;

import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.fatefall.poker.plugin.PokerCardLoader;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class PokerCard extends CardFX<PokerCard.Front, PokerCard.Back> {

	public PokerCard(){
		setFront(new Front());
		setBack(new Back());
	}

	public static class Front extends CardFaceFX {

		private Front(){
			getTemplate().setFxmlUrl(PokerCardLoader.FRONT_FXML);
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
	public static class Back extends CardFaceFX {

		private Back(){
			getTemplate().setFxmlUrl(PokerCardLoader.BACK_FXML);
		}
		private final ObjectProperty<Image> image = new SimpleObjectProperty<>(this, "image", PokerCardLoader.BACK_IMAGE);

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
