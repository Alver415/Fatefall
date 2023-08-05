package com.alver.fatefall;

import com.alver.fatefall.app.fx.component.mainstage.ApplicationView;
import com.alver.fatefall.app.splash.ApplicationProgressListener;
import com.alver.fatefall.app.splash.SplashController;
import com.jpro.webapi.JProApplication;
import com.tangorabox.componentinspector.fx.FXComponentInspectorHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.function.Consumer;

public class FatefallFXApplication extends JProApplication implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Eager initialize UserAgentStylesheet so that it doesn't trigger later and undo user preferences.
        setUserAgentStylesheet(Application.STYLESHEET_MODENA);

        // Load and show Splash page with application loading progress.
        URL splashFxml = Objects.requireNonNull(SplashController.class.getResource("Splash.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(splashFxml);
        Parent root = loader.load();

        SplashController controller = loader.getController();
        controller.setApplicationProgressListener(applicationProgressListener);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

        // Execute the callback on another thread to start the Spring Application.
        new Thread(() -> onStart.accept(this)).start();
    }

    @Override
    public void stop() {
        Platform.exit();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private static Consumer<FatefallFXApplication> onStart;

    public static void onStart(Consumer<FatefallFXApplication> onStart) {
        FatefallFXApplication.onStart = onStart;
    }

    private static ApplicationProgressListener applicationProgressListener;

    public static void setStartupObservable(ApplicationProgressListener applicationProgressListener) {
        FatefallFXApplication.applicationProgressListener = applicationProgressListener;
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