package com.alver.fatefall.service;

import com.alver.fatefall.data.entity.Card;
import com.alver.fatefall.data.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CardService extends EntityService<Card> {

	@Autowired
	public CardService(EntityRepository<Card> repository) {
		super(repository);
	}
}
