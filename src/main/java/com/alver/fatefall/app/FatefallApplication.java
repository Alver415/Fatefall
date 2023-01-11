package com.alver.fatefall.app;

import com.alver.fatefall.FatefallLauncher;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.app.fx.components.mainstage.ApplicationView;
import com.alver.fatefall.app.services.CardCollectionRepository;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Objects;

public class FatefallApplication extends Application {

    protected static final Image ICON = new Image(Objects.requireNonNull(FatefallApplication.class.getResourceAsStream("icon.png")));

    private ConfigurableApplicationContext context;

    @Autowired
    protected ApplicationView applicationView;

    @Value("${title}")
    private String title;

    @Override
    public void init() {
        ApplicationContextInitializer<GenericApplicationContext> initializer = ac -> {
            ac.registerBean(FatefallApplication.class, () -> FatefallApplication.this);
            ac.registerBean(HostServices.class, this::getHostServices);
            ac.registerBean(Parameters.class, this::getParameters);
        };

        this.context = new SpringApplicationBuilder()
                .sources(FatefallLauncher.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(applicationView);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(ICON);
        primaryStage.setTitle(title);
        primaryStage.show();
    }

    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }

}