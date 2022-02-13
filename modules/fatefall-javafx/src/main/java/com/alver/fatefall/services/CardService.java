package com.alver.fatefall.services;

import com.alver.fatefall.database.CardCollection;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    protected CardCollectionService cardCollectionService;

    public ObservableList<CardCollection> getAllCardCollections(){
        return cardCollectionService.getAllCardCollections();
    }
}
