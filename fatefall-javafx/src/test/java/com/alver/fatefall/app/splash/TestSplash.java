package com.alver.fatefall.app.splash;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.concurrent.atomic.AtomicInteger;

public class TestSplash extends Preloader {

    private static final class Launcher{
        public static void main(String... args){
            Application.launch(TestSplash.class, args);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SplashPreloader.class.getResource("Splash.fxml"));
        Parent root = loader.load();
        SplashController controller = loader.getController();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

        Stage secondaryStage = new Stage();
        Button button = new Button("Increment");
        PreloaderBeanPostProcessor processor = new PreloaderBeanPostProcessor();
        controller.setPreloaderBeanPostProcessor(processor);
        AtomicInteger i = new AtomicInteger(0);
        button.setOnAction(a -> {
            Object bean = new Object();
            String beanName = "Bean" + i.getAndIncrement();
            processor.postProcessBeforeInitialization(bean, beanName);
            processor.postProcessAfterInitialization(bean, beanName);
        });
        Scene secondaryScene = new Scene(button);
        secondaryStage.setScene(secondaryScene);
        secondaryStage.show();
    }
}
