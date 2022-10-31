package com.alver.fatefall.app.fx.components.cardcollection;

import com.alver.fatefall.api.models.Card;

import java.util.List;

public class ScryfallSearchPane extends CardGridPane {

    public ScryfallSearchPane() {
        super();
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