import com.alver.fatefall.templatebuilder.app.TemplateBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class TemplateBuilderApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL fxml = TemplateBuilder.class.getResource("TemplateBuilder.fxml");
        FXMLLoader loader = new FXMLLoader(fxml);
        loader.setRoot(primaryStage);
        Stage stage = loader.load();
        stage.show();
        stage.setMaximized(true);
    }

    public static class Launcher {
        public static void main(String... args) {
            launch(TemplateBuilderApplication.class, args);
        }
    }

}
