package com.alver.fatefall.app.persistence.repositories;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.Workspace;
import com.alver.fatefall.app.persistence.models.CardRow;
import com.alver.fatefall.app.persistence.models.WorkspaceRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceRepository extends AbstractRepository<Workspace, WorkspaceRow, String> {

	protected CardRepository cardRepository;

	@Autowired
	WorkspaceRepository(
			WorkspaceRowRepository wrappedRepository,
			CardRepository cardRepository) {
		super(wrappedRepository);
		this.cardRepository = cardRepository;
	}

	@Override
	protected <S extends Workspace> WorkspaceRow instantiateRow(S entity) {
		return new WorkspaceRow();
	}

	@Override
	protected <S extends Workspace> S instantiateEntity(WorkspaceRow row) {
		return (S) new Workspace();
	}


	protected <S extends Workspace> S hydrateEntity(WorkspaceRow row, S entity) {
		super.hydrateEntity(row, entity);
		Iterable<Card> cards = cardRepository.createEntities(row.getCards());
		for (Card card : cards) {
			entity.getCards().add(card);
		}
		return entity;
	}

	protected <S extends Workspace> WorkspaceRow hydrateRow(S entity, WorkspaceRow row) {
		super.hydrateRow(entity, row);
		Iterable<CardRow> cards = cardRepository.createRows(entity.getCards());
		for (CardRow card : cards) {
			row.getCards().add(card);
		}
		return row;
	}
}