package com.alver.fatefall;

import com.alver.fatefall.app.fx.component.mainstage.ApplicationView;
import com.jpro.webapi.JProApplication;
import com.tangorabox.componentinspector.fx.FXComponentInspectorHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.EventListener;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FatefallFXApplication extends JProApplication implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private static final CompletableFuture<FatefallFXApplication> INSTANCE = new CompletableFuture<>();

    public static FatefallFXApplication waitForInstance() {
        try {
            return INSTANCE.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() {
        INSTANCE.complete(this);
    }

    @Override
    public void start(Stage primaryStage) {
        // Eager initialize UserAgentStylesheet so that it doesn't trigger later and undo user preferences.
        setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }


    @Override
    public void stop() {
        Platform.exit();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startup(ApplicationReadyEvent event) {
        Platform.runLater(() -> {
            Stage primaryStage = new Stage();
            Scene scene = new Scene(applicationContext.getBean(ApplicationView.class));
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(e -> stop());
            primaryStage.show();

            FXComponentInspectorHandler.handleAll();
        });
    }
}