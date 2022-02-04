package com.alver.fatefall.fx.components;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public interface Controller {

    default void fxmlLoad(String fxml){
        URL resource = this.getClass().getResource(fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    default void runAsync(Runnable runnable) {
        new Thread(runnable).start();
    }

    default void runFx(Runnable runnable) {
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        } else {
            Platform.runLater(runnable);
        }
    }
}
