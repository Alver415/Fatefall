package com.alver.fatefall.api.controllers;

import com.alver.fatefall.api.repositories.CardCollectionRepository;
import com.alver.fatefall.api.services.CardCollectionService;
import com.alver.fatefall.api.models.CardCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("card_collection")
public class CardCollectionController {

    protected CardCollectionRepository cardCollectionRepository;
    protected CardCollectionService cardCollectionService;

    @Autowired
    public CardCollectionController(
            CardCollectionRepository cardCollectionRepository,
            CardCollectionService cardCollectionService) {
        this.cardCollectionRepository = cardCollectionRepository;
        this.cardCollectionService = cardCollectionService;
    }

    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<CardCollection> findAll() {
        return cardCollectionRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public CardCollection findById(@PathVariable Long id) {
        return cardCollectionRepository.findById(id).get();
    }

}
