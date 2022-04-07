package com.alver.fatefall;

import com.alver.fatefall.fx.components.mainstage.MainStage;
import com.alver.fatefall.fx.components.settings.Settings;
import com.alver.fatefall.services.AsyncService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class FatefallApplication extends Application {

    public static ApplicationContext APPLICATION_CONTEXT;

    @Autowired
    private FxApplicationExceptionHandler applicationExceptionHandler;

    @Autowired
    private Settings settings;

    @Override
    public void start(Stage startStage) {
        startStage.setScene(new Scene(new Label("LOADING")));
        startStage.setResizable(false);
        startStage.initStyle(StageStyle.UNDECORATED);
        startStage.show();

        new Thread(() -> {
            //Start Spring Application and get the ApplicationContext
            APPLICATION_CONTEXT = SpringApplication.run(FatefallApplication.class);

            //Inject Spring dependencies.
            APPLICATION_CONTEXT.getAutowireCapableBeanFactory().autowireBean(this);

            //Setup global FX Thread exception handling.
            Thread.currentThread().setUncaughtExceptionHandler(applicationExceptionHandler);

            //Disregard the original stage, create our own.
            Platform.runLater(() -> {
                Stage mainStage = new MainStage();
                mainStage.getScene().getStylesheets().add(settings.getStylesheet());
                mainStage.setTitle("Fatefall 0.1 - Magic: the Gathering Card Designer Powered by the Scryfall API");
                mainStage.getIcons().add(new Image(Objects.requireNonNull(
                        FatefallApplication.class.getResource("icon.png")).toExternalForm()));
                mainStage.show();
                startStage.close();
            });
        }).start();
    }

    @Override
    public void stop() {
        //Wait 2.5s then force stop application if it's not already stopped.
        APPLICATION_CONTEXT.getBean(AsyncService.class).runAsync(() -> {
            System.err.println("FORCE STOP");
            Platform.exit();
            System.exit(-1);
        }, 2500);
    }

}