package com.alver.fatefall.api.models;


import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Workspace extends AbstractEntity{

    protected ListProperty<Card> cards = new SimpleListProperty<>(FXCollections.observableArrayList());
    public ObservableList<Card> getCards() {
        return cards.get();
    }
    public ListProperty<Card> cardsProperty() {
        return cards;
    }
    public void setCards(ObservableList<Card> cards) {
        this.cards.set(cards);
    }
}
