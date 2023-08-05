package com.alver.fatefall.app.splash;

import javafx.application.Platform;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class SplashController {

    @FXML
    private ListView<String> listView;

    @FXML
    private ProgressBar progressBar;

    private final MapChangeListener<String, Long> progressListener = change -> Platform.runLater(() -> {
        listView.getItems().add("%s: %sms".formatted(change.getKey(), change.getValueAdded()));

        // TODO: Fix this.
        // We don't know how long its going to take, how many beans need to be loaded, so just increment the progress.
        double progress = progressBar.getProgress();
        double todo = 1 - progress;
        double less = 0.1 * todo;
        progressBar.setProgress(progress + less);
    });

    public void setApplicationProgressListener(ApplicationProgressListener applicationProgressListener) {
        applicationProgressListener.getObservableMap().addListener(progressListener);
    }


}
