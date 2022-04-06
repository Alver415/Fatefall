package com.alver.fatefall;

import com.alver.fatefall.fx.components.mainstage.MainStage;
import com.alver.fatefall.fx.components.settings.Settings;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class FatefallApplication extends Application {

    public static FatefallApplication INSTANCE;

    @Autowired
    private FxApplicationExceptionHandler applicationExceptionHandler;

    @Autowired
    private Settings settings;

    @Override
    public void init() throws IOException {
        INSTANCE = this;
    }

    @Override
    public void start(Stage stage) throws IOException {
        //Inject Spring dependencies.
        Main.APPLICATION_CONTEXT.getAutowireCapableBeanFactory().autowireBean(this);

        //Setup global FX Thread exception handling.
        Thread.currentThread().setUncaughtExceptionHandler(applicationExceptionHandler);

        //Disregard the original stage, create our own.
        stage = new MainStage();
        stage.getScene().getStylesheets().add(settings.getStylesheet());
        stage.setTitle("Fatefall 0.1 - Magic: the Gathering Card Designer Powered by the Scryfall API");
        stage.getIcons().add(new Image(Objects.requireNonNull(
                FatefallApplication.class.getResource("icon.png")).toExternalForm()));
        stage.show();
    }

    @Override
    public void stop() {
//        Platform.exit();
//        System.exit(0);
    }

    @Autowired
    private ApplicationConfiguration config;


}