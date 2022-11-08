import com.alver.fatefall.templatebuilder.app.TemplateBuilder;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.net.URL;

public class TemplateBuilderApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        URL fxml = TemplateBuilder.class.getResource("TemplateBuilder.fxml");
        FXMLLoader loader = new FXMLLoader(fxml);
        loader.setRoot(primaryStage);
        Stage stage = loader.load();
        stage.show();
    }

    public static class Launcher {
        public static void main(String... args) {
            launch(TemplateBuilderApplication.class, args);
        }
    }

}
