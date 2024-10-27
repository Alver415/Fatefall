package com.alver.fatefall.mtg.plugin;

import com.alver.fatefall.fx.app.view.entity.card.template.FXMLTemplate;
import com.alver.fatefall.fx.app.view.entity.card.template.ImageTemplate;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.fatefall.fx.core.view.EditorInfo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.Objects;

public class MagicCard<F extends CardFaceFX<?>, B extends CardFaceFX<?>> extends CardFX<F, B> {

	public MagicCard() {
		setFront((F) new Front());
		setBack((B) new StandardMTGBack());
	}


	private final StringProperty cardName = new SimpleStringProperty(this, "cardName");

	@EditorInfo(displayName = "Card Name", order = -1)
	public StringProperty cardNameProperty() {
		return this.cardName;
	}

	public String getCardName() {
		return this.cardNameProperty().get();
	}

	public void setCardName(String value) {
		this.cardNameProperty().set(value);
	}

	public class Front extends CardFaceFX<FXMLTemplate> {
		public static final URL FXML =
				MagicCard.class.getResource("template/MagicCard.fxml");

		public Front() {
			super(MagicCard.this);
			setTemplate(new FXMLTemplate(FXML));
		}

		private final StringProperty name = new SimpleStringProperty(this, "name");

		public StringProperty nameProperty() {
			return this.name;
		}

		public String getName() {
			return this.nameProperty().get();
		}

		public void setName(String value) {
			this.nameProperty().set(value);
		}
	}

	public class Back extends CardFaceFX<FXMLTemplate> {
		public Back() {
			super(MagicCard.this);
		}
	}

	public class StandardMTGBack extends CardFaceFX<ImageTemplate> {
		public static final Image MAGIC_CARD_BACK = new Image(Objects.requireNonNull(
				MagicCard.class.getResourceAsStream("template/images/magic_card_back.png")));

		public StandardMTGBack() {
			super(MagicCard.this);
			setTemplate(new ImageTemplate(MAGIC_CARD_BACK));
		}

		private final ObjectProperty<Image> image = new SimpleObjectProperty<>(
				this, "image", MAGIC_CARD_BACK);

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
