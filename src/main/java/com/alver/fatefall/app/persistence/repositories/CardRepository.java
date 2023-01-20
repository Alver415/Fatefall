package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardAttribute;
import com.alver.fatefall.app.persistence.models.CardAttributeRow;
import com.alver.fatefall.app.persistence.models.CardRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class CardRepository extends ProxyRepository<Card, CardRow, String> {

	protected CardAttributeRepository cardAttributeRepository;

	@Autowired
	CardRepository(CardRowRepository wrappedRepository,
				   CardAttributeRepository cardAttributeRepository) {
		super(wrappedRepository);
		this.cardAttributeRepository = cardAttributeRepository;
	}

	@Override
	<S extends Card> CardRow toRow(S card) {
		CardRow row = new CardRow();
		row.setId(card.getId());
		row.setName(card.getName());
		row.setFrontUrl(card.getFrontUrl());
		row.setBackUrl(card.getBackUrl());
		row.setAttributeList(card.getAttributeList().stream()
				.map(cardAttributeRepository::toRow).toList());
		row.setData(card.getData());
		return row;
	}

	@Override
	<S extends Card> S fromRow(CardRow row) {
		Card card = new Card();
		card.setId(row.getId());
		card.setName(row.getName());
		card.setFrontUrl(row.getFrontUrl());
		card.setBackUrl(row.getBackUrl());
		card.setData(row.getData());
		card.getAttributeList().setAll(row.getAttributeList().stream()
				.map((Function<? super CardAttributeRow, ? extends CardAttribute<?>>)
						cardAttributeRepository::fromRow).toList());
		return (S) card;
	}

	@Override
	<S extends Card> Iterable<CardRow> toRows(Iterable<S> entities) {
		List<CardRow> list = new ArrayList<>();
		for (Card card : entities) {
			list.add(toRow(card));
		}
		return list;
	}
	@Override
	<S extends Card> Iterable<S> fromRows(Iterable<CardRow> rows) {
		List<Card> list = new ArrayList<>();
		for (CardRow row : rows) {
			list.add(fromRow(row));
		}
		return (Iterable<S>) list;
	}
}