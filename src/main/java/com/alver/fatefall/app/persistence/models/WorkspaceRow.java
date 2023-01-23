package com.alver.fatefall.app.persistence.models;


import com.alver.fatefall.api.models.Workspace;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workspace")
public class WorkspaceRow extends AbstractRow<Workspace> {

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CardRow> cards = new ArrayList<>();

    public List<CardRow> getCards() {
        return cards;
    }

    public void setCards(List<CardRow> cards) {
        this.cards = cards;
    }
}
