package com.alver.fatefall.controller.entity;

import com.alver.fatefall.data.entity.CardFace;
import com.alver.fatefall.data.entity.CardFaceRow;
import com.alver.fatefall.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card_face")
public class CardFaceRestController extends EntityRestController<CardFace<?>, CardFaceRow> {

	@Autowired
	public CardFaceRestController(EntityService<CardFace<?>, CardFaceRow> service) {
		super(service);
	}

}
