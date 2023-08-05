package com.alver.fatefall.app.splash;

import com.alver.fatefall.app.services.FXAsyncUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Objects;

public class SplashUtil {

    private static Stage stage;

    public static void showSplash(ApplicationProgressListener applicationProgressListener){
        FXAsyncUtils.runFx(() -> {
            // Load and show Splash page with application loading progress.
            URL splashFxml = Objects.requireNonNull(SplashController.class.getResource("Splash.fxml"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(splashFxml);
            Parent root = loader.load();

            SplashController controller = loader.getController();
            controller.setApplicationProgressListener(applicationProgressListener);

            stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        });
    }

    public static void hideSplash(){
        FXAsyncUtils.runFx(() -> stage.hide());
    }
}
