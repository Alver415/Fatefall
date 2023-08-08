package com.alver.fatefall.preloader;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SplashController {

    private static final Logger log = LoggerFactory.getLogger(SplashController.class);

    private PreloaderBeanPostProcessor processor;

    @FXML
    private BorderPane root;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Text progressText;

    private final DoubleProperty progressProperty = new SimpleDoubleProperty(0);

    @FXML
    private void initialize() {
        progressText.textProperty().bind(progressBar.progressProperty().multiply(100).map("%.2f%%"::formatted));
    }

    public void setPreloaderBeanPostProcessor(PreloaderBeanPostProcessor processor) {
        this.processor = processor;
        processor.getObservableBeanStates().addListener((MapChangeListener<? super String, ? super BeanState<?>>) change -> {
            Platform.runLater(() -> {
                if (change.wasAdded()) {
                    double progress = progressProperty.get();
                    double newProgress = progress + (1 - progress) / 50d;
                    progressProperty.set(newProgress);
                    new Timeline(new KeyFrame(Duration.seconds(0.5),
                                    new KeyValue(progressBar.progressProperty(), newProgress, Interpolator.EASE_OUT))
                    ).play();
                }
            });
        });
    }

    @FXML
    public void requestExit() {
        log.info("User requested exit.");
        root.setOpacity(0.5);
        processor.requestExit();
    }
}
