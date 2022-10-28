package app;

import component.Component;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardEditor extends AnchorPane {

    private static final Path PATH = Path.of("src/main/resources/app/card.fxml");

    public CardEditor() {
        try {
            FXMLLoader loader = new FXMLLoader(PATH.toUri().toURL());
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setOnKeyPressed(e -> {
            if (e.isControlDown() && e.getCode().equals(KeyCode.P)) {
                WritableImage snapshot = this.snapshot(new SnapshotParameters(), null);
                Scene scene = new Scene(new Pane(new ImageView(snapshot)));
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        });
    }

    public void toggleBoxes() {
        if (!this.getStylesheets().contains("app/boxes.css")) {
            this.getStylesheets().add("app/boxes.css");
        } else {
            this.getStylesheets().remove("app/boxes.css");
        }
    }

    public List<Component> getComponents() {
        return getChildrenRecursive(this).stream()
                .filter(Component.class::isInstance)
                .map(node -> (Component) node)
                .toList();
    }

    public Map<String, Component> getComponentsMap() {
        return getChildrenRecursive(this).stream()
                .filter(Component.class::isInstance)
                .map(node -> (Component)node)
                .collect(Collectors.toMap(Component::getId, v -> v));
    }

    public static List<Node> getChildrenRecursive(Parent parent) {
        List<Node> nodes = new ArrayList<>();
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent child) {
                nodes.addAll(getChildrenRecursive(child));
            }
        }
        return nodes;
    }

}
