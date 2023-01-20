package com.alver.fatefall.app.persistence.models;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "card_collection")
public class CardCollectionRow {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    @Lob
    private String data;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CardRow> cards;

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
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public List<CardRow> getCards() {
        return cards;
    }
    public void setCards(List<CardRow> cards) {
        this.cards = cards;
    }
}
