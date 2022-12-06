package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.app.fx.components.FXMLLoadable;
import com.alver.fatefall.app.plugin.interfaces.CardView;
import com.alver.fatefall.app.plugin.models.Card;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class DefaultCardView extends StackPane implements CardView, FXMLLoadable {

    @FXML
    protected CardPane cardPane;

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

    public CardPane getCardPane() {
        return cardPane;
    }

    public DefaultCardView() {
        super();
        loadFxml();
        //Whenever card property changes, update the images.
        cardProperty().addListener((observable, oldValue, newValue) -> {
            setupCardFaces(newValue);
        });
    }

    @FXML
    public void flip() {
        cardPane.flip();
    }

    @FXML
    public void spinLeft() {
        cardPane.spinLeft();
    }

    @FXML
    public void spinRight() {
        cardPane.spinRight();
    }

    private void setupCardFaces(Card card) {
        cardPane.setCardWidth(300d);
        cardPane.setCardHeight(450d);

        ImageView frontImageView = new ImageView(new Image(card.getFrontUrl()));
        ImageView backImageView = new ImageView(new Image(card.getBackUrl()));

        CardFace frontFace = new CardFace(frontImageView);
        CardFace backFace = new CardFace(backImageView);

        frontImageView.fitHeightProperty().bind(frontFace.heightProperty());
        frontImageView.fitWidthProperty().bind(frontFace.widthProperty());

        backImageView.fitHeightProperty().bind(backFace.heightProperty());
        backImageView.fitWidthProperty().bind(backFace.widthProperty());

        cardPane.setFront(frontFace);
        cardPane.setBack(backFace);
    }
}
