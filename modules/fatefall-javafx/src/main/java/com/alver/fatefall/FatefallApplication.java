package com.alver.fatefall;

import com.alver.fatefall.database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

@SpringBootApplication
public class FatefallApplication extends Application {

    private static FatefallApplication INSTANCE;
    public static final String DEFAULT_STYLE = Objects.requireNonNull(
            FatefallApplication.class.getResource("base.css")).toExternalForm();

    private ApplicationContext context;

    @Autowired
    private DatabaseManager databaseManager;

    @Autowired
    private FxApplicationErrorHandler applicationErrorHandler;

    @Override
    public void init() throws IOException {
        INSTANCE = this;

        //Start Spring
        context = SpringApplication.run(this.getClass());
    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            //Inject spring dependencies.
            autowire(this);

            //Setup global FX Thread exception handling.
            Thread.currentThread().setUncaughtExceptionHandler(applicationErrorHandler);

            //Initialize FXML
            FXMLLoader loader = new FXMLLoader(MainStageController.class.getResource("main-stage.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getRoot().getStylesheets().add(DEFAULT_STYLE);
            stage.setTitle("Fatefall - Magic: the Gathering Card Designer Powered by Scryfall API");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        databaseManager.stop();
    }

    public static Optional<String> textDialog(String title, String contentText){
        Dialog<String> dialog = new TextInputDialog();
        dialog.getDialogPane().getStylesheets().add(DEFAULT_STYLE);
        dialog.setTitle(title);
        dialog.setGraphic(null);
        dialog.setHeaderText(null);
        dialog.setContentText(contentText);
        return dialog.showAndWait();
    }

    /**
     * Static method to autowire spring beans from anywhere in the application.
     */
    public static void autowire(Object object) {
        INSTANCE.context.getAutowireCapableBeanFactory().autowireBean(object);
    }

    /**
     * Load and FXML root object with the given controller.
     * - If controller given, then it won't be spring injected.
     * - If a controller is not given, then it will use spring application context with dependency injection.
     */
    public static <T extends Node> T load(URL fxml, T node) {
        try {
            //Inject spring dependencies.
            autowire(node);

            //Initialize FXML
            FXMLLoader loader = new FXMLLoader(fxml);
            loader.setController(node);
            loader.setRoot(node);
            T root = loader.load();
            return root;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}