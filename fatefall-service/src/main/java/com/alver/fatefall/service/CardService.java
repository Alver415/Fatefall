package com.alver.fatefall.service;

import com.alver.fatefall.api.Card;
import com.alver.fatefall.database.row.CardRow;
import com.alver.fatefall.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardService {

	private final CardRepository cardRepository;

	@Autowired
	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	public List<Card> getAllCards() {
		List<CardRow> cardRows = cardRepository.getAllCardRows();
		return cardRows.stream().map(row -> new Card(row.id(), row.name())).toList();

	}

	public Card getCardById(String id) {
		CardRow cardRow = cardRepository.getCardRow(id);
		return new Card(cardRow.id(), cardRow.name());
	}

}
