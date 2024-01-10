package com.alver.fatefall.template.mtg;

import com.alver.fatefall.fx.app.view.entity.card.template.BaseTemplate;
import com.alver.fatefall.fx.core.utils.FXUtils;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.alver.fatefall.template.mtg.ColorIdentity.*;

@FXMLPrototype
public class MTGCardTemplate extends BaseTemplate {


	@FXML
	protected void initialize() {
		colors.addListener((SetChangeListener<? super Color>) observable -> {
			FXUtils.runFx(() -> {
				boolean colorless = observable.getSet().isEmpty();
				if (colorless) {
					Image colorlessBackground = getBackground(COLORLESS);
					leftBackground.set(colorlessBackground);
					rightBackground.set(colorlessBackground);
				} else if (observable.getSet().size() == 1) {
					Iterator<? extends Color> i = observable.getSet().iterator();
					Image background = getBackground(of(i.next()));
					leftBackground.set(background);
					rightBackground.set(background);
				} else if (observable.getSet().size() == 2) {
					Iterator<? extends Color> i = observable.getSet().iterator();
					leftBackground.set(getBackground(ColorIdentity.of(i.next())));
					rightBackground.set(getBackground(ColorIdentity.of(i.next())));
				} else {
					Image background = getBackground(WUBRG);
					leftBackground.set(background);
					rightBackground.set(background);
				}
			});
		});
		colors.addAll(Set.of(Color.RED,Color.BLUE));
	}

	private static final Map<ColorIdentity, Image> backgrounds = Map.of(
			COLORLESS, loadImage("ccard.jpg"),
			MONO_WHITE, loadImage("wcard.jpg"),
			MONO_BLUE, loadImage("ucard.jpg"),
			MONO_BLACK, loadImage("bcard.jpg"),
			MONO_RED, loadImage("rcard.jpg"),
			MONO_GREEN, loadImage("gcard.jpg"),
			WUBRG, loadImage("mcard.jpg"));

	private static Image loadImage(String fileName) {
		return new Image(Objects.requireNonNull(MTGCardTemplate.class.getResourceAsStream("assets/" + fileName)));
	}

	private static Image getBackground(ColorIdentity colorIdentity) {
		return backgrounds.get(colorIdentity);
	}

	protected final StringProperty name = new SimpleStringProperty(this, "name");
	protected final ObjectProperty<ManaCost> manaCost = new SimpleObjectProperty<>(this, "manaCost");
	protected final SetProperty<Color> colors = new SimpleSetProperty<>(this, "colors", FXCollections.observableSet());
	protected final ObjectProperty<Node> art = new SimpleObjectProperty<>(this, "art");

	protected final ListProperty<String> cardTypes = new SimpleListProperty<>(this, "cardTypes");
	protected final ListProperty<String> superTypes = new SimpleListProperty<>(this, "superTypes");
	protected final ListProperty<String> subTypes = new SimpleListProperty<>(this, "subTypes");
	protected final ObjectProperty<Rarity> rarity = new SimpleObjectProperty<>(this, "rarity");

	protected final IntegerProperty power = new SimpleIntegerProperty(this, "power");
	protected final IntegerProperty toughness = new SimpleIntegerProperty(this, "toughness");
	protected final IntegerProperty loyalty = new SimpleIntegerProperty(this, "loyalty");


	@FXML
	protected ObjectProperty<Image> leftBackground = new SimpleObjectProperty<>(this, "leftBackground");
	public ObjectProperty<Image> leftBackgroundProperty() {
		return leftBackground;
	}
	public Image getLeftBackground() {
		return leftBackground.get();
	}
	public void setLeftBackground(Image leftBackground) {
		this.leftBackground.set(leftBackground);
	}


	@FXML
	protected ObjectProperty<Image> rightBackground = new SimpleObjectProperty<>(this, "rightBackground");
	public ObjectProperty<Image> rightBackgroundProperty() {
		return rightBackground;
	}
	public Image getRightBackground() {
		return rightBackground.get();
	}
	public void setRightBackground(Image rightBackground) {
		this.rightBackground.set(rightBackground);
	}
}
