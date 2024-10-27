package com.alver.fatefall.mtg.plugin;

import com.alver.fatefall.fx.app.view.entity.card.template.FXMLTemplate;
import com.alver.fatefall.fx.app.view.entity.card.template.ImageTemplate;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.fatefall.fx.core.view.EditorInfo;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;

public class MagicCard<F extends CardFaceFX<?>, B extends CardFaceFX<?>> extends CardFX<F, B> {

	private static final Logger log = LoggerFactory.getLogger(MagicCard.class);

	public MagicCard() {
		setFront((F) new Front());
		setBack((B) new StandardMTGBack());
	}

	private final StringProperty cardName = new SimpleStringProperty(this, "cardName");

	@EditorInfo(displayName = "Card Name", order = 1)
	public StringProperty cardNameProperty() {
		return this.cardName;
	}

	public String getCardName() {
		return this.cardNameProperty().get();
	}

	public void setCardName(String value) {
		this.cardNameProperty().set(value);
	}

	private final StringProperty developer = new SimpleStringProperty(this, "developer");

	@EditorInfo(displayName = "Developer Credit", order = 100)
	public StringProperty developerProperty(){
	    return this.developer;
	}
	public String getDeveloper(){
	    return this.developerProperty().get();
	}
	public void setDeveloper(String value){
	    this.developerProperty().set(value);
	}

	public class Front extends CardFaceFX<FXMLTemplate> {
		public static final URL FXML =
				MagicCard.class.getResource("template/MagicCard.fxml");

		public Front() {
			super(MagicCard.this);
			setTemplate(new FXMLTemplate(FXML));
		}

		private final StringProperty name = new SimpleStringProperty(this, "name");
		@EditorInfo(displayName = "Name", order = 3)
		public StringProperty nameProperty() {
			return this.name;
		}

		public String getName() {
			return this.nameProperty().get();
		}

		public void setName(String value) {
			this.nameProperty().set(value);
		}
		private final StringProperty type = new SimpleStringProperty(this, "type");
		@EditorInfo(displayName = "Type", order = 3)
		public StringProperty typeProperty(){
		    return this.type;
		}
		public String getType(){
		    return this.typeProperty().get();
		}
		public void setType(String value){
		    this.typeProperty().set(value);
		}
		private final ObjectProperty<Image> art = new SimpleObjectProperty<>(this, "art");
		@EditorInfo(displayName = "Artwork", order = 2)
		public ObjectProperty<Image> artProperty(){
			return this.art;
		}
		public Image getArt(){
			return this.artProperty().get();
		}
		public void setArt(Image value){
			this.artProperty().set(value);
		}

		private final ObjectProperty<File> file = new SimpleObjectProperty<>(this, "file"){
			{
				map(this::toFileInputStream).map(Image::new).subscribe(art::set);
			}

			private FileInputStream toFileInputStream(File value) {
				try {
					return new FileInputStream(value);
				} catch (FileNotFoundException e) {
					log.error(e.getMessage(), e);
					return null;
				}
			}
		};
		public ObjectProperty<File> fileProperty(){
		    return this.file;
		}
		public File getFile(){
		    return this.fileProperty().get();
		}
		public void setFile(File value){
		    this.fileProperty().set(value);
		}

		private final IntegerProperty power = new SimpleIntegerProperty(this, "power");

		public IntegerProperty powerProperty() {
			return this.power;
		}

		public Integer getPower() {
			return this.powerProperty().get();
		}

		public void setPower(Integer value) {
			this.powerProperty().set(value);
		}

		private final IntegerProperty toughness = new SimpleIntegerProperty(this, "toughness");

		public IntegerProperty toughnessProperty() {
			return this.toughness;
		}

		public Integer getToughness() {
			return this.toughnessProperty().get();
		}

		public void setToughness(Integer value) {
			this.toughnessProperty().set(value);
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
