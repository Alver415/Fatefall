package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.app.view.entity.card.face.CardFaceController;
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
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Prototype
public class CardView extends Control {

	private final FatefallProperties properties;
	private final AppController appController;
	private final SpringFX springFX;

	private final Node front;
	private final Node back;

	/**
	 * === Constructor ===
	 */
	@Autowired
	public CardView(
			FXMLControllerAndView<CardFaceController, Pane> frontControllerAndView,
			FXMLControllerAndView<CardFaceController, Pane> backControllerAndView,
			AppController appController,
			FatefallProperties properties,
			SpringFX springFX) {
		this.properties = properties;
		this.appController = appController;
		this.springFX = springFX;

		this.front = new Group(frontControllerAndView.view());
		this.back = new Group(backControllerAndView.view());
		CardFaceController frontController = frontControllerAndView.controller();
		CardFaceController backController = backControllerAndView.controller();

		frontController.cardProperty().bind(cardProperty);
		backController.cardProperty().bind(cardProperty);

		//FIXME
		frontController.cardFaceProperty().bind(Bindings.createObjectBinding(() ->
				Optional.ofNullable(getCard()).map(CardFX::getFront).orElse(null), cardProperty));
		backController.cardFaceProperty().bind(Bindings.createObjectBinding(() ->
				Optional.ofNullable(getCard()).map(CardFX::getBack).orElse(null), cardProperty));
		properties.getCardViewSkinSelection().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				setSkin(buildSkin(newValue));
			}
		});
		buildContextMenu();
	}

	private void buildContextMenu() {
		MenuItem open = new MenuItem("Edit");
		open.setOnAction(a -> {
			FXMLControllerAndView<CardEditorView, BorderPane> cnv = springFX.load(CardEditorView.class);
			cnv.controller().setCard(getCard());
			AppView appView = AppView.of(cardProperty.map(EntityFX::getName), cnv.view());
			appController.addView(appView);
		});

		Menu viewMode = new Menu("View Mode");
		viewMode.getItems().setAll(
				buildMenuItem("Adjacent", AdjacentSkin.class, () -> setSkin(buildSkin("Adjacent"))),
				buildMenuItem("Stacked", StackedSkin.class, () -> setSkin(buildSkin("Stacked"))),
				buildMenuItem("Flippable", FlippableSkin.class, () -> setSkin(buildSkin("Flippable"))));

		ContextMenu contextMenu = new ContextMenu();
		contextMenu.getItems().setAll(open, viewMode);
		setContextMenu(contextMenu);
	}

	private MenuItem buildMenuItem(String text, Class<?> clazz, Runnable action) {
		MenuItem menuItem = new MenuItem(text);
		ImageView imageView = new ImageView(ResourceUtil.image(clazz, "icon.png"));
		imageView.setFitWidth(100);
		imageView.setFitHeight(100);
		menuItem.setGraphic(imageView);
		menuItem.setOnAction(a -> action.run());
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

	public Node getFront() {
		return front;
	}

	public Node getBack() {
		return back;
	}
}
