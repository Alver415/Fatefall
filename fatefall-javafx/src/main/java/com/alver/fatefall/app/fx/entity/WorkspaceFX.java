package com.alver.fatefall.app.fx.entity;

import com.alver.fatefall.data.entity.Workspace;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.Collection;
import java.util.List;

public class WorkspaceFX extends EntityFX implements Workspace<CardFX> {

	private final ListProperty<CardFX> cards = new SimpleListProperty<>(this, "cards", FXCollections.observableArrayList());

	public WorkspaceFX() {
		super();
	}
	public WorkspaceFX(Long id) {
		super(id);
	}

	public ListProperty<CardFX> cardsProperty(){
		return cards;
	}

	@Override
	public List<CardFX> getCards() {
		return cards.get();
	}
	@Override
	public void addCards(CardFX... cards) {
		this.cards.addAll(List.of(cards));
	}
	@Override
	public void addCards(Collection<CardFX> cards) {
		this.cards.addAll(cards);
	}
	@Override
	public void removeCards(CardFX... cards) {
		this.cards.removeAll(List.of(cards));
	}
	@Override
	public void removeCards(Collection<CardFX> cards) {
		this.cards.removeAll(cards);
	}
}
