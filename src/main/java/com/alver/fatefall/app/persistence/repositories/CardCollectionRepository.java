package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.app.persistence.models.CardCollectionRow;
import com.alver.fatefall.app.persistence.models.CardRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class CardCollectionRepository extends ProxyRepository<CardCollection, CardCollectionRow, String> {

	protected CardRepository cardRepository;

	@Autowired
	CardCollectionRepository(
			CardCollectionRowRepository wrappedRepository,
			CardRepository cardRepository) {
		super(wrappedRepository);
		this.cardRepository = cardRepository;
	}

	@Override
	<S extends CardCollection> CardCollectionRow toRow(S collection) {
		CardCollectionRow row = new CardCollectionRow();
		row.setId(collection.getId());
		row.setName(collection.getName());
		row.setCards(collection.getCardList().stream()
				.map(cardRepository::toRow).toList());
		row.setData(collection.getData());
		return row;
	}

	@Override
	<S extends CardCollection> S fromRow(CardCollectionRow row) {
		CardCollection collection = new CardCollection();
		collection.setId(row.getId());
		collection.setName(row.getName());
		collection.setData(row.getData());
		collection.getCardList().setAll(row.getCards().stream()
				.map((Function<? super CardRow, ? extends Card>)
						cardRepository::fromRow).toList());
		return (S) collection;
	}

	@Override
	<S extends CardCollection> Iterable<CardCollectionRow> toRows(Iterable<S> collections) {
		List<CardCollectionRow> list = new ArrayList<>();
		for (CardCollection collection : collections) {
			list.add(toRow(collection));
		}
		return list;
	}
	@Override
	<S extends CardCollection> Iterable<S> fromRows(Iterable<CardCollectionRow> rows) {
		List<CardCollection> list = new ArrayList<>();
		for (CardCollectionRow row : rows) {
			list.add(fromRow(row));
		}
		return (Iterable<S>) list;
	}
}