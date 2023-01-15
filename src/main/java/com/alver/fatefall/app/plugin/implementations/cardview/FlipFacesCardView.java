package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.app.fx.components.FXMLAutoLoad;
import javafx.animation.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@FXMLAutoLoad
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FlipFacesCardView extends AbstractCardView<FlipFacesCardView> {

    public enum Side {FRONT, BACK}

    public enum Spin {UP, RIGHT, DOWN, LEFT}

    @FXML
    private StackPane wrapper;
    @FXML
    private Node buttons;
    @FXML
    private void initialize(){
        buttons.setOpacity(0);
        setOnMouseEntered(e -> fade(buttons, true));
        setOnMouseExited(e -> fade(buttons, false));
    }

    private void fade(Node node, boolean direction){
        FadeTransition fade = new FadeTransition();
        fade.setToValue(direction ? 1 : 0);

        TranslateTransition move = new TranslateTransition();
        move.setToY(direction ? 0 : 20);

        ScaleTransition scale = new ScaleTransition();
        scale.setToX(direction ? 1 : 0);
        scale.setToY(direction ? 1 : 0);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.setNode(node);
        parallelTransition.getChildren().setAll(fade, move, scale);
        parallelTransition.setRate(2);
        parallelTransition.play();

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
                getFrontFace().setVisible(newValue == Side.FRONT);
                getBackFace().setVisible(newValue == Side.BACK);
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


}
