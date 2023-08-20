package com.alver.fatefall.preloader;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class SplashPreloader extends Preloader {
    private static final URL FXML = SplashController.class.getResource("Splash.fxml");

    private Stage stage;
    private SplashController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(FXML);
        stage = loader.load();
        controller = loader.getController();
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
