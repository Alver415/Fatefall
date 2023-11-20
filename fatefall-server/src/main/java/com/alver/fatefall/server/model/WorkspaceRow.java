package com.alver.fatefall.server.model;

import com.alver.fatefall.core.entity.Workspace;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class WorkspaceRow extends EntityRow implements Workspace {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected List<CardRow> cards = new ArrayList<>();

    @Override
    public List<CardRow> getCards() {
        return cards;
    }

    public void setCards(List<CardRow> cards) {
        this.cards = cards;
    }
}
