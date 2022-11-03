import com.alver.fatefall.templatebuilder.components.block.Card;
import com.alver.fatefall.templatebuilder.components.builders.CardBuilder;
import com.alver.fatefall.templatebuilder.components.builders.CardFaceBuilder;
import com.alver.fatefall.templatebuilder.components.builders.TextBlockBuilder;
import com.alver.fatefall.templatebuilder.components.serialization.FXMLSaver;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FXMLSaverTest {
    @BeforeAll
    public static void setup() {
    }

    @Test
    public void test() {
        Card card = CardBuilder.builder().front(CardFaceBuilder.builder().addBlock(TextBlockBuilder.builder().text("test").build()).build()).build();

        Path path = Path.of("src/test/resources/test.fxml");
        File file = path.toFile();

        FXMLSaver.save(file, card);
    }

    @Test
    public void testGraalFX() throws InterruptedException {

        Platform.setImplicitExit(false);
        Platform.startup(() -> {
            try (Context context = Context.newBuilder()
                    .allowAllAccess(true)
                    .out(System.out)
                    .err(System.err)
                    .allowExperimentalOptions(true)
                    .build()) {
                BorderPane parent = new BorderPane();
                Button child = new Button("Click Me");
                child.setOnAction(e -> new Alert(Alert.AlertType.INFORMATION).show());
                context.getPolyglotBindings().putMember("parent", parent);
                context.getPolyglotBindings().putMember("child", child);

                context.eval("js", """                     
                        var Stage = Java.type('javafx.stage.Stage');
                        var Scene = Java.type('javafx.scene.Scene');

                        var Button = Java.type('javafx.scene.control.Button');

                        var parent = Polyglot.import('parent');
                        var child = Polyglot.import('child');
                            
                        var myButton = new Button("new button");
                        myButton.setOnAction(child.getOnAction());
                                            
                        parent.setCenter(myButton);

                        var stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                                            
                        """);
                assertTrue(parent.getCenter() == child);
            }

        });
        while (true) Thread.sleep(1000);
    }


    @Test
    public void testGraalFX2() throws InterruptedException {

        Platform.setImplicitExit(false);
        Platform.startup(() -> {
            try (Context context = Context.newBuilder()
                    .allowAllAccess(true)
                    .out(System.out)
                    .err(System.err)
                    .allowExperimentalOptions(true)
                    .build()) {
                BorderPane parent = new BorderPane();
                Button child = new Button("Click Me");
                child.setOnAction(e -> new Alert(Alert.AlertType.INFORMATION).show());
                context.getPolyglotBindings().putMember("parent", parent);
                context.getPolyglotBindings().putMember("child", child);

                Value value = context.eval("js", """                     
                        var v = {
                            id:"testId",
                            name:"card name"
                        };
                        v
                        """);
                for (String key : value.getMemberKeys()){
                    System.out.printf("%s:%s%n", key, value.getMember(key));
                }
            }

        });
        while (true) Thread.sleep(1000);
    }
}
