package com.alver.fatefall.app.fx.view.entity.card;

import com.alver.fatefall.app.fx.editor.block.ImageBlock;
import com.alver.fatefall.app.fx.entity.CardFX;
import com.alver.fatefall.app.fx.view.entity.card.skin.adjacent.AdjacentSkin;
import com.alver.fatefall.app.fx.view.entity.card.skin.flippable.FlippableSkin;
import com.alver.fatefall.app.fx.view.entity.card.skin.stacked.StackedSkin;
import com.alver.fatefall.utils.ResourceUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.function.Consumer;

public class CardView extends Control {

    private static final Image ADJACENT_IMAGE = ResourceUtil.image("adjacent.png");
    private static final Image STACKED_IMAGE = ResourceUtil.image("stacked.png");
    private static final Image FLIPPABLE_IMAGE = ResourceUtil.image("flippable.png");

    /**
     * === Constructor ===
     */
    public CardView() {
        super();

        setFront(new CardFacePane());
        setBack(new CardFacePane());


        MenuItem adjacent = buildMenuItem("Adjacent", ADJACENT_IMAGE, () -> setSkin(new AdjacentSkin(this)));
        MenuItem stacked = buildMenuItem("Stacked", STACKED_IMAGE, () -> setSkin(new StackedSkin(this)));
        MenuItem flippable = buildMenuItem("Flippable", FLIPPABLE_IMAGE, () -> setSkin(new FlippableSkin(this)));
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
//			getContextMenu().getItems().addAll(componentFactory.buildCardViewContextMenuItems(this));

            setupCardFace(getFront(), newCard.getFront().getImageUrl());
            setupCardFace(getBack(), newCard.getBack().getImageUrl());

        });
    }

    private void buildCardFaces(CardFX card) {
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
            throw new RuntimeException(e);
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

    private void setupCardFace(CardFacePane cardFaceView, String imageUrl) {
        if (imageUrl == null) return;
        ImageBlock imageBlock = new ImageBlock(new Image(imageUrl));
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

    private Skin<CardView> buildSkin(String skinName) {
        return switch (skinName) {
            case "Flippable":
                yield new FlippableSkin(this);
            case "Stacked":
                yield new StackedSkin(this);
            case "Adjacent":
                yield new AdjacentSkin(this);
            default:
                throw new RuntimeException();
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


    protected ObjectProperty<CardFacePane> frontProperty = new SimpleObjectProperty<>();

    public ObjectProperty<CardFacePane> frontProperty() {
        return frontProperty;
    }

    public CardFacePane getFront() {
        return frontProperty().get();
    }

    public void setFront(CardFacePane front) {
        frontProperty().set(front);
    }

    protected ObjectProperty<CardFacePane> backProperty = new SimpleObjectProperty<>();

    public ObjectProperty<CardFacePane> backProperty() {
        return backProperty;
    }

    public CardFacePane getBack() {
        return backProperty().get();
    }

    public void setBack(CardFacePane back) {
        backProperty().set(back);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new FlippableSkin(this);
    }

}
