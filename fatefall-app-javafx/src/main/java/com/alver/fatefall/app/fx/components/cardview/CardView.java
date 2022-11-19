package com.alver.fatefall.app.fx.components.cardview;

import com.alver.fatefall.api.FatefallApi;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.app.fx.components.FxComponent;
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.alver.fatefall.app.fx.components.cardview.CardView.CardDimensions.ARC_HEIGHT_MULTIPLIER;
import static com.alver.fatefall.app.fx.components.cardview.CardView.CardDimensions.ARC_WIDTH_MULTIPLIER;

public class CardView extends StackPane implements FxComponent {

    @Autowired
    private FatefallApi fatefallApi;

    public enum Side {FRONT, BACK}

    public enum Spin {UP, RIGHT, DOWN, LEFT}

    /* === Static Resources ===*/
    public static final Image PLACEHOLDER = new Image(Objects.requireNonNull(
            CardView.class.getResource("placeholder.png")).toExternalForm());
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
    protected ObjectProperty<Image> frontFaceProperty = new SimpleObjectProperty<>(PLACEHOLDER);
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
    protected ObjectProperty<Image> backFaceProperty = new SimpleObjectProperty<>(PLACEHOLDER);
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
        setupControls();
        setPickOnBounds(true);
        imageView.imageProperty().bind(frontFaceProperty);
        cardProperty.addListener(l -> sideProperty.set(Side.FRONT));

        //Whenever side changes, update the imageView binding.
        sideProperty().addListener((observable, oldValue, newValue) -> {
            boolean isFront = newValue == Side.FRONT;
            ImageView flipButtonGraphic = (ImageView) flipButton.getGraphic();
            flipButtonGraphic.imageProperty().set(isFront ? FLIP_FRONT : FLIP_BACK);
            imageView.imageProperty().bind(isFront ? frontFaceProperty : backFaceProperty);
        });

        //On right click, build and show context menu.
        setOnContextMenuRequested(e -> showContextMenu(e.getScreenX(), e.getScreenY()));

        //Whenever card property changes, update the images.
        cardProperty().addListener((observable, oldValue, newValue) -> {
            loadCardImages(newValue);
        });
    }

    private void setupClip() {
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(imageView.fitWidthProperty());
        clip.heightProperty().bind(imageView.fitHeightProperty());
        clip.arcWidthProperty().bind(Bindings.multiply(imageView.fitWidthProperty(), ARC_WIDTH_MULTIPLIER));
        clip.arcHeightProperty().bind(Bindings.multiply(imageView.fitHeightProperty(), ARC_HEIGHT_MULTIPLIER));
        imageView.setClip(clip);
    }

    private void loadCardImages(Card card) {
        if (card == null) {
            setFrontFace(PLACEHOLDER);
            setBackFace(PLACEHOLDER);
            return;
        }

        progressIndicator.setVisible(true);
        runAsync(() -> {
            try {
                Image frontFaceImage = fatefallApi.getCardApi().getImage(card.getFrontFaceUrl());
                Image backFaceImage = fatefallApi.getCardApi().getImage(card.getBackFaceUrl());

                runFx(() -> {
                    progressIndicator.progressProperty().bind(frontFaceImage.progressProperty());
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
        if (frontFaceImage.getException() != null) {
            setFrontFace(PLACEHOLDER);
        } else {
            setFrontFace(frontFaceImage);
        }
        if (backFaceImage.getException() != null) {
            setBackFace(PLACEHOLDER);
        } else {
            setBackFace(backFaceImage);
        }
        progressIndicator.setVisible(false);
    }

    private void setupControls() {
        flipButton.setOnMouseClicked((event) -> flip());
        spinLeftButton.setOnMouseClicked((event) -> spinLeft());
        spinRightButton.setOnMouseClicked((event) -> spinRight());

        setOnMouseEntered(e -> {
            flipButton.setVisible(true);
            spinLeftButton.setVisible(true);
            spinRightButton.setVisible(true);
        });
        setOnMouseExited(e -> {
            flipButton.setVisible(false);
            spinLeftButton.setVisible(false);
            spinRightButton.setVisible(false);
        });
    }

    /**
     * Context Menu
     */
    private final ContextMenu contextMenu = new ContextMenu();
    public void showContextMenu(double x, double y) {
        if (getCard() == null) {
            return;
        }
        contextMenu.getItems().clear();

        //Save
        MenuItem save = new MenuItem("Save");
        save.setOnAction(a -> fatefallApi.getCardApi().save(getCard()));
        contextMenu.getItems().add(save);

        //Delete
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(a -> fatefallApi.getCardApi().delete(getCard().getPk()));
        contextMenu.getItems().add(delete);

        //Add to Collection
        Menu collectionsMenu = new Menu("Add to...");
        contextMenu.getItems().add(collectionsMenu);
        runAsync(() -> {
            List<CardCollection> cardCollections = fatefallApi.getCardCollectionApi().findAll();
            runFx(() -> {
                for (CardCollection cardCollection : cardCollections) {
                    MenuItem item = new MenuItem(cardCollection.getName());
                    item.setOnAction(a -> {
                        cardCollection.getCards().add(getCard());
                        fatefallApi.getCardCollectionApi().save(cardCollection);
                    });
                    collectionsMenu.getItems().add(item);
                }
            });
        });

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

    public interface CardDimensions {
        double WIDTH = 2.5;
        double HEIGHT = 3.5;
        double CORNER_RADIUS = 0.125;

        //TODO: Figure out why the 2.5x is necessary. Math is off somehow...
        double ARC_WIDTH_MULTIPLIER = 2.5 * CORNER_RADIUS / WIDTH;
        double ARC_HEIGHT_MULTIPLIER = 2.5 * CORNER_RADIUS / HEIGHT;
    }
}
