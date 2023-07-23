package com.alver.fatefall.data.entity;

public class Card extends Entity {

	private CardFace front = new CardFace();
	private CardFace back = new CardFace();

	public CardFace getFront() {
		return front;
	}
	public void setFront(CardFace front) {
		this.front = front;
	}
	public CardFace getBack() {
		return back;
	}
	public void setBack(CardFace back) {
		this.back = back;
	}
}
