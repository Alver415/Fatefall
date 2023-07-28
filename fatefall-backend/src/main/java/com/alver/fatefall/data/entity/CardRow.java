package com.alver.fatefall.data.entity;

public class CardRow extends EntityRow implements Card<CardFaceRow> {

	private CardFaceRow front = new CardFaceRow();
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
