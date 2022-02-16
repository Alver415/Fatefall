package com.alver.fatefall.repositories.models;

import com.scryfall.api.PersistedObject;
import com.scryfall.api.models.Card;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class CardCollection extends PersistedObject {

    protected String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    protected List<Card> cards = new ArrayList<>();

    @Transient
    protected ObservableList<Card> observableCards;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservableList<Card> getCards() {
        if (observableCards == null) {
            observableCards = FXCollections.observableList(this.cards);
        }
        return observableCards;
    }

    public void addCards(List<Card> cards) {
        this.observableCards.addAll(cards);
    }
    public void addCards(Card... cards) {
        addCards(Arrays.asList(cards));
    }

    public void removeCards(List<Card> cards) {
        this.observableCards.removeAll(cards);
    }
    public void removeCards(Card... cards) {
        removeCards(Arrays.asList(cards));
    }
}
