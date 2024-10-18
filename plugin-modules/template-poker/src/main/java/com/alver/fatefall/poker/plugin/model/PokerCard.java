package com.alver.fatefall.poker.plugin.model;

import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class PokerCard extends CardFX<PokerCard.Front, PokerCard.Back> {

	public PokerCard(){
		frontProperty().set(new Front());
		backProperty().set(new Back());
	}

	public static class Front extends CardFaceFX {

		private final ObjectProperty<Rank> rank = new SimpleObjectProperty<>(this, "rank");

		public ObjectProperty<Rank> rankProperty() {
			return this.rank;
		}

		public Rank getRank() {
			return this.rankProperty().get();
		}

		public void setRank(Rank value) {
			this.rankProperty().set(value);
		}

		private final ObjectProperty<Suit> suit = new SimpleObjectProperty<>(this, "suit");

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

		private final ObjectProperty<Image> image = new SimpleObjectProperty<>(this, "image");

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
