package com.alver.fatefall;

import com.alver.fatefall.api.client.FatefallApiClient;
import com.alver.fatefall.fx.components.mainstage.MainStage;
import com.alver.fatefall.fx.components.settings.Settings;
import com.alver.scryfall.api.ScryfallApiClient;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class FatefallApplication extends Application {

    public static FatefallApplication INSTANCE;
    public static final Image APP_ICON = new Image(Objects.requireNonNull(
            FatefallApplication.class.getResource("icon.png")).toExternalForm());

    private ApplicationContext applicationContext;
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

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
        //Start Spring application.
        applicationContext = SpringApplication.run(this.getClass());

        //Inject Spring dependencies.
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);

        //Initialize Settings after Spring setup is complete.
        settings.initFxml();

        //Setup global FX Thread exception handling.
        Thread.currentThread().setUncaughtExceptionHandler(applicationExceptionHandler);

        //Disregard the original stage, create our own.
        stage = new MainStage();
        stage.setTitle("Fatefall - Magic: the Gathering Card Designer Powered by the Scryfall API");
        stage.getScene().getStylesheets().add(settings.getSelectedStylesheet().getValue());
        stage.getIcons().add(APP_ICON);
        stage.show();
    }

    @Autowired
    private FatefallApplicationConfiguration config;

    @Bean
    public FatefallApiClient getFatefallApiClient() {
        String baseUrl = String.join(":", config.getHost(), Integer.toString(config.getPort()));
        return new FatefallApiClient(baseUrl);
    }

    @Bean
    public ScryfallApiClient getScryfallClient() {
        return new ScryfallApiClient();
    }


}