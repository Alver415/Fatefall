package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.core.api.EntityApi;
import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.app.menu.CardFaceContextMenuFactory;
import com.alver.fatefall.fx.app.view.entity.card.face.CardFaceView;
import com.alver.fatefall.fx.app.view.entity.card.skin.adjacent.AdjacentSkin;
import com.alver.fatefall.fx.app.view.entity.card.skin.flippable.FlippableSkin;
import com.alver.fatefall.fx.app.view.entity.card.skin.stacked.StackedSkin;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.TemplateFX;
import com.alver.fatefall.fx.core.utils.ResourceUtil;
import com.alver.springfx.annotations.Prototype;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;

@Prototype
public class CardView extends Control {

	private final FatefallProperties properties;

	protected final CardFaceContextMenuFactory contextMenuFactory;
	protected final EntityApi<TemplateFX> templateApi;

	private final CardFaceView front;
	private final CardFaceView back;

	/**
	 * === Constructor ===
	 */
	@Autowired
	public CardView(
			CardFaceView front,
			CardFaceView back,
			FatefallProperties properties,
			CardFaceContextMenuFactory contextMenuFactory,
			EntityApi<TemplateFX> templateApi) {
		this.front = front;
		this.back = back;
		this.properties = properties;
		this.contextMenuFactory = contextMenuFactory;
		this.templateApi = templateApi;

		cardProperty.addListener((observable, oldValue, newValue) -> {
			if (newValue != null){
				newValue.getFront().setCard(newValue);
				newValue.getBack().setCard(newValue);
			}
		});
		front.cardFaceProperty().bind(cardProperty.flatMap(CardFX::frontProperty));
		back.cardFaceProperty().bind(cardProperty.flatMap(CardFX::backProperty));

		properties.getCardViewSkinSelection().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				setSkin(buildSkin(newValue));
			}
		});
		buildContextMenu();

		scaleXProperty().bind(properties.getCardViewScale());
		scaleYProperty().bind(properties.getCardViewScale());
	}

	private void buildContextMenu() {
		Menu viewMode = new Menu("View Mode");
		viewMode.getItems().setAll(
				buildMenuItem("Adjacent", AdjacentSkin.class, () -> setSkin(buildSkin("Adjacent"))),
				buildMenuItem("Stacked", StackedSkin.class, () -> setSkin(buildSkin("Stacked"))),
				buildMenuItem("Flippable", FlippableSkin.class, () -> setSkin(buildSkin("Flippable"))));

		ContextMenu contextMenu = new ContextMenu();
		contextMenu.getItems().setAll(viewMode);
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

	public CardFaceView getFront() {
		return front;
	}

	public CardFaceView getBack() {
		return back;
	}
}
