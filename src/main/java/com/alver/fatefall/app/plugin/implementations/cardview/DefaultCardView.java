package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.fx.components.FXMLAutoLoad;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@FXMLAutoLoad
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DefaultCardView extends StackPane implements CardView<DefaultCardView> {

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
        //Whenever card property changes, update the images.
        cardProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> setupCardFaces(newValue));
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

    protected void setupCardFaces(Card card) {

        ImageView frontImageView = new ImageView(new Image(card.getFrontUrl(), true));
        ImageView backImageView = new ImageView(new Image(card.getBackUrl(), true));

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
