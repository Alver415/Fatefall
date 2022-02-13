package com.alver.fatefall;

import com.alver.fatefall.database.DatabaseManager;
import com.scryfall.api.models.Card;

import java.util.UUID;

public class CardRepository extends DatabaseManager {

    public void persist(Card card) {
        inSession((s) -> {
            s.persist(card);
        });
    }

    public Card merge(Card card) {
        return inSession((s) -> {
            return (Card) s.merge(card);
        });
    }

    public Card get(UUID id) {
        return inSession((s) -> {
            return s.get(Card.class, id);
        });
    }
}
