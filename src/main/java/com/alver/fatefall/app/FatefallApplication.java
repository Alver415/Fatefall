package com.alver.fatefall.app;

import com.alver.fatefall.app.fx.components.mainstage.ApplicationView;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class FatefallApplication extends Application {

    private ConfigurableApplicationContext context;

    @Autowired
    protected ApplicationView applicationView;

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
        primaryStage.show();
    }

    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }

}