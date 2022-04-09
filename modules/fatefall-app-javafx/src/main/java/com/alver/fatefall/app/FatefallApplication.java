package com.alver.fatefall.app;

import com.alver.fatefall.app.fx.components.mainstage.MainStage;
import com.alver.fatefall.app.fx.components.settings.Settings;
import com.alver.fatefall.app.services.AsyncService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Objects;

@SpringBootApplication
@ComponentScan("com.alver.fatefall")
public class FatefallApplication extends Application {

    public static ApplicationContext APPLICATION_CONTEXT;

    @Autowired
    private FxApplicationExceptionHandler exceptionHandler;

    @Autowired
    private Settings settings;

    @Override
    public void start(Stage primaryStage) {
        Stage splash = Splash.createAndShow();

        new Thread(() -> {
            //Start Spring Application and get the ApplicationContext
            APPLICATION_CONTEXT = SpringApplication.run(FatefallApplication.class);

            //Inject Spring dependencies.
            APPLICATION_CONTEXT.getAutowireCapableBeanFactory().autowireBean(this);

            //Setup global FX Thread exception handling.
            Thread.currentThread().setUncaughtExceptionHandler(exceptionHandler);

            //Disregard the original stage, create our own.
            Platform.runLater(() -> {
                Stage mainStage = new MainStage();
                mainStage.getScene().getStylesheets().add(settings.getStylesheet());
                mainStage.setTitle("Fatefall 0.1 - Magic: the Gathering Card Designer Powered by the Scryfall API");
                mainStage.getIcons().add(new Image(Objects.requireNonNull(
                        FatefallApplication.class.getResource("icon.png")).toExternalForm()));
                mainStage.show();
                splash.close();
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