package com.alver.fatefall.api.controllers;

import com.alver.fatefall.api.repositories.CardRepository;
import com.alver.fatefall.api.services.CardService;
import com.alver.scryfall.api.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("card")
public class CardController {

    protected CardRepository cardRepository;
    protected CardService cardService;

    @Autowired
    public CardController(
            CardRepository cardRepository,
            CardService cardService) {
        this.cardRepository = cardRepository;
        this.cardService = cardService;
    }

    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public Card findById(@PathVariable Long id) {
        return cardRepository.findById(id).get();
    }

}
