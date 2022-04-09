package com.alver.fatefall.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Splash {

    public static Stage createAndShow() {
        try {
            URL fxml = Objects.requireNonNull(Splash.class.getResource("splash.fxml"));
            Scene scene = FXMLLoader.load(fxml);
            Stage stage = new Stage(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            return stage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
