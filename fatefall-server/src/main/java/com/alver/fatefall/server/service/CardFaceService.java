package com.alver.fatefall.server.service;

import com.alver.fatefall.core.entity.CardFace;
import com.alver.fatefall.server.repository.CardFaceRepository;
import com.alver.fatefall.server.model.CardFaceRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CardFaceService extends EntityService<CardFace, CardFaceRow> {

    @Autowired
    public CardFaceService(CardFaceRepository cardFaceRepository) {
        super(cardFaceRepository);
    }

}
