package com.alver.fatefall.preloader;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class SplashController {

    private static final Logger log = LoggerFactory.getLogger(SplashController.class);

    @FXML
    private BorderPane root;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Text progressText;

    @FXML
    private Rectangle frontCard;
    @FXML
    private Rectangle backCard;

    private Timeline timeline;

    @FXML
    private void initialize() {
        progressText.textProperty().bind(progressBar.progressProperty().multiply(100).map("%.2f%%"::formatted));

        StackPane.setMargin(backCard, new Insets(20, 20, 0, 0));
        StackPane.setMargin(frontCard, new Insets(0, 0, 20, 20));

        Rotate frontRotation = new Rotate();
        frontRotation.pivotXProperty().bind(frontCard.widthProperty());
        frontRotation.pivotYProperty().bind(frontCard.heightProperty().multiply(1.5));
        frontCard.getTransforms().add(frontRotation);

        Rotate backRotation = new Rotate();
        backRotation.pivotXProperty().bind(backCard.widthProperty());
        backRotation.pivotYProperty().bind(backCard.heightProperty().multiply(1.5));
        backCard.getTransforms().add(backRotation);

        int translation = 20;
        Supplier<Rectangle> card = () -> (this.backCard.getParent().getChildrenUnmodifiable().get(0) == this.backCard) ? this.backCard : frontCard;

        timeline = new Timeline();
        timeline.setAutoReverse(true);
        timeline.setCycleCount(2);

        KeyFrame open = new KeyFrame(Duration.seconds(0),
                new KeyValue(backCard.translateXProperty(), 0),
                new KeyValue(backCard.translateYProperty(), 0),
                new KeyValue(backRotation.angleProperty(), 0),
                new KeyValue(frontCard.translateXProperty(), 0),
                new KeyValue(frontCard.translateYProperty(), 0),
                new KeyValue(frontRotation.angleProperty(), 0));
        KeyFrame close = new KeyFrame(Duration.seconds(0.2), a ->
                card.get().toFront(),
                new KeyValue(backCard.translateXProperty(), -translation * 2.5),
                new KeyValue(backCard.translateYProperty(), translation),
                new KeyValue(backRotation.angleProperty(), -10),
                new KeyValue(frontCard.translateXProperty(), translation * 2.5),
                new KeyValue(frontCard.translateYProperty(), -translation),
                new KeyValue(frontRotation.angleProperty(), 10));
        timeline.getKeyFrames().setAll(open, close);
    }

    public void setPreloaderBeanPostProcessor(PreloaderBeanPostProcessor processor) {
        processor.getObservableMap().addListener((MapChangeListener<? super String, ? super BeanState<?>>) change -> {
            Platform.runLater(() -> {
                if (change.wasAdded()) {
                    double progress = progressBar.getProgress();
                    progressBar.setProgress(progress + (1 - progress) / 50d);
                    if (timeline.getStatus() != Animation.Status.RUNNING) {
                        timeline.play();
                    }
                }
            });
        });
    }
}
