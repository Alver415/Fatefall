import com.alver.fatefall.templatebuilder.app.TemplateBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class TemplateBuilderApplication extends Application{

    @Override
    public void start(Stage primaryStage) throws IOException {
        //Disregard the primaryStage.
        primaryStage.close();

        TemplateBuilder stage = new TemplateBuilder();
        stage.show();
        stage.centerOnScreen();
    }

    public static class Launcher {
        public static void main(String... args){
            launch(TemplateBuilderApplication.class, args);
        }
    }

}
