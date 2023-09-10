package com.alver.fatefall.app.fx.view.entity.card;

import com.alver.fatefall.app.fx.model.entity.CardFaceFX;
import com.alver.springfx.SpringFXLoader;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

@FXMLPrototype
public class CardFacePane extends AnchorPane {

    protected final ObjectProperty<CardFaceFX> cardFaceProperty = new SimpleObjectProperty<>();

    public ObjectProperty<CardFaceFX> cardFaceProperty() {
        return cardFaceProperty;
    }

    public CardFaceFX getCardFace() {
        return cardFaceProperty.get();
    }

    public void setCardFace(CardFaceFX cardFace) {
        cardFaceProperty.set(cardFace);
    }

    public CardFacePane() {
        cardFaceProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                getChildren().clear();
            } else {
                String fxmlUrl = newValue.getTemplate().getFxmlUrl();
                URL fxml = CardFacePane.class.getResource("EmptyTemplate.fxml");
                SpringFXLoader loader = new SpringFXLoader(fxml);
                loader.getNamespace().put("data.width", new SimpleObjectProperty<>(250));
                loader.getNamespace().put("data.height", new SimpleObjectProperty<>(350));
                try {
                    getChildren().setAll((Node) loader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
