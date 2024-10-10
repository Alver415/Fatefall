package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.app.view.entity.card.face.CardFaceView;
import com.alver.fatefall.fx.app.view.entity.card.skin.adjacent.AdjacentSkin;
import com.alver.fatefall.fx.app.view.entity.card.skin.flippable.FlippableSkin;
import com.alver.fatefall.fx.app.view.entity.card.skin.stacked.StackedSkin;
import com.alver.fatefall.fx.core.interfaces.AppController;
import com.alver.fatefall.fx.core.interfaces.AppView;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.EntityFX;
import com.alver.fatefall.fx.core.utils.ResourceUtil;
import com.alver.springfx.SpringFX;
import com.alver.springfx.annotations.Prototype;
import com.alver.springfx.model.FXMLControllerAndView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

@Prototype
public class CardView extends Control {

	public static final URL FXML = CardView.class.getResource("CardView.fxml");

	private final FatefallProperties properties;
	private final AppController appController;
	private final SpringFX springFX;

	private final CardFaceView front;
	private final CardFaceView back;

	/**
	 * === Constructor ===
	 */
	@Autowired
	public CardView(
			CardFaceView front,
			CardFaceView  back,
			AppController appController,
			FatefallProperties properties,
			SpringFX springFX) {
		this.front = front;
		this.back = back;

		this.properties = properties;
		this.appController = appController;
		this.springFX = springFX;

		front.cardFaceProperty().bind(cardProperty().flatMap(CardFX::frontProperty));
		back.cardFaceProperty().bind(cardProperty().flatMap(CardFX::backProperty));

		properties.getCardViewSkinSelection().subscribe(skin -> {
			if (skin != null) setSkin(buildSkin(skin));
		});
		buildContextMenu();

		scaleXProperty().bind(properties.getCardViewScale());
		scaleYProperty().bind(properties.getCardViewScale());
	}

	private void buildContextMenu() {
		MenuItem edit = new MenuItem("Edit");
		edit.setOnAction(_ -> {
			FXMLControllerAndView<CardEditorView, BorderPane> cnv = springFX.load(CardEditorView.class);
			cnv.controller().setCard(getCard());
			AppView appView = AppView.of(cardProperty.map(EntityFX::getName), cnv.view());
			appController.registerView(appView);
		});

		Menu viewMode = new Menu("View Mode");
		viewMode.getItems().setAll(
				buildMenuItem("Adjacent", AdjacentSkin.class, () -> setSkin(buildSkin("Adjacent"))),
				buildMenuItem("Stacked", StackedSkin.class, () -> setSkin(buildSkin("Stacked"))),
				buildMenuItem("Flippable", FlippableSkin.class, () -> setSkin(buildSkin("Flippable"))));

		ContextMenu contextMenu = new ContextMenu();
		contextMenu.getItems().setAll(edit, viewMode);
//		setContextMenu(contextMenu);
	}

	private MenuItem buildMenuItem(String text, Class<?> clazz, Runnable action) {
		MenuItem menuItem = new MenuItem(text);
		ImageView imageView = new ImageView(ResourceUtil.image(clazz, "icon.png"));
		imageView.setFitWidth(100);
		imageView.setFitHeight(100);
		menuItem.setGraphic(imageView);
		menuItem.setOnAction(_ -> action.run());
		return menuItem;
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return buildSkin(properties.getCardViewSkinSelection().get());
	}

	private Skin<CardView> buildSkin(String skinName) {
		return switch (skinName) {
			case "Flippable":
				yield new FlippableSkin(this, properties);
			case "Stacked":
				yield new StackedSkin(this, properties);
			case "Adjacent":
				yield new AdjacentSkin(this, properties);
			default:
				throw new UnsupportedOperationException(
						"No CardView skin for key: %s".formatted(skinName));
		};
	}

	/**
	 * === Properties ===
	 */
	protected ObjectProperty<CardFX> cardProperty = new SimpleObjectProperty<>();

	public ObjectProperty<CardFX> cardProperty() {
		return cardProperty;
	}

	public CardFX getCard() {
		return cardProperty().get();
	}

	public void setCard(CardFX card) {
		cardProperty().set(card);
	}

	public CardFaceView getFront() {
		return front;
	}

	public CardFaceView getBack() {
		return back;
	}
}
