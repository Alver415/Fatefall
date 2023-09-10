package com.alver.fatefall.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;

@Entity
public class CardRow extends EntityRow implements Card<CardFaceRow, TemplateRow> {

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private CardFaceRow front = new CardFaceRow();
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private CardFaceRow back = new CardFaceRow();

	@Override
	public CardFaceRow getFront() {
		return front;
	}
	public void setFront(CardFaceRow front) {
		this.front = front;
	}

	@Override
	public CardFaceRow getBack() {
		return back;
	}
	public void setBack(CardFaceRow back) {
		this.back = back;
	}
}
