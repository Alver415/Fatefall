package com.alver.fatefall.service;

import com.alver.fatefall.data.repository.CardRepository;
import com.alver.fatefall.data.entities.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

	private final CardRepository cardRepository;

	@Autowired
	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	public List<Card> findAll() {
		return cardRepository.findAll();
	}

	public Optional<Card> findById(Long id) {
		return cardRepository.findById(id);
	}

	public Card save(Card card) {
		return cardRepository.save(card);
	}

	public void deleteById(Long id) {
		cardRepository.deleteById(id);
	}
}
