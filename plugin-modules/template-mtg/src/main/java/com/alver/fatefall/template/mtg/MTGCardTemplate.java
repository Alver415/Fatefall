package com.alver.fatefall.template.mtg;

import com.alver.fatefall.fx.app.view.entity.card.template.BaseTemplate;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.*;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;

import java.util.Iterator;
import java.util.Map;

import static com.alver.fatefall.template.mtg.ColorIdentity.*;

@FXMLPrototype
public class MTGCardTemplate extends BaseTemplate {

	@FXML
	protected ObjectProperty<Image> leftBackground = new SimpleObjectProperty<>(this, "leftBackground");
	@FXML
	protected ObjectProperty<Image> rightBackground = new SimpleObjectProperty<>(this, "rightBackground");

	@FXML
	protected void initialize() {
		colors.addListener((SetChangeListener<? super Color>) observable -> {
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
	}

	private static final Map<ColorIdentity, Image> backgrounds = Map.of(
			COLORLESS, new Image("file:assets/background/ccard.png"),
			MONO_WHITE, new Image("file:assets/background/wcard.png"),
			MONO_BLUE, new Image("file:assets/background/ucard.png"),
			MONO_BLACK, new Image("file:assets/background/bcard.png"),
			MONO_RED, new Image("file:assets/background/rcard.png"),
			MONO_GREEN, new Image("file:assets/background/gcard.png"),
			WUBRG,new Image("file:assets/background/mcard.png"));

	private static Image getBackground(ColorIdentity colorIdentity) {
		return backgrounds.get(colorIdentity);
	}

	protected final StringProperty name = new SimpleStringProperty(this, "name");
	protected final ObjectProperty<ManaCost> manaCost = new SimpleObjectProperty<>(this, "manaCost");
	protected final SetProperty<Color> colors = new SimpleSetProperty<>(this, "colors");
	protected final ObjectProperty<Node> art = new SimpleObjectProperty<>(this, "art");

	protected final ListProperty<String> cardTypes = new SimpleListProperty<>(this, "cardTypes");
	protected final ListProperty<String> superTypes = new SimpleListProperty<>(this, "superTypes");
	protected final ListProperty<String> subTypes = new SimpleListProperty<>(this, "subTypes");
	protected final ObjectProperty<Rarity> rarity = new SimpleObjectProperty<>(this, "rarity");

	protected final IntegerProperty power = new SimpleIntegerProperty(this, "power");
	protected final IntegerProperty toughness = new SimpleIntegerProperty(this, "toughness");
	protected final IntegerProperty loyalty = new SimpleIntegerProperty(this, "loyalty");

}
