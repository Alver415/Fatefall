package com.alver.fatefall.repositories;

import com.alver.fatefall.repositories.models.CardCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Repository;

import static javafx.collections.FXCollections.observableList;

@Repository
public class CardCollectionRepository extends EntityRepository<CardCollection>{

    protected ObservableList<CardCollection> cardCollections;

    public CardCollectionRepository(){
        super(CardCollection.class);
    }

    public ObservableList<CardCollection> getCardCollections() {
        if (cardCollections == null) {
            cardCollections = FXCollections.observableList(getAll());
        }
        return cardCollections;
    }

}
