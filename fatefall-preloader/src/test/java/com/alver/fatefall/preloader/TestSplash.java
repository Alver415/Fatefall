package com.alver.fatefall.preloader;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

        Stage secondaryStage = new Stage();
        SplashController controller = loader.getController();
        ComboBox<String> fontComboBox = new ComboBox<>();
        fontComboBox.getItems().addAll(Font.getFamilies());
        fontComboBox.getSelectionModel().selectFirst(); // Select the first font initially

        fontComboBox.setOnAction(event -> {
            String selectedFontName = fontComboBox.getValue();
            if (selectedFontName != null) {
                Font selectedFont = Font.font(selectedFontName, controller.title.getFont().getSize());
                controller.title.setFont(selectedFont);
            }
        });


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
        Scene secondaryScene = new Scene(new VBox(fontComboBox, button));
        secondaryStage.setScene(secondaryScene);
        secondaryStage.setX(0);
        secondaryStage.setY(0);
        secondaryStage.show();
    }
}
