package com.alver.fatefall.server.service;

import com.alver.fatefall.core.entity.Card;
import com.alver.fatefall.server.repository.CardRepository;
import com.alver.fatefall.server.model.CardRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CardService extends EntityService<Card, CardRow> {

	@Autowired
	public CardService(CardRepository cardRepository) {
		super(cardRepository);
	}
}
