package com.alver.fatefall.data.entity;

import java.util.*;

public class Workspace extends Entity {

	private String name;

	protected Set<Card> cards = new HashSet<>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Set<Card> getCards() {
		return cards;
	}
	private void setCards(Set<Card> cards) {
		this.cards = cards;
	}
	public void addCards(Card card) {
		addCards(List.of(card));
	}
	public void addCards(Card... cards) {
		addCards(List.of(cards));
	}
	public void addCards(Collection<Card> cards) {
		this.cards.addAll(cards);
	}
	public void removeCards(Card... cards) {
		removeCards(List.of(cards));
	}
	public void removeCards(Collection<Card> cards) {
		this.cards.removeAll(cards);
	}

}
