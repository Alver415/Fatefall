package com.alver.fatefall.services;

import com.alver.fatefall.repositories.models.CardCollection;
import com.alver.fatefall.repositories.CardCollectionRepository;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CardCollectionService {

    @Autowired
    protected CardCollectionRepository repository;

    public ObservableList<CardCollection> getAllCardCollections(){
        return repository.getCardCollections();
    }

    public CardCollection save(CardCollection cardCollection){
        return repository.save(cardCollection);
    }
    public List<CardCollection> saveAll(List<CardCollection> cardCollections){
        return repository.saveAll(cardCollections);
    }
    public void delete(CardCollection cardCollection){
        repository.delete(cardCollection);
    }


}
