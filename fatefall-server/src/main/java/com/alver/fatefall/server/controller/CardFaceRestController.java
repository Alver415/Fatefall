package com.alver.fatefall.server.controller;

import com.alver.fatefall.core.api.RequestMappings;
import com.alver.fatefall.core.entity.CardFace;
import com.alver.fatefall.server.model.CardFaceRow;
import com.alver.fatefall.server.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RequestMappings.CARD_FACES)
public class CardFaceRestController extends EntityRestController<CardFace, CardFaceRow> {

	@Autowired
	public CardFaceRestController(EntityService<CardFace, CardFaceRow> service) {
		super(service);
	}

}
