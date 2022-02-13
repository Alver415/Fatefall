package com.alver.fatefall.services;

import com.alver.fatefall.database.CardCollection;
import com.alver.fatefall.repositories.CardCollectionRepository;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardCollectionService {

    @Autowired
    protected CardCollectionRepository repository;

    public ObservableList<CardCollection> getAllCardCollections(){
        return repository.getCardCollections();
    }

}
