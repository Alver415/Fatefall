package app;

import app.tool.EditorToolPane;
import component.Card;
import component.CardEditor;
import component.FileField;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class TemplateBuilder extends Application {

    @FXML
    public FileField fxmlFileSelector;
    @FXML
    protected CardEditor editor;
    @FXML
    protected EditorToolPane editorToolPane;

    @FXML
    public void initialize() {
        fxmlFileSelector.setOnAction(a -> {
            reloadCard();
        });
//        editorToolPane.setTarget(cardEditor);
//        editorToolPane.setMaxHeight(600);
//
//        for (Node node : CardEditor.getChildrenRecursive(cardEditor)) {
//            if (node instanceof Component)
//                node.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
//                    editorToolPane.setTarget(node);
//                });
//        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            primaryStage.setTitle(TemplateBuilder.class.getSimpleName());

            URL fxml = TemplateBuilder.class.getResource("TemplateBuilder.fxml");
            FXMLLoader loader = new FXMLLoader(fxml);
            loader.setController(this);
            Parent root = loader.load();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
            primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
                if (e.isControlDown() && e.getCode().equals(KeyCode.R)) {
                    reloadCard();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void reloadCard() {
        try {
            URL fxml = fxmlFileSelector.getFile().toURI().toURL();
            FXMLLoader loader = new FXMLLoader(fxml);
            Card card = loader.load();
            editor.setCard(card);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}