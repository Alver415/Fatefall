package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.editor.components.ImageBlock;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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

    public ObjectProperty<Card> cardProperty() {
        return cardProperty;
    }

    protected ObjectProperty<CardFace> frontProperty = new SimpleObjectProperty<>(new CardFace());

    public ObjectProperty<CardFace> frontProperty() {
        return frontProperty;
    }

    protected ObjectProperty<CardFace> backProperty = new SimpleObjectProperty<>(new CardFace());

    public ObjectProperty<CardFace> backProperty() {
        return backProperty;
    }


    /**
     * === Constructor ===
     */
    public CardViewImpl() {
        super();
        setSkin(createDefaultSkin());

        MenuItem adjacent = buildMenuItem("Adjacent", adjacentImage, () -> setSkin(new AdjacentSkin(this)));
        MenuItem stacked = buildMenuItem("Stacked", stackedImage, () -> setSkin(new StackedSkin(this)));
        MenuItem flippable = buildMenuItem("Flippable", flippableImage, () -> setSkin(new FlippableSkin(this)));
        Menu menu = new Menu("View Mode");
        menu.getItems().setAll(adjacent, stacked, flippable);

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(menu);
        setContextMenu(contextMenu);

        //Whenever card property changes, update the images.
        cardProperty().addListener((observable, oldValue, newValue) -> {
            getFront().getChildren().clear();
            getBack().getChildren().clear();
            if (newValue == null) {
                return;
            }

            ImageBlock frontImageBlock = new ImageBlock(new Image(newValue.getFrontUrl(), true));
            ImageBlock backImageBlock = new ImageBlock(new Image(newValue.getBackUrl(), true));

            frontImageBlock.prefHeightProperty().bind(getFront().heightProperty());
            frontImageBlock.prefWidthProperty().bind(getFront().widthProperty());

            backImageBlock.prefHeightProperty().bind(getBack().heightProperty());
            backImageBlock.prefWidthProperty().bind(getBack().widthProperty());

            getFront().getChildren().setAll(frontImageBlock);
            getBack().getChildren().setAll(backImageBlock);
        });
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
        return new AdjacentSkin(this);
    }

}
