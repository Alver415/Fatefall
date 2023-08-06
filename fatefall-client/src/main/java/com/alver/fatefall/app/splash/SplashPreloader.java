package com.alver.fatefall.app.splash;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Objects;

public class SplashPreloader extends Preloader {

    private Stage stage;
    private SplashController controller;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        URL splashFxml = Objects.requireNonNull(SplashController.class.getResource("Splash.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(splashFxml);
        Parent root = loader.load();

        controller = loader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (StateChangeNotification.Type.BEFORE_START == evt.getType()) {
            stage.hide();
        }
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification preloaderNotification) {
        if (preloaderNotification instanceof PreloaderBeanPostProcessor processor) {
            controller.setPreloaderBeanPostProcessor(processor);
        }
    }
}
