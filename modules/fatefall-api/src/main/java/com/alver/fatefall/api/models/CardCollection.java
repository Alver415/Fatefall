package com.alver.fatefall.api.models;


import com.alver.fatefall.api.PersistedObject;
import com.alver.fatefall.api.base.Card;
import com.alver.fatefall.api.base.implementation.ModifiableCard;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CardCollection extends PersistedObject {

    protected String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    protected List<ModifiableCard> cards = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModifiableCard> getCards() {
        return cards;
    }
}
