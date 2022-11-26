package com.alver.fatefall.app.fx.components.cardview;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class CardFace extends AnchorPane {

    public CardFace(){
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);
        AnchorPane.setLeftAnchor(this, 0.0);
        parentProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof CardPane cardPane) {
                minWidthProperty().bind(cardPane.cardWidthProperty());
                minHeightProperty().bind(cardPane.cardHeightProperty());
                maxWidthProperty().bind(cardPane.cardWidthProperty());
                maxHeightProperty().bind(cardPane.cardHeightProperty());
                prefWidthProperty().bind(cardPane.cardWidthProperty());
                prefHeightProperty().bind(cardPane.cardHeightProperty());
            }
        });
    }

    public CardFace(Node... children){
        this();
        getChildren().addAll(children);
    }
}
