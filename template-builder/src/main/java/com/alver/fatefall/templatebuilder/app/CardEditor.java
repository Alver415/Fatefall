package com.alver.fatefall.templatebuilder.app;

import com.alver.fatefall.templatebuilder.components.block.Card;
import com.alver.fatefall.templatebuilder.components.block.FXMLLoadable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.net.URL;

public class CardEditor extends BorderPane implements FXMLLoadable {
    private static final URL FXML = FXMLLoadable.fxmlResource(CardEditor.class);


    public CardEditor() {
        load(getClass(), FXML);

        setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        setOnKeyPressed(e -> {
            if (e.isControlDown() && e.getCode().equals(KeyCode.O)) {
                toggleShowOutlines();
            } else if (e.isControlDown() && e.getCode().equals(KeyCode.P)) {
                screenshot();
            }
        });
        cardProperty().addListener((observable, oldValue, newValue) -> {
            setCenter(newValue);
        });

        showOutlinesProperty().addListener(observable -> {
            if (showOutlinesProperty().get()) {
                getStyleClass().add("outlined");
            } else {
                getStyleClass().removeAll("outlined");
            }
        });

    }

    private void toggleShowOutlines() {
        setShowOutlines(!getShowOutlines());
    }

    private void screenshot() {
        boolean before = getShowOutlines();
        setShowOutlines(false);
        WritableImage snapshot = snapshot(new SnapshotParameters(), null);
        setShowOutlines(before);
        Scene scene = new Scene(new BorderPane(new ImageView(snapshot)));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public BooleanProperty showOutlines = new SimpleBooleanProperty(this, "showOutlines", true);

    public BooleanProperty showOutlinesProperty() {
        return showOutlines;
    }

    public boolean getShowOutlines() {
        return showOutlinesProperty().get();
    }

    public void setShowOutlines(boolean showOutlines) {
        showOutlinesProperty().set(showOutlines);
    }

    protected ObjectProperty<Card> card = new SimpleObjectProperty<>(this, "card", null);

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
