package com.alver.fatefall.controller;

import com.alver.fatefall.data.entities.Card;
import com.alver.fatefall.service.CardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {

	private final CardService cardService;

	@Autowired
	public CardController(CardService cardService) {
		this.cardService = cardService;
	}

	@GetMapping
	public ResponseEntity<List<Card>> getAllCards() {
		return ResponseEntity.ok(cardService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Card> getCardById(
			@RequestParam Long id) {
		return ResponseEntity.ok(cardService.findById(id)
				.orElseThrow(EntityNotFoundException::new));
	}

	@PostMapping()
	public ResponseEntity<Card> createCard(
			@RequestBody Card card) {
		return ResponseEntity.ok(cardService.save(card));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Card> updateCard(
			@PathVariable Long id,
			@RequestBody Card cardWithUpdates) {
		Optional<Card> existing = cardService.findById(id);
		if (!existing.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Card saved = cardService.save(cardWithUpdates);
		return ResponseEntity.ok(saved);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCard(
			@PathVariable Long id) {
		Optional<Card> existing = cardService.findById(id);
		if (!existing.isEmpty()) {
			cardService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
