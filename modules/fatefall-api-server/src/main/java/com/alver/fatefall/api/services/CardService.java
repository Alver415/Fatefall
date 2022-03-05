package com.alver.fatefall.api.services;

import com.alver.fatefall.api.repositories.CardRepository;
import com.alver.scryfall.api.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CardService {

    @Autowired
    protected CardRepository cardRepository;

}
