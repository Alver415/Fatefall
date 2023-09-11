package com.alver.fatefall.app.fx.view.entity.card.skin.flippable;

import com.alver.fatefall.app.fx.component.settings.FatefallProperties;
import com.alver.fatefall.app.fx.view.entity.card.CardView;
import com.alver.fatefall.app.fx.view.entity.card.skin.AbstractCardViewSkin;
import com.alver.fatefall.utils.ResourceUtil;
import javafx.animation.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class FlippableSkin extends AbstractCardViewSkin {

    private static final Image SPIN_LEFT = ResourceUtil.image("spinLeft.png");
    private static final Image FLIP_OVER = ResourceUtil.image("flipOver.png");
    private static final Image SPIN_RIGHT = ResourceUtil.image("spinRight.png");

    public enum Side {FRONT, BACK}

    public enum Spin {UP, RIGHT, DOWN, LEFT}

    protected StackPane wrapper;
    protected HBox buttons;

    public FlippableSkin(CardView control, FatefallProperties properties) {
        super(control);
        wrapper = new StackPane();
        wrapper.scaleXProperty().bind(properties.getCardViewScale());
        wrapper.scaleYProperty().bind(properties.getCardViewScale());

        wrapper.setMaxWidth(0);
        wrapper.setMaxHeight(0);
        wrapper.getChildren().setAll(
                front,
                back);

        buttons = new HBox();
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        StackPane.setAlignment(buttons, Pos.BOTTOM_CENTER);

        //Initial state
        buttons.setOpacity(0);
        front.setVisible(true);
        back.setVisible(false);

        buttons.getChildren().setAll(
                buildButton(SPIN_LEFT, this::spinLeft),
                buildButton(FLIP_OVER, this::flip),
                buildButton(SPIN_RIGHT, this::spinRight));

        control.setOnMouseEntered(e -> animateButtons(true));
        control.setOnMouseExited(e -> animateButtons(false));

        getChildren().setAll(new Group(wrapper), buttons);
    }

    private Button buildButton(Image image, Runnable runnable) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);

        Button button = new Button();
        button.setOpacity(0.5);
        button.setGraphic(imageView);
        button.setOnAction(a -> runnable.run());
        return button;
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
    protected ObjectProperty<Side> sideProperty = new SimpleObjectProperty<>(Side.FRONT) {
        {
            addListener((observable, oldValue, newValue) -> {
                front.setVisible(newValue == Side.FRONT);
                back.setVisible(newValue == Side.BACK);
            });
        }
    };

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
     * Card Flip/Spin Animation
     */
    private static final Duration end = Duration.seconds(0.2);
    private static final Duration half = end.divide(2);
    private static final Duration start = Duration.ZERO;

    private final Timeline flipTimeline = new Timeline();
    private final Timeline spinTimeline = new Timeline();

    @FXML
    public void spinLeft() {
        Spin[] spins = Spin.values();
        Spin nextSpin = spins[Math.floorMod(getSpin().ordinal() - 1, spins.length)];
        spin(-90);
        setSpin(nextSpin);
    }

    @FXML
    public void spinRight() {
        Spin[] spins = Spin.values();
        Spin nextSpin = spins[Math.floorMod(getSpin().ordinal() + 1, spins.length)];
        spin(90);
        setSpin(nextSpin);
    }

    public void spin(double amount) {
        wrapper.setRotationAxis(Rotate.Z_AXIS);
        double rotationStart = getSpin().ordinal() * 90;
        double rotationEnd = rotationStart + amount;

        spinTimeline.getKeyFrames().setAll(
                new KeyFrame(start, new KeyValue(wrapper.rotateProperty(), rotationStart)),
                new KeyFrame(end, new KeyValue(wrapper.rotateProperty(), rotationEnd)));
        spinTimeline.setOnFinished((event) -> wrapper.setRotate(getSpin().ordinal() * 90));
        spinTimeline.playFromStart();
    }

    @FXML
    public void flip() {
        flipTo(getSide() == Side.FRONT ? Side.BACK : Side.FRONT);
    }

    public void flipTo(Side side) {
        //First reset spin.
        setSpin(Spin.UP);
        wrapper.setRotationAxis(Rotate.Z_AXIS);
        wrapper.setRotate(0);

        //Then handle flip.
        wrapper.setRotationAxis(Rotate.Y_AXIS);
        flipTimeline.getKeyFrames().setAll(
                new KeyFrame(start,
                        new KeyValue(wrapper.rotationAxisProperty(), Rotate.Y_AXIS),
                        new KeyValue(wrapper.rotateProperty(), 0)),
                new KeyFrame(half,
                        new KeyValue(wrapper.rotateProperty(), 90),
                        new KeyValue(sideProperty(), side)),
                new KeyFrame(end,
                        new KeyValue(wrapper.rotateProperty(), 0))
        );
        flipTimeline.playFromStart();
    }

    protected void animateButtons(boolean show) {
        FadeTransition fade = new FadeTransition();
        fade.setToValue(show ? 1 : 0);

        TranslateTransition move = new TranslateTransition();
        move.setToY(show ? 0 : 20);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.setNode(buttons);
        parallelTransition.getChildren().setAll(fade, move);
        parallelTransition.setRate(2);
        parallelTransition.play();

    }

}
