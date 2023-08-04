package com.alver.fatefall.service;

import com.alver.fatefall.data.entity.CardFace;
import com.alver.fatefall.data.entity.CardFaceRow;
import com.alver.fatefall.data.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CardFaceService extends EntityService<CardFace, CardFaceRow> {

	@Autowired
	public CardFaceService(EntityRepository<CardFaceRow> repository) {
		super(repository);
	}
}
