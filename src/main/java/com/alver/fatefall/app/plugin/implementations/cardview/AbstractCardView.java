package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.models.Card;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class AbstractCardView<T extends Node> extends StackPane implements CardView<T> {

    @FXML
    protected CardFace frontFace;
    @FXML
    protected CardFace backFace;

    public CardFace getFrontFace() {
        return frontFace;
    }
    public CardFace getBackFace() {
        return backFace;
    }

    /**
     * === Card Property ===
     */
    protected ObjectProperty<Card> cardProperty = new SimpleObjectProperty<>();

    public void setCard(Card value) {
        cardProperty.set(value);
    }

    public Card getCard() {
        return cardProperty.get();
    }

    public ObjectProperty<Card> cardProperty() {
        return cardProperty;
    }

    public AbstractCardView() {
        super();
        //Whenever card property changes, update the images.
        cardProperty().addListener((observable, oldValue, newValue) -> {
            frontFace.getChildren().clear();
            backFace.getChildren().clear();
            if (newValue == null){
                return;
            }

            ImageView frontImageView = new ImageView(new Image(newValue.getFrontUrl(), true));
            ImageView backImageView = new ImageView(new Image(newValue.getBackUrl(), true));

            frontImageView.fitHeightProperty().bind(frontFace.heightProperty());
            frontImageView.fitWidthProperty().bind(frontFace.widthProperty());

            backImageView.fitHeightProperty().bind(backFace.heightProperty());
            backImageView.fitWidthProperty().bind(backFace.widthProperty());

            frontFace.getChildren().setAll(frontImageView);
            backFace.getChildren().setAll(backImageView);
        });
    }

}
