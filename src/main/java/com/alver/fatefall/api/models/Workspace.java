package com.alver.fatefall.api.models;

import jakarta.persistence.*;
import javafx.beans.property.MapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;

import java.util.*;

@Entity
@Table(name = "workspace")
public class Workspace extends BaseEntity {

	protected ObservableList<Card> cards = FXCollections.observableArrayList();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Set<Card> getCards() {
		return new HashSet<>(cards);
	}
	public void setCards(Set<Card> cards) {
		this.cards.setAll(cards);
	}
	@Transient
	public ObservableList<Card> getObservableCards() {
		return cards;
	}

	public void addCard(Card card) {
		this.cards.add(card);
	}
	public void addCards(Collection<Card> cards) {
		cards.forEach(this::addCard);
	}
	public void removeCard(Card card) {
		this.cards.remove(card);
	}

}
