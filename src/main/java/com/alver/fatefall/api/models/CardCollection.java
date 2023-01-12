package com.alver.fatefall.api.models;


import jakarta.persistence.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "card_collection")
public class CardCollection {

    protected String id;
    protected String name;
    protected ObservableList<Card> cards = FXCollections.observableArrayList();
    protected String data;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Card> getCards() {
        return cards;
    }

    @Transient
    public ObservableList<Card> getObservableCards() {
        return cards;
    }

    private void setCards(List<Card> cards) {
        this.cards = FXCollections.observableArrayList(cards);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
