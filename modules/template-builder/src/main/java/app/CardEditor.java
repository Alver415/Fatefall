package app;

import components.BaseComponent;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CardEditor extends AnchorPane {

    private static final Path PATH = Path.of("src/main/resources/app/card.fxml");

    public final Map<ReadOnlyStringProperty, StringProperty> data;

    public CardEditor() {
        this.data = new ConcurrentHashMap<>();
        try {
            FXMLLoader loader = new FXMLLoader(PATH.toUri().toURL());
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.getStylesheets().add("app/component.css");
    }

    public void toggleBoxes(){
        if (!this.getStylesheets().contains("app/boxes.css")) {
            this.getStylesheets().add("app/boxes.css");
        } else {
            this.getStylesheets().remove("app/boxes.css");
        }
    }

    @FXML
    public void initialize() {
        List<Node> childrenRecursive = getChildrenRecursive(this);
        for (Node node : childrenRecursive) {
            if (node instanceof BaseComponent component) {
                component.card.setValue(this);
            }
        }
    }

    private static List<Node> getChildrenRecursive(Parent parent) {
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
