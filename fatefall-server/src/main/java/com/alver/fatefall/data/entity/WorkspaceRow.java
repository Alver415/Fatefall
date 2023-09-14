package com.alver.fatefall.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class WorkspaceRow extends EntityRow implements Workspace<CardRow, CardFaceRow, TemplateRow> {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected List<CardRow> cards = new ArrayList<>();

    @Override
    public List<CardRow> getCards() {
        return cards;
    }

    public void setCards(List<CardRow> cards) {
        this.cards = cards;
    }

    @Override
    public void addCards(CardRow... cards) {
        this.cards.addAll(List.of(cards));
    }

    @Override
    public void addCards(Collection<CardRow> cards) {
        this.cards.addAll(cards);
    }

    @Override
    public void removeCards(CardRow... cards) {
        this.cards.removeAll(List.of(cards));
    }

    @Override
    public void removeCards(Collection<CardRow> cards) {
        this.cards.removeAll(cards);
    }
}
