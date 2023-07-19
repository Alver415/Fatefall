package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.interfaces.ComponentFactory;
import com.alver.fatefall.api.models.Element;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.editor.components.ImageBlock;
import com.alver.fatefall.app.editor.components.TextBlock;
import com.alver.fatefall.app.fx.components.settings.FatefallProperties;
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

	private static final Image adjacentImage = loadImage("adjacent.png");
	private static final Image stackedImage = loadImage("stacked.png");
	private static final Image flippableImage = loadImage("flippable.png");

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

	protected ObjectProperty<CardFace> frontProperty = new SimpleObjectProperty<>();

	@Override
	public ObjectProperty<CardFace> frontProperty() {
		return frontProperty;
	}

	protected ObjectProperty<CardFace> backProperty = new SimpleObjectProperty<>();

	@Override
	public ObjectProperty<CardFace> backProperty() {
		return backProperty;
	}

	protected BeanFactory beanFactory;
	protected FatefallProperties properties;

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

		setFront(beanFactory.getBean(CardFace.class));
		setBack(beanFactory.getBean(CardFace.class));

		MenuItem adjacent = buildMenuItem("Adjacent", adjacentImage, () -> setSkin(new AdjacentSkin(this, properties)));
		MenuItem stacked = buildMenuItem("Stacked", stackedImage, () -> setSkin(new StackedSkin(this, properties)));
		MenuItem flippable = buildMenuItem("Flippable", flippableImage, () -> setSkin(new FlippableSkin(this, properties)));
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

			buildCardFace(newCard);

			getContextMenu().getItems().setAll(componentFactory.buildCardViewContextMenuItems(newCard));

			Element frontUrl = newCard.getElement("_front_");
			if (frontUrl != null)
				setupCardFace(getFront(), imageCache.getUnchecked(frontUrl.getValue()));

			Element backUrl = newCard.getElement("_back_");
			if (backUrl != null)
				setupCardFace(getBack(), imageCache.getUnchecked(backUrl.getValue()));

			for (Element childAttribute : newCard.getElements().values()) {
				if (childAttribute.getName().startsWith("_")) {
					Node childNode = buildElements(childAttribute);
					getFront().getChildren().add(childNode);
				}
			}
		});

		properties.getCardViewSkinSelection().addListener((observable, oldValue, newValue) -> {
			setSkin(buildSkin(newValue));
		});
	}

	private void buildCardFace(Card card) {
		try {
			FXMLLoader loader = new FXMLLoader();
			String fxml = card.getFxml();
			if (fxml != null) {
				Node root = loader.load(new ByteArrayInputStream(fxml.getBytes()));
				getFront().getChildren().setAll(root);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void traverse(Node node, Consumer<Node> hook){
		hook.accept(node);
		if (node instanceof Parent parent){
			for (Node child : parent.getChildrenUnmodifiable()){
				traverse(child, hook);
			}
		}

	}

	private Node buildElements(Element attribute) {
		TextBlock textBlock = new TextBlock();
		textBlock.textProperty().bindBidirectional(attribute.valueProperty());
		textBlock.topProperty().bindBidirectional(attribute.topProperty());
		textBlock.bottomProperty().bindBidirectional(attribute.bottomProperty());
		textBlock.leftProperty().bindBidirectional(attribute.leftProperty());
		textBlock.rightProperty().bindBidirectional(attribute.rightProperty());

		for (Element childAttribute : attribute.getElements().values()) {
			Node childNode = buildElements(childAttribute);
			textBlock.getChildren().add(childNode);
		}
		return textBlock;

	}

	private void setupCardFace(CardFace cardFace, Image image) {
		ImageBlock imageBlock = new ImageBlock(image);
		bindRegionDimensions(imageBlock, cardFace);
		cardFace.getChildren().setAll(imageBlock);
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
