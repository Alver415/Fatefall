package com.alver.fatefall.fx.components.cardview;

import com.alver.fatefall.FxComponent;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.client.FatefallApiClient;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.api.models.Layouts;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

import static com.alver.fatefall.fx.components.cardview.CardDimensions.*;

public class CardView extends StackPane implements FxComponent {

    @Autowired
    private FatefallApiClient fatefallApiClient;

    public enum Side {FRONT, BACK}

    public enum Spin {UP, RIGHT, DOWN, LEFT}

    /* === Static Resources ===*/
    public static final Image CARD_BACK = new Image(Objects.requireNonNull(
            CardView.class.getResource("card_back.png")).toExternalForm());
    public static final Image CARD_BACK_INVERTED_GRAY = new Image(Objects.requireNonNull(
            CardView.class.getResource("card_back_inverted_gray.png")).toExternalForm());

    public static final Image FLIP_FRONT = new Image(Objects.requireNonNull(
            CardView.class.getResource("flip_front.png")).toExternalForm());
    public static final Image FLIP_BACK = new Image(Objects.requireNonNull(
            CardView.class.getResource("flip_back.png")).toExternalForm());

    /* === FXML Bindings ===*/
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

    /**
     * === Card Spin Property ==
     */
    protected ObjectProperty<Spin> spinProperty = new SimpleObjectProperty<>(Spin.UP);
    public final void setSpin(Spin value) {
        spinProperty().set(value);
    }
    public final Spin getSpin() {
        return spinProperty.get();
    }
    public final ObjectProperty<Spin> spinProperty() {
        return spinProperty;
    }

    /**
     * === Card Side Property ==
     */
    protected ObjectProperty<Side> sideProperty = new SimpleObjectProperty<>(Side.FRONT);
    public final void setSide(Side value) {
        sideProperty.set(value);
    }
    public final Side getSide() {
        return sideProperty.get();
    }
    public final ObjectProperty<Side> sideProperty() {
        return sideProperty;
    }

    /**
     * Front Face Image Property
     */
    protected ObjectProperty<Image> frontFaceProperty = new SimpleObjectProperty<>(CARD_BACK);
    public final void setFrontFace(Image value) {
        frontFaceProperty.set(value);
    }
    public final Image getFrontFace() {
        return frontFaceProperty.get();
    }
    public final ObjectProperty<Image> frontFaceProperty() {
        return frontFaceProperty;
    }

    /**
     * Back Face Image Property
     */
    protected ObjectProperty<Image> backFaceProperty = new SimpleObjectProperty<>(CARD_BACK);
    public final void setBackFace(Image value) {
        backFaceProperty.set(value);
    }
    public final Image getBackFace() {
        return backFaceProperty.get();
    }
    public final ObjectProperty<Image> backFaceProperty() {
        return backFaceProperty;
    }

    public CardView() {
        initFxml();
    }

    @FXML
    public void initialize() {
        setupClip();
        setPickOnBounds(true);
        imageView.imageProperty().bind(frontFaceProperty);

        //Whenever side changes, update the imageView binding.
        sideProperty().addListener((observable, oldValue, newValue) -> {
            boolean isFront = newValue == Side.FRONT;
            imageView.imageProperty().bind(
                    isFront ? frontFaceProperty : backFaceProperty);
            ((ImageView) flipButton.getGraphic()).imageProperty().set(
                    isFront ? FLIP_FRONT : FLIP_BACK);
        });

        //On right click, build and show context menu.
        setOnContextMenuRequested(e -> showContextMenu(e.getScreenX(), e.getScreenY()));

        //Whenever card property changes, update the images.
        cardProperty().addListener((observable, oldValue, newValue) -> {
            loadCardImages(newValue);
        });
    }

    //TODO: Figure out why the doubling is necessary. Math is off somehow...
    private static final double ARC_WIDTH_MULTIPLIER = 2 * CORNER_RADIUS / WIDTH;
    private static final double ARC_HEIGHT_MULTIPLIER = 2 * CORNER_RADIUS / HEIGHT;

    private void setupClip() {

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(imageView.fitWidthProperty());
        clip.heightProperty().bind(imageView.fitHeightProperty());
        clip.arcWidthProperty().bind(Bindings.multiply(imageView.fitWidthProperty(), ARC_WIDTH_MULTIPLIER));
        clip.arcHeightProperty().bind(Bindings.multiply(imageView.fitHeightProperty(), ARC_HEIGHT_MULTIPLIER));
        imageView.setClip(clip);
    }

