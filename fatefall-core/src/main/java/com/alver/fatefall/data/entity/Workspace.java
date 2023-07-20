package com.alver.fatefall.data.entity;

import java.util.ArrayList;
import java.util.List;

public class Workspace extends Entity {

	private String name;

	protected List<Card> cards = new ArrayList<>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

}
