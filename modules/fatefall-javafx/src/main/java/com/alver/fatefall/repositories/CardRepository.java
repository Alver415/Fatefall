package com.alver.fatefall.repositories;

import com.scryfall.api.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CardRepository {

    @Autowired
    private DatabaseManager databaseManager;

    public Card get(UUID id) {
        return databaseManager.inSession((s) -> {
            return s.get(Card.class, id);
        });
    }

    public Card merge(Card card) {
        return databaseManager.inSession((s) -> {
            return (Card) s.merge(card);
        });
    }

    public void delete(Card card) {
        databaseManager.inSession((s) -> {
            s.delete(card);
        });
    }

}
