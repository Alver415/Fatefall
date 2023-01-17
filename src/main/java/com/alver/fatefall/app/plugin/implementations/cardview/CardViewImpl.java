package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.editor.components.ImageBlock;
import com.alver.fatefall.app.fx.components.settings.FatefallProperties;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
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

    protected ObjectProperty<CardFace> frontProperty = new SimpleObjectProperty<>();

    public ObjectProperty<CardFace> frontProperty() {
        return frontProperty;
    }

    protected ObjectProperty<CardFace> backProperty = new SimpleObjectProperty<>();

    public ObjectProperty<CardFace> backProperty() {
        return backProperty;
    }

    protected BeanFactory beanFactory;
    protected FatefallProperties properties;

    /**
     * === Constructor ===
     */
    @Autowired
    public CardViewImpl(BeanFactory beanFactory, FatefallProperties properties) {
        super();
        this.properties = properties;
        this.beanFactory = beanFactory;
        setFront(beanFactory.getBean(CardFace.class));
        setBack(beanFactory.getBean(CardFace.class));

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

            if (newValue.getFrontUrl() != null) {
                ImageBlock frontImageBlock = new ImageBlock(new Image(newValue.getFrontUrl(), true));
                bindDimensions(frontImageBlock, getFront());
                getFront().getChildren().setAll(frontImageBlock);
            }
            if (newValue.getBackUrl() != null) {
                ImageBlock backImageBlock = new ImageBlock(new Image(newValue.getBackUrl(), true));
                bindDimensions(backImageBlock, getBack());
                getBack().getChildren().setAll(backImageBlock);
            }
        });

        properties.getCardViewSkinSelection().addListener((observable, oldValue, newValue) -> {
            setSkin(buildSkin(newValue));
        });
    }

    private void bindDimensions(Region first, Region second) {
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
                yield new FlippableSkin(this);
            case "Stackable":
                yield new StackedSkin(this);
            case "Adjacent":
                yield new AdjacentSkin(this);
            default:
                throw new RuntimeException();
        };
    }

}
