package com.alver.fatefall.preloader;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SplashController {

    private static final Logger log = LoggerFactory.getLogger(SplashController.class);

    private PreloaderBeanPostProcessor processor;

    @FXML
    private Stage stage;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Text progressText;

    private final DoubleProperty progressProperty = new SimpleDoubleProperty();
    private final StringProperty progressPercentProperty = new SimpleStringProperty();
    private final StringProperty titleProperty = new SimpleStringProperty("Fatefall");

    @FXML
    private void initialize() {
        progressPercentProperty.bind(progressProperty.multiply(100).map("%.2f%%"::formatted));
        titleProperty.bind(Bindings.createStringBinding(() -> "Fatefall - " + progressPercentProperty.get(), progressPercentProperty));

        stage.titleProperty().bind(titleProperty);
        progressText.textProperty().bind(progressPercentProperty);
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
        stage.getScene().getRoot().setOpacity(0.5);
        if (processor != null) {
            processor.requestExit();
        } else {
            System.exit(0);
        }
    }


}
