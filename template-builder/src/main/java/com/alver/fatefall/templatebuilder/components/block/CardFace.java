package com.alver.fatefall.templatebuilder.components.block;

import javafx.scene.layout.AnchorPane;

public class CardFace extends AnchorPane implements FXMLLoadable {

    public CardFace(){
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);
        AnchorPane.setLeftAnchor(this, 0.0);
        parentProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof Card card) {
                minWidthProperty().bind(card.cardWidth);
                minHeightProperty().bind(card.cardHeight);
                maxWidthProperty().bind(card.cardWidth);
                maxHeightProperty().bind(card.cardHeight);
                prefWidthProperty().bind(card.cardWidth);
                prefHeightProperty().bind(card.cardHeight);
            }
        });
    }
}
