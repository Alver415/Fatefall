package com.alver.fatefall.app.fx.components.cardview;

import com.alver.fatefall.api.FatefallApi;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.fx.components.FxComponent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;


public class CardView extends StackPane implements FxComponent {

    @Autowired
    private FatefallApi fatefallApi;

    @FXML
    protected CardPane cardPane;

    public static final Image PLACEHOLDER = new Image(Objects.requireNonNull(
            CardView.class.getResource("placeholder.png")).toExternalForm());

    /* === Properties ===*/

    /**
     * === Card Property ==
     */
    protected ObjectProperty<Card> cardProperty = new SimpleObjectProperty<>();
    public final void setCard(Card value) {
        cardProperty.set(value);
    }
    public final Card getCard() {
        return cardProperty.get();
    }
    public final ObjectProperty<Card> cardProperty() {
        return cardProperty;
    }


    public CardView() {
        initFxml();
    }

    public CardPane getCardPane(){
        return cardPane;
    }

    @FXML
    public void initialize() {
        //Whenever card property changes, update the images.
        cardProperty().addListener((observable, oldValue, newValue) -> {
            setupCardFaces(newValue);
        });
    }

    @FXML
    public void flip(){
        cardPane.flip();
    }
    @FXML
    public void spinLeft(){
        cardPane.spinLeft();
    }
    @FXML
    public void spinRight(){
        cardPane.spinRight();
    }

    private void setupCardFaces(Card card) {
        if (card.getFxml() != null){
            try {
                CardPane cardPane = new FXMLLoader().load(new ByteArrayInputStream(card.getFxml().getBytes()));
                this.getChildren().remove(this.cardPane);
                this.getChildren().add(0, cardPane);
                this.cardPane = cardPane;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        runAsync(() -> {
            try {
                Image frontFaceImage = fatefallApi.getCardApi().getImage(card.getFrontFaceUrl());
                Image backFaceImage = fatefallApi.getCardApi().getImage(card.getBackFaceUrl());

                runFx(() -> {
                    if (Objects.equals(frontFaceImage.getProgress(), 1.0)) {
                        setImages(frontFaceImage, backFaceImage);
                    } else {
                        frontFaceImage.progressProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue.equals(1.0)) {
                                setImages(frontFaceImage, backFaceImage);
                            }
                        });
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setImages(Image frontFaceImage, Image backFaceImage) {
        cardPane.setCardWidth(frontFaceImage.getWidth());
        cardPane.setCardHeight(frontFaceImage.getHeight());

        ImageBlock frontBlock = new ImageBlock(frontFaceImage.getException() == null ? frontFaceImage : PLACEHOLDER);
        ImageBlock backBlock = new ImageBlock(backFaceImage.getException() == null ? backFaceImage : PLACEHOLDER);

        CardFace frontFace = new CardFace(frontBlock);
        CardFace backFace = new CardFace(backBlock);

        frontBlock.minWidthProperty().bind(frontFace.widthProperty());
        frontBlock.minHeightProperty().bind(frontFace.heightProperty());
        frontBlock.prefWidthProperty().bind(frontFace.widthProperty());
        frontBlock.prefHeightProperty().bind(frontFace.heightProperty());
        frontBlock.maxWidthProperty().bind(frontFace.widthProperty());
        frontBlock.maxHeightProperty().bind(frontFace.heightProperty());

        backBlock.minWidthProperty().bind(backFace.widthProperty());
        backBlock.minHeightProperty().bind(backFace.heightProperty());
        backBlock.prefWidthProperty().bind(backFace.widthProperty());
        backBlock.prefHeightProperty().bind(backFace.heightProperty());
        backBlock.maxWidthProperty().bind(backFace.widthProperty());
        backBlock.maxHeightProperty().bind(backFace.heightProperty());

        cardPane.setFront(frontFace);
        cardPane.setBack(backFace);
    }
}
