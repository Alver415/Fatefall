package com.alver.fatefall.database;

import com.scryfall.api.PersistedObject;
import com.scryfall.api.models.Card;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class CardCollection extends PersistedObject {

    protected String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    protected List<Card> cards = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }
    public void addCards(Card... cards) {
        addCards(Arrays.asList(cards));
    }

    public void removeCards(List<Card> cards) {
        this.cards.removeAll(cards);
    }
    public void removeCards(Card... cards) {
        removeCards(Arrays.asList(cards));
    }
}
