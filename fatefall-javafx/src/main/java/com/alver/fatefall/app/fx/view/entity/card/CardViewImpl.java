package com.alver.fatefall.app.fx.view.entity.card;

import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.editor.block.ImageBlock;
import com.alver.fatefall.app.fx.component.settings.FatefallProperties;
import com.alver.fatefall.app.services.ComponentFactory;
import com.alver.fatefall.data.entity.Card;
import com.google.common.cache.LoadingCache;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

@Prototype
public class CardViewImpl extends Control implements CardView<CardViewImpl> {

	private static final Image ADJACENT_IMAGE = loadImage("adjacent.png");
	private static final Image STACKED_IMAGE = loadImage("stacked.png");
	private static final Image FLIPPABLE_IMAGE = loadImage("flippable.png");

	private static Image loadImage(String url) {
		return new Image(Objects.requireNonNull(FlippableSkin.class.getResourceAsStream(url)));
	}

	/**
	 * === Properties ===
	 */
	protected ObjectProperty<Card> cardProperty = new SimpleObjectProperty<>();

	@Override
	public ObjectProperty<Card> cardProperty() {
		return cardProperty;
	}

	protected ObjectProperty<CardFaceView> frontProperty = new SimpleObjectProperty<>();

	@Override
	public ObjectProperty<CardFaceView> frontProperty() {
		return frontProperty;
	}

	protected ObjectProperty<CardFaceView> backProperty = new SimpleObjectProperty<>();

	@Override
	public ObjectProperty<CardFaceView> backProperty() {
		return backProperty;
	}

	protected BeanFactory beanFactory;
	protected FatefallProperties properties;
	protected LoadingCache<String, Image> imageCache;

	/**
	 * === Constructor ===
	 */
	@Autowired
	public CardViewImpl(
			BeanFactory beanFactory,
			ComponentFactory componentFactory,
			LoadingCache<String, Image> imageCache,
			FatefallProperties properties) {
		super();
		this.properties = properties;
		this.beanFactory = beanFactory;
		this.imageCache = imageCache;

		setFront(beanFactory.getBean(CardFaceView.class));
		setBack(beanFactory.getBean(CardFaceView.class));

		MenuItem adjacent = buildMenuItem("Adjacent", ADJACENT_IMAGE, () -> setSkin(new AdjacentSkin(this, properties)));
		MenuItem stacked = buildMenuItem("Stacked", STACKED_IMAGE, () -> setSkin(new StackedSkin(this, properties)));
		MenuItem flippable = buildMenuItem("Flippable", FLIPPABLE_IMAGE, () -> setSkin(new FlippableSkin(this, properties)));
		Menu menu = new Menu("View Mode");
		menu.getItems().setAll(adjacent, stacked, flippable);

		ContextMenu contextMenu = new ContextMenu();
		contextMenu.getItems().add(menu);
		setContextMenu(contextMenu);

		//Whenever card property changes, update the images.
		cardProperty().addListener((observable, oldCard, newCard) -> {
			getContextMenu().getItems().clear();
			getFront().getChildren().clear();
			getBack().getChildren().clear();
			if (newCard == null) {
				return;
			}

			buildCardFaces(newCard);

			getContextMenu().getItems().add(menu);
			getContextMenu().getItems().addAll(componentFactory.buildCardViewContextMenuItems(this));

			setupCardFace(getFront(), newCard.getFront().getImageUrl());
			setupCardFace(getBack(), newCard.getBack().getImageUrl());

		});

		properties.getCardViewSkinSelection().addListener((observable, oldValue, newValue) -> {
			setSkin(buildSkin(newValue));
		});
	}

	private void buildCardFaces(Card card) {
		try {
			FXMLLoader loader = new FXMLLoader();
			String frontFxml = card.getFront().getFxmlTemplate();
			if (frontFxml != null) {
				Node front = loader.load(new ByteArrayInputStream(frontFxml.getBytes()));
				getFront().getChildren().setAll(front);
			}

			String backFxml = card.getBack().getFxmlTemplate();
			if (backFxml != null) {
				Node back = loader.load(new ByteArrayInputStream(backFxml.getBytes()));
				getBack().getChildren().setAll(back);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void traverse(Node node, Consumer<Node> hook) {
		hook.accept(node);
		if (node instanceof Parent parent) {
			for (Node child : parent.getChildrenUnmodifiable()) {
				traverse(child, hook);
			}
		}
	}

	private void setupCardFace(CardFaceView cardFaceView, String imageUrl) {
		if (imageUrl == null) return;
		ImageBlock imageBlock = new ImageBlock(imageCache.getUnchecked(imageUrl));
		bindRegionDimensions(imageBlock, cardFaceView);
		cardFaceView.getChildren().setAll(imageBlock);
	}

	private void bindRegionDimensions(Region first, Region second) {
		first.minHeightProperty().bind(second.heightProperty());
		first.minWidthProperty().bind(second.widthProperty());
		first.maxHeightProperty().bind(second.heightProperty());
		first.maxWidthProperty().bind(second.widthProperty());
		first.prefHeightProperty().bind(second.heightProperty());
		first.prefWidthProperty().bind(second.widthProperty());
	}

	private MenuItem buildMenuItem(String text, Image image, Runnable action) {
		MenuItem menuItem = new MenuItem(text);
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(100);
		imageView.setFitHeight(100);
		menuItem.setGraphic(imageView);
		menuItem.setOnAction(a -> action.run());
		return menuItem;
	}

	@Override
	protected Skin<CardViewImpl> createDefaultSkin() {
		return buildSkin(properties.getCardViewSkinSelection().get());
	}

	private Skin<CardViewImpl> buildSkin(String skinName) {
		return switch (skinName) {
			case "Flippable":
				yield beanFactory.getBean(FlippableSkin.class, this, properties);
			case "Stacked":
				yield beanFactory.getBean(StackedSkin.class, this, properties);
			case "Adjacent":
				yield beanFactory.getBean(AdjacentSkin.class, this, properties);
			default:
				throw new RuntimeException();
		};
	}

}
