package component;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardEditor extends BorderPane implements FXMLLoadable {
    private static final URL FXML = FXMLLoadable.findFXML(CardEditor.class);

    @FXML
    protected BorderPane borderPane;

    public CardEditor() {
        load(getClass(), FXML);
        setOnKeyPressed(e -> {
            if (e.isControlDown() && e.getCode().equals(KeyCode.O)) {
                showOutlines.set(!showOutlines.get());
            } else if (e.isControlDown() && e.getCode().equals(KeyCode.P)) {
                boolean before = getShowOutlines();
                setShowOutlines(false);
                WritableImage snapshot = borderPane.getCenter().snapshot(new SnapshotParameters(), null);
                setShowOutlines(before);
                Scene scene = new Scene(new BorderPane(new ImageView(snapshot)));
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        });
    }


    public BooleanProperty showOutlines = new SimpleBooleanProperty(this, "showOutlines", true) {
        {
            getStyleClass().add("outlined");
            addListener(observable -> {
                if (showOutlinesProperty().get()) {
                    getStyleClass().add("outlined");
                } else {
                    getStyleClass().removeAll("outlined");
                }
            });
        }
    };

    public BooleanProperty showOutlinesProperty() {
        return showOutlines;
    }

    public boolean getShowOutlines() {
        return showOutlinesProperty().get();
    }

    public void setShowOutlines(boolean showOutlines) {
        showOutlinesProperty().set(showOutlines);
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
                .map(node -> (Component) node)
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

    protected ObjectProperty<Card> card = new SimpleObjectProperty<>(this, "card", null) {
        {
            addListener((observable, oldValue, newValue) -> {
                borderPane.setCenter(newValue);
            });
        }
    };

    public ObjectProperty<Card> cardProperty() {
        return card;
    }

    public Card getCard() {
        return card.get();
    }

    public void setCard(Card card) {
        this.card.set(card);
    }

}
