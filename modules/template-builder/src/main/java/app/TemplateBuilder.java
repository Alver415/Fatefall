package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class TemplateBuilder extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        List<Font> fonts = loadFonts(Path.of("fonts"));
        primaryStage.setTitle(TemplateBuilder.class.getSimpleName());
        Scene scene = loadScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene loadScene() {
        try {
            URL fxml = TemplateBuilder.class.getResource("TemplateBuilder.fxml");
            FXMLLoader loader = new FXMLLoader(fxml);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.isControlDown() && e.getCode() == KeyCode.R) {
                    loadScene();
                }
            });
            return scene;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Font> loadFonts(Path path) {
        try (Stream<Path> walk = Files.walk(path)) {
            return walk
                    .filter(p -> p.getFileName().toString().endsWith(".ttf"))
                    .map(TemplateBuilder::loadFont)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Font loadFont(Path p) {
        try {
            return Font.loadFont(p.toUri().toURL().toString(), 12);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}