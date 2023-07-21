package com.alver.fatefall.controller.entity;

import com.alver.fatefall.data.entity.Card;
import com.alver.fatefall.data.entity.Workspace;
import com.alver.fatefall.service.CardService;
import com.alver.fatefall.service.EntityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/card")
public class CardController extends EntityController<Card> {

	@Autowired
	public CardController(EntityService<Card> card) {
		super(card);
	}
}
