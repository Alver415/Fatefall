package com.alver.fatefall.templatebuilder.components.block;

import javafx.scene.layout.AnchorPane;

public class CardFace extends AnchorPane implements FXMLLoadable {

    public CardFace(){
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
