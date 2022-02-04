package com.alver.fatefall.fx.components;

import com.alver.fatefall.ImageRepository;
import com.scryfall.api.models.Card;
import com.scryfall.api.models.Layouts;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.WritableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Objects;


public class CardView extends StackPane implements Controller {

    public static final Image CARD_BACK = new Image(Objects.requireNonNull(
            CardView.class.getResource("card_back.png")).toExternalForm());
    public static final Image FLIP_FRONT = new Image(Objects.requireNonNull(
            CardView.class.getResource("flip_front.png")).toExternalForm());
    public static final Image FLIP_BACK = new Image(Objects.requireNonNull(
            CardView.class.getResource("flip_back.png")).toExternalForm());

    protected ImageRepository imageRepository;

    @FXML
    protected ImageView imageView;

    @FXML
    protected Button spinLeftButton;
    @FXML
    protected Button spinRightButton;

    @FXML
    protected Button flipButton;

    @FXML
    protected ProgressIndicator progressIndicator;

    protected Card card;
    protected Image frontFace;
    protected Image backFace;

    public CardView() {
        this(null);
    }

    public CardView(Card card) {
        this.card = card;
        fxmlLoad("card-view.fxml");
    }

    public void setCard(Card card) {
        this.card = card;
        runAsync(this::loadCardImages);
    }

    public Card getCard() {
        return this.card;
    }

    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @FXML
    public void initialize() {
        runFx(() -> this.setPickOnBounds(false));
        runFx(() -> imageView.setEffect(new DropShadow(5, Color.BLACK)));
        runAsync(this::loadCardImages);
    }

    private synchronized void loadCardImages() {
        if (card == null){
            frontFace = CARD_BACK;
            backFace = CARD_BACK;
            return;
        } else {
            runFx(() -> progressIndicator.setVisible(true));
            switch (card.layout()) {
                case TRANSFORM, MODAL_DFC, DOUBLE_FACED_TOKEN, ART_SERIES, REVERSIBLE_CARD -> {
                    frontFace = imageRepository.get(card.cardFaces().get(0).imageUris().png(), true);
                    backFace = imageRepository.get(card.cardFaces().get(1).imageUris().png(), true);
                }
                default -> {
                    frontFace = imageRepository.get(card.imageUris().png(), true);
                    backFace = CARD_BACK;
                }
            }
        }

        runFx(() -> {
            progressIndicator.progressProperty().bind(frontFace.progressProperty());
            if (Objects.equals(frontFace.getProgress(), 1.0)) {
                onImageLoaded();
            } else {
                frontFace.progressProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue.equals(1.0)) {
                        onImageLoaded();
                    }
                });
            }
        });
    }

    private void onImageLoaded() {
        setupControls();
        imageView.setImage(frontFace);
        progressIndicator.setVisible(false);
    }

    private void setupControls() {
        boolean isDoubleFaced = Layouts.isDoubleFaced(card);
        boolean isRotated = Layouts.isRotated(card);

        CardRotation cardRotation = new CardRotation();
        flipButton.setVisible(true);
        flipButton.setStyle("-fx-background-radius: 100px; -fx-background-color: " +
                (isDoubleFaced ? "#FFFA;" : "#FFF2;"));
        flipButton.setOnMouseClicked((event) -> cardRotation.flip());

        spinLeftButton.setVisible(true);
        spinLeftButton.setStyle("-fx-background-radius: 100px; -fx-background-color: " +
                (isRotated ? "#FFFA;" : "#FFF2;"));
        spinLeftButton.setOnMouseClicked((event) -> cardRotation.spinLeft());

        spinRightButton.setVisible(true);
        spinRightButton.setStyle("-fx-background-radius: 100px; -fx-background-color: " +
                (isRotated ? "#FFFA;" : "#FFF2;"));
        spinRightButton.setOnMouseClicked((event) -> cardRotation.spinRight());
    }

    private class CardRotation {

        private enum Side {FRONT, BACK}

        private enum Spin {UP, RIGHT, DOWN, LEFT}

        private Side side = Side.FRONT;
        private Spin spin = Spin.UP;

        private static final Duration end = Duration.seconds(0.2);
        private static final Duration half = end.divide(2);
        private static final Duration start = Duration.ZERO;

        private final Timeline flipTimeline;
        private final Timeline spinTimeline;

        private CardRotation() {
            flipTimeline = new Timeline();
            spinTimeline = new Timeline();
        }


        private void spinLeft() {
            Spin[] spins = Spin.values();
            Spin nextSpin = spins[Math.floorMod(this.spin.ordinal() - 1, spins.length)];
            spin(-90);
            this.spin = nextSpin;
        }

        private void spinRight() {
            Spin[] spins = Spin.values();
            Spin nextSpin = spins[Math.floorMod(this.spin.ordinal() + 1, spins.length)];
            spin(90);
            this.spin = nextSpin;
        }

        private void spin(double amount) {
            double rotationStart = this.spin.ordinal() * 90;
            double rotationEnd = rotationStart + amount;

            ObservableList<KeyFrame> keyFrames = spinTimeline.getKeyFrames();
            keyFrames.clear();

            imageView.setRotationAxis(Rotate.Z_AXIS);
            keyFrames.add(keyFrame(start, imageView.rotateProperty(), rotationStart));
            keyFrames.add(keyFrame(end, imageView.rotateProperty(), rotationEnd));
            spinTimeline.setOnFinished((event) -> {
                imageView.setRotate(this.spin.ordinal() * 90);
            });
            spinTimeline.playFromStart();
        }

        private void flip() {
            Side[] sides = Side.values();
            flipTo(sides[Math.floorMod(side.ordinal() + 1, sides.length)]);
        }

        private void flipTo(Side side) {
            //First reset spin.
            this.spin = Spin.UP;
            imageView.setRotationAxis(Rotate.Z_AXIS);
            imageView.setRotate(0);

            //Then handle flip.
            this.side = side;
            Image face = side == Side.FRONT ? frontFace : backFace;

            ObservableList<KeyFrame> keyFrames = flipTimeline.getKeyFrames();
            keyFrames.clear();

            ((ImageView) flipButton.getGraphic()).setImage(this.side == Side.FRONT ? FLIP_FRONT : FLIP_BACK);
            imageView.setRotationAxis(Rotate.Y_AXIS);
            keyFrames.add(keyFrame(start, imageView.rotationAxisProperty(), Rotate.Y_AXIS));
            keyFrames.add(keyFrame(start, imageView.rotateProperty(), 0));
            keyFrames.add(keyFrame(half, imageView.imageProperty(), face));
            keyFrames.add(keyFrame(half, imageView.rotateProperty(), 90));
            keyFrames.add(keyFrame(end, imageView.rotateProperty(), 0));
            flipTimeline.playFromStart();
        }

        private <T> KeyFrame keyFrame(Duration duration, WritableValue<T> property, T value) {
            return new KeyFrame(duration, new KeyValue(property, value));
        }
    }
}
