package com.alver.fatefall.api.services;

import com.alver.fatefall.api.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    protected CardRepository cardRepository;

}
