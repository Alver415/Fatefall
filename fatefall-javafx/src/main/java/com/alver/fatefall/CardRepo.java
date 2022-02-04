package com.alver.fatefall;

import com.scryfall.api.models.Card;
import org.hibernate.Session;

import java.util.List;
import java.util.UUID;

public class CardRepo extends DatabaseAccess {

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

    public List<Card> search(String name) {
        try (Session session = getSession()) {
            String hql = "FROM Card";
            return session.createQuery(hql, Card.class).list();
        } catch (Exception e) {
            throw e;
        }
    }
}
