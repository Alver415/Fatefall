package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.api.models.Attribute;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.persistence.models.CardRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardRepository extends AbstractRepository<Card, CardRow, String> {

	protected AttributeRepository cardAttributeRepository;

	@Autowired
	CardRepository(CardRowRepository wrappedRepository,
				   AttributeRepository cardAttributeRepository) {
		super(wrappedRepository);
		this.cardAttributeRepository = cardAttributeRepository;
	}

	@Override
	protected <S extends Card> CardRow instantiateRow(S entity) {
		return new CardRow();
	}

	@Override
	protected <S extends Card> S instantiateEntity(CardRow row) {
		return (S) new Card();
	}
}