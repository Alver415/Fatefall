package com.alver.fatefall.fx.core.model;

import com.alver.fatefall.core.entity.Workspace;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.Arrays;
import java.util.List;

public class WorkspaceFX extends EntityFX implements Workspace {

    private final SimpleListProperty<CardFX> cards = new SimpleListProperty<>(this, "cards", FXCollections.observableArrayList());

    public WorkspaceFX() {
        super();
    }

    public WorkspaceFX(Long id) {
        super(id);
    }

    public ListProperty<CardFX> cardsProperty() {
        return cards;
    }

    @Override
    public List<CardFX> getCards() {
        return cards;
    }

    public void addCards(CardFX... cards){
        addCards(Arrays.asList(cards));
    }
    public void addCards(List<CardFX> cards){
        this.cards.addAll(cards);
    }

}
