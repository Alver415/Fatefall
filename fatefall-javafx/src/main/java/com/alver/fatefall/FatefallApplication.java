package com.alver.fatefall;

import com.scryfall.api.implementation.ScryfallClientImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FatefallApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FatefallApplication.class.getResource("card-search.fxml"));
        fxmlLoader.setController(new CardSearchController(new ScryfallClientImpl(), new ImageRepository()));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("base.css")).toExternalForm());
        stage.setTitle("Fatefall - Magic: the Gathering Card Designer Powered by Scryfall API");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest((event) -> {
            Platform.exit();
            System.exit(0);
        });

    }
}