package com.alver.fatefall.fx.components.cardview;

import com.alver.fatefall.FatefallApplication;
import com.alver.fatefall.database.CardCollection;
import com.alver.fatefall.database.DatabaseManager;
import com.alver.fatefall.database.ImageRepository;
import com.alver.fatefall.services.CardService;
import com.scryfall.api.models.Card;
import com.scryfall.api.models.Layouts;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.Objects;

import static com.alver.fatefall.ApplicationUtil.runFx;

public class CardView extends StackPane {
    public static final URL FXML = CardView.class.getResource("card-view.fxml");

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private DatabaseManager databaseManager;

    @Autowired
    private CardService cardService;

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

    public CardView(){
        FatefallApplication.load(FXML, this);
    }

    @FXML
    public void initialize() {
        super.setPickOnBounds(true);
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
        setOnContextMenuRequested(e -> {
            buildContextMenu().show(this, e.getScreenX(), e.getScreenY());
        });

        //Whenever card property changes, update the images.
        cardProperty().addListener((observable, oldValue, newValue) -> {
            loadCardImages(newValue);
        });
    }

    private void loadCardImages(Card card) {
        if (card == null) {
            setFrontFace(CARD_BACK_INVERTED_GRAY);
            setBackFace(CARD_BACK_INVERTED_GRAY);
            return;
        }

        runFx(() -> progressIndicator.setVisible(true));

        switch (card.layout()) {
            case TRANSFORM, MODAL_DFC, DOUBLE_FACED_TOKEN, ART_SERIES, REVERSIBLE_CARD -> {
                setFrontFace(imageRepository.get(card.cardFaces().get(0).imageUris().png(), true));
                setBackFace(imageRepository.get(card.cardFaces().get(1).imageUris().png(), true));
            }
            default -> {
                setFrontFace(imageRepository.get(card.imageUris().png(), true));
                setBackFace(CARD_BACK);
            }
        }

        runFx(() -> {
            progressIndicator.progressProperty().bind(getFrontFace().progressProperty());
            if (Objects.equals(frontFaceProperty.get().getProgress(), 1.0)) {
                onImageLoaded();
            } else {
                getFrontFace().progressProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue.equals(1.0)) {
                        onImageLoaded();
                    }
                });
            }
        });
    }

    private void onImageLoaded() {
        setupControls();
        progressIndicator.setVisible(false);
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

        this.setOnMouseEntered(e -> {
            flipButton.setVisible(true);
            spinLeftButton.setVisible(true);
            spinRightButton.setVisible(true);
        });
        this.setOnMouseExited(e -> {
            flipButton.setVisible(isDoubleFaced);
            spinLeftButton.setVisible(isRotated);
            spinRightButton.setVisible(isRotated);
        });
    }

    /**
     * Context Menu
     */

    public ContextMenu buildContextMenu(){
        List<CardCollection> cardCollections = cardService.getAllCardCollections();
        ContextMenu contextMenu = new ContextMenu();
        for (CardCollection cardCollection : cardCollections) {
            MenuItem item = new MenuItem(cardCollection.getName());
            item.setOnAction(a -> cardCollection.addCards(getCard()));
            contextMenu.getItems().add(item);
        }
        return contextMenu;
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
