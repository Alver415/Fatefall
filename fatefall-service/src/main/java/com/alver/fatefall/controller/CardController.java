package com.alver.fatefall.controller;

import com.alver.fatefall.api.Card;
import com.alver.fatefall.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

	private final CardService cardService;

	@Autowired
	public CardController(CardService cardService) {
		this.cardService = cardService;
	}

	@GetMapping("/")
	public List<Card> fetch() {
		return cardService.getAllCards();
	}
	@GetMapping("/{id}")
	public Card fetch(
			@RequestParam String id) {
		return cardService.getCardById(id);
	}

	@PutMapping("/")
	public void put() {
	}
}
