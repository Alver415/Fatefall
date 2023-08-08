package com.alver.fatefall.preloader;

import javafx.application.Platform;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
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
    @FXML
    public Label title;

    @FXML
    private void initialize() {
        progressText.textProperty().bind(progressBar.progressProperty().multiply(100).map("%.2f%%"::formatted));
    }

    public void setPreloaderBeanPostProcessor(PreloaderBeanPostProcessor processor) {
        this.processor = processor;
        processor.getObservableBeanStates().addListener((MapChangeListener<? super String, ? super BeanState<?>>) change -> {
            Platform.runLater(() -> {
                if (change.wasAdded()) {
                    double progress = progressBar.getProgress();
                    progressBar.setProgress(progress + (1 - progress) / 50d);
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
