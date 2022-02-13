package com.alver.fatefall.repositories;

import com.alver.fatefall.database.CardCollection;
import com.alver.fatefall.database.DatabaseManager;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.synchronizedObservableList;

@Repository
public class CardCollectionRepository {

    protected DatabaseManager databaseManager;

    protected ObservableList<CardCollection> cardCollections;

    @Autowired
    public CardCollectionRepository(DatabaseManager databaseManager) {
        this.cardCollections = synchronizedObservableList(observableList(databaseManager.loadAll(CardCollection.class)));
    }

    public ObservableList<CardCollection> getCardCollections() {
        return cardCollections;
    }

}
