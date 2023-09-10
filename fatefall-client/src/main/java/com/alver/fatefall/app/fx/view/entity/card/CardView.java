package com.alver.fatefall.app.fx.view.entity.card;

import com.alver.fatefall.app.fx.component.settings.FatefallProperties;
import com.alver.fatefall.app.fx.model.entity.CardFX;
import com.alver.fatefall.app.fx.view.entity.card.skin.adjacent.AdjacentSkin;
import com.alver.fatefall.app.fx.view.entity.card.skin.flippable.FlippableSkin;
import com.alver.fatefall.app.fx.view.entity.card.skin.stacked.StackedSkin;
import com.alver.fatefall.utils.ResourceUtil;
import com.alver.springfx.SpringFX;
import com.alver.springfx.annotations.Prototype;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Prototype
public class CardView extends Control {

    private final SpringFX springFX;
    private final FatefallProperties properties;

    private final CardFacePane frontPane;
    private final CardFacePane backPane;

    /**
     * === Constructor ===
     */
    @Autowired
    public CardView(SpringFX springFX, FatefallProperties properties) {
        this.springFX = springFX;
        this.properties = properties;
        this.frontPane = springFX.loadView(CardFacePane.class);
        this.backPane = springFX.loadView(CardFacePane.class);
        buildContextMenu();
        getFrontPane().cardFaceProperty().bind(Bindings.createObjectBinding(() ->
                Optional.ofNullable(getCard()).map(CardFX::getFront).orElse(null), cardProperty));
        getBackPane().cardFaceProperty().bind(Bindings.createObjectBinding(() ->
                Optional.ofNullable(getCard()).map(CardFX::getBack).orElse(null), cardProperty));
    }

    private void buildContextMenu() {
        Menu menu = new Menu("View Mode");
        menu.getItems().setAll(
                buildMenuItem("Adjacent", ResourceUtil.image("adjacent.png"), () -> setSkin(new AdjacentSkin(this))),
                buildMenuItem("Stacked", ResourceUtil.image("stacked.png"), () -> setSkin(new StackedSkin(this))),
                buildMenuItem("Flippable", ResourceUtil.image("flippable.png"), () -> setSkin(new FlippableSkin(this))));

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().setAll(menu);
        setContextMenu(contextMenu);
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
    protected Skin<?> createDefaultSkin() {
        return buildSkin(properties.getCardViewSkinSelection().get());
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

    public CardFacePane getFrontPane() {
        return frontPane;
    }

    public CardFacePane getBackPane() {
        return backPane;
    }
}
