package com.alver.fatefall.app;

import com.alver.fatefall.app.configuration.ApplicationConfiguration;
import com.alver.fatefall.app.fx.components.mainstage.MainStage;
import com.alver.fatefall.app.fx.components.settings.Settings;
import com.alver.fatefall.app.services.AsyncService;
import com.alver.log.Log;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Objects;

@SpringBootApplication
@ComponentScan("com.alver.fatefall.app")
public class FatefallApplication extends Application {

    public static ApplicationContext APPLICATION_CONTEXT;

    @Autowired
    private FxApplicationExceptionHandler exceptionHandler;

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    @Autowired
    private Settings settings;

    @Override
    public void start(Stage primaryStage) {
        Stage splash = Splash.createAndShow();

        new Thread(() -> {
            initializeSpring();

            //Disregard the original stage, create our own.
            Platform.runLater(() -> {
                //Setup global FX Thread exception handling.
                Thread.currentThread().setUncaughtExceptionHandler(exceptionHandler);

                Stage mainStage = new MainStage();
                mainStage.getScene().getStylesheets().add(settings.getStylesheet());
                mainStage.setTitle(applicationConfiguration.getTitle());
                mainStage.getIcons().add(new Image(Objects.requireNonNull(
                        FatefallApplication.class.getResource("icon.png")).toExternalForm()));
                mainStage.show();
                splash.close();
            });
        }).start();
    }

    @Log(Level.WARN)
    private void initializeSpring() {
        //Start Spring Application and get the ApplicationContext
        APPLICATION_CONTEXT = SpringApplication.run(FatefallApplication.class);

        //Inject Spring dependencies.
        APPLICATION_CONTEXT.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() {
        //Wait 2.5s then force stop application if it's not already stopped.
        APPLICATION_CONTEXT.getBean(AsyncService.class).runAsync(() -> {
            LoggerFactory.getLogger(this.getClass()).error("FORCE EXIT");
            Platform.exit();
            System.exit(-1);
        }, 2500);
    }

}