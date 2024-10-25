package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.app.view.entity.card.face.CardFaceView;
import com.alver.fatefall.fx.app.view.entity.card.skin.adjacent.AdjacentSkin;
import com.alver.fatefall.fx.app.view.entity.card.skin.flippable.FlippableSkin;
import com.alver.fatefall.fx.app.view.entity.card.skin.stacked.StackedSkin;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.utils.ResourceUtil;
import com.alver.fatefall.fx.core.utils.SimpleClip;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

public class CardView extends Control {

	public static final URL FXML = CardView.class.getResource("CardView.fxml");

	private final CardFaceView front;
	private final CardFaceView back;

	/**
	 * === Constructor ===
	 */
	public CardView() {
		this(new CardFaceView(), new CardFaceView());
	}

	@Autowired
	public CardView(CardFaceView front, CardFaceView back) {
		this.front = front;
		this.back = back;

		front.cardFaceProperty().bind(cardProperty().flatMap(CardFX::frontProperty));
		back.cardFaceProperty().bind(cardProperty().flatMap(CardFX::backProperty));

		bindDimensions(front, back);
		setupContextMenu();
	}

	private void bindDimensions(CardFaceView front, CardFaceView back) {
		ObservableValue<Number> width = cardProperty.flatMap(CardFX::widthProperty).orElse(200);
		ObservableValue<Number> height = cardProperty.flatMap(CardFX::heightProperty).orElse(200);
		ObservableValue<Number> arcWidth = cardProperty.flatMap(CardFX::arcWidthProperty).orElse(20);
		ObservableValue<Number> arcHeight = cardProperty.flatMap(CardFX::arcHeightProperty).orElse(20);

		bindDimensions(front, width, height, arcWidth, arcHeight);
		bindDimensions(back, width, height, arcWidth, arcHeight);
	}

	private static void bindDimensions(
			CardFaceView front,
			ObservableValue<Number> width,
			ObservableValue<Number> height,
			ObservableValue<Number> arcWidth,
			ObservableValue<Number> arcHeight) {
		front.widthProperty().bind(width);
		front.heightProperty().bind(height);
		front.setClip(new SimpleClip(width, height, arcWidth, arcHeight));
	}

	public Skin<CardView> buildCardViewSkin(String skinName) {
		if (skinName == null) return createDefaultSkin();
		return switch (skinName) {
			case "Flippable":
				yield new FlippableSkin(this);
			case "Stacked":
				yield new StackedSkin(this);
			case "Adjacent":
				yield new AdjacentSkin(this);
			default:
				throw new UnsupportedOperationException(
						"No CardView skin for key: %s".formatted(skinName));
		};
	}

	private void setupContextMenu() {
		Menu viewMode = new Menu("View Mode");
		viewMode.getItems().setAll(
				buildMenuItem("Adjacent", AdjacentSkin.class, () -> setSkin(buildCardViewSkin("Adjacent"))),
				buildMenuItem("Stacked", StackedSkin.class, () -> setSkin(buildCardViewSkin("Stacked"))),
				buildMenuItem("Flippable", FlippableSkin.class, () -> setSkin(buildCardViewSkin("Flippable"))));

		ContextMenu contextMenu = new ContextMenu();
		contextMenu.getItems().setAll(viewMode);
		setContextMenu(contextMenu);
	}

	private MenuItem buildMenuItem(String text, Class<?> clazz, Runnable action) {
		MenuItem menuItem = new MenuItem(clazz.getSimpleName());
		ImageView imageView = new ImageView(ResourceUtil.image(clazz, "icon.png"));
		imageView.setFitWidth(100);
		imageView.setFitHeight(100);
		menuItem.setGraphic(imageView);
		menuItem.setOnAction(_ -> action.run());
		return menuItem;
	}


	@Override
	protected Skin<CardView> createDefaultSkin() {
		return new AdjacentSkin(this);
	}

	/**
	 * === Properties ===
	 */
	protected ObjectProperty<CardFX<?, ?>> cardProperty = new SimpleObjectProperty<>();

	public ObjectProperty<CardFX<?, ?>> cardProperty() {
		return cardProperty;
	}

	public CardFX<?, ?> getCard() {
		return cardProperty().get();
	}

	public void setCard(CardFX<?, ?> card) {
		cardProperty().set(card);
	}

	public CardFaceView getFront() {
		return front;
	}

	public CardFaceView getBack() {
		return back;
	}
}
