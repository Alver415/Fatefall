package com.alver.fatefall.data.entity;

import java.util.Collection;
import java.util.List;

public interface Workspace<ICard extends Card<?>> extends Entity {

	List<ICard> getCards();

	default void addCards(ICard... cards) {
		getCards().addAll(List.of(cards));
	}

	default void addCards(Collection<ICard> cards) {
		getCards().addAll(cards);
	}

	default void removeCards(ICard... cards) {
		getCards().removeAll(List.of(cards));
	}

	default void removeCards(Collection<ICard> cards) {
		getCards().removeAll(cards);
	}
}