    private void loadCardImages(Card card) {
        setFrontFace(CARD_BACK_INVERTED_GRAY);
        setBackFace(CARD_BACK_INVERTED_GRAY);

        if (card == null) {
            return;
        }

        progressIndicator.setVisible(true);
        runAsync(() -> {
            Image frontFaceImage;
            Image backFaceImage;
            switch (card.layout()) {
                case TRANSFORM, MODAL_DFC, DOUBLE_FACED_TOKEN, ART_SERIES, REVERSIBLE_CARD -> {
                    frontFaceImage = new Image(card.cardFaces().get(0).imageUris().png(), true);
                    backFaceImage = new Image(card.cardFaces().get(1).imageUris().png(), true);
                }
                default -> {
                    frontFaceImage = new Image(card.imageUris().png(), true);
                    backFaceImage = CARD_BACK;
                }
            }

            runFx(() -> {
                progressIndicator.progressProperty().bind(frontFaceImage.progressProperty());
                if (Objects.equals(frontFaceImage.getProgress(), 1.0)) {
                    setFrontFace(frontFaceImage);
                    setBackFace(backFaceImage);
                    setupControls();
                    progressIndicator.setVisible(false);
                } else {
                    frontFaceImage.progressProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue.equals(1.0)) {
                            setFrontFace(frontFaceImage);
                            setBackFace(backFaceImage);
                            setupControls();
                            progressIndicator.setVisible(false);
                        }
                    });
                }
            });
        });
    }

    private void setupControls() {
        Card card = getCard();
        boolean isDoubleFaced = Layouts.isDoubleFaced(card);
        boolean isRotated = Layouts.isRotated(card);

        flipButton.setVisible(isDoubleFaced);
        flipButton.setOnMouseClicked((event) -> flip());

        spinLeftButton.setVisible(isRotated);
        spinLeftButton.setOnMouseClicked((event) -> spinLeft());

        spinRightButton.setVisible(isRotated);
        spinRightButton.setOnMouseClicked((event) -> spinRight());

        setOnMouseEntered(e -> {
            flipButton.setVisible(true);
            spinLeftButton.setVisible(true);
            spinRightButton.setVisible(true);
        });
        setOnMouseExited(e -> {
            flipButton.setVisible(isDoubleFaced);
            spinLeftButton.setVisible(isRotated);
            spinRightButton.setVisible(isRotated);
        });
    }

    /**
     * Context Menu
     */
    public void showContextMenu(double x, double y) {
        List<CardCollection> cardCollections = fatefallApiClient.getCardCollectionApi().findAll();
        ContextMenu contextMenu = new ContextMenu();

        //Save
        MenuItem save = new MenuItem("Save");
        save.setOnAction(a -> fatefallApiClient.getCardApi().save(getCard()));
        contextMenu.getItems().add(save);

        //Remove
        MenuItem delete = new MenuItem("Remove");
        delete.setOnAction(a -> fatefallApiClient.getCardApi().delete(getCard().getPk()));
        contextMenu.getItems().add(delete);

        //Add to Collection
        Menu collectionsMenu = new Menu("Add to...");
        for (CardCollection cardCollection : cardCollections) {
            MenuItem item = new MenuItem(cardCollection.getName());
            item.setOnAction(a -> cardCollection.getCards().add(getCard()));
            collectionsMenu.getItems().add(item);
        }
        contextMenu.getItems().add(collectionsMenu);

        //Show the context menu.
        contextMenu.show(this, x, y);
    }

    /**
     * Card Flip/Spin Animation
     */
    private static final Duration end = Duration.seconds(0.2);
    private static final Duration half = end.divide(2);
    private static final Duration start = Duration.ZERO;

    private final Timeline flipTimeline = new Timeline();
    private final Timeline spinTimeline = new Timeline();

    private void spinLeft() {
        Spin[] spins = Spin.values();
        Spin nextSpin = spins[Math.floorMod(getSpin().ordinal() - 1, spins.length)];
        spin(-90);
        setSpin(nextSpin);
    }

    private void spinRight() {
        Spin[] spins = Spin.values();
        Spin nextSpin = spins[Math.floorMod(getSpin().ordinal() + 1, spins.length)];
        spin(90);
        setSpin(nextSpin);
    }

    private void spin(double amount) {
        double rotationStart = getSpin().ordinal() * 90;
        double rotationEnd = rotationStart + amount;

        ObservableList<KeyFrame> keyFrames = spinTimeline.getKeyFrames();
        keyFrames.clear();

        imageView.setRotationAxis(Rotate.Z_AXIS);
        keyFrames.add(new KeyFrame(start, new KeyValue(imageView.rotateProperty(), rotationStart)));
        keyFrames.add(new KeyFrame(end, new KeyValue(imageView.rotateProperty(), rotationEnd)));
        spinTimeline.setOnFinished((event) -> {
            imageView.setRotate(getSpin().ordinal() * 90);
        });
        spinTimeline.playFromStart();
    }

    private void flip() {
        flipTo(getSide() == Side.FRONT ? Side.BACK : Side.FRONT);
    }

    private void flipTo(Side side) {
        //First reset spin.
        setSpin(Spin.UP);
        imageView.setRotationAxis(Rotate.Z_AXIS);
        imageView.setRotate(0);

        //Then handle flip.
        ObservableList<KeyFrame> keyFrames = flipTimeline.getKeyFrames();
        keyFrames.clear();

        imageView.setRotationAxis(Rotate.Y_AXIS);
        keyFrames.addAll(
                new KeyFrame(start,
                        new KeyValue(imageView.rotationAxisProperty(), Rotate.Y_AXIS),
                        new KeyValue(imageView.rotateProperty(), 0)),
                new KeyFrame(half,
                        new KeyValue(imageView.rotateProperty(), 90),
                        new KeyValue(sideProperty(), side)),
                new KeyFrame(end,
                        new KeyValue(imageView.rotateProperty(), 0))
        );
        flipTimeline.playFromStart();
    }

}
