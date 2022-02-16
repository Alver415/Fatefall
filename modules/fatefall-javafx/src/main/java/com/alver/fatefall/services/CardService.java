package com.alver.fatefall.services;

import com.alver.fatefall.repositories.models.CardCollection;
import com.alver.fatefall.repositories.CardRepository;
import com.scryfall.api.models.Card;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    protected CardCollectionService cardCollectionService;

    @Autowired
    protected CardRepository cardRepository;


    public ObservableList<CardCollection> getAllCardCollections(){
        return cardCollectionService.getAllCardCollections();
    }

    public void save(Card card){
        cardRepository.merge(card);
    }
    public void delete(Card card){
        cardRepository.delete(card);
    }
}
