package com.alver.fatefall.api.models;

import java.util.ArrayList;
import java.util.List;

public class CardCollection {

	protected String id;
	protected String name;
	protected List<Card> cards = new ArrayList<>();
	protected Object data;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
