package com.alver.fatefall.fx.components.cardcollection;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.fx.components.cardview.CardView;
import com.alver.fatefall.api.models.scryfall.CardList;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ScryfallSearchPane extends CardGridPane {

    public ScryfallSearchPane() {
        //Override fxml loading to use CardCollectionPane's fxml.
        super(CardGridPane.class);
    }

    public void search(String query) {
        runAsync(() -> {
            List<Card> cards = scryfallClient.getCardApi().search(query);
            if (cards.isEmpty()) {
                throw new RuntimeException("No results");
            }
            runFx(() -> redraw(cards));
        });
    }

}