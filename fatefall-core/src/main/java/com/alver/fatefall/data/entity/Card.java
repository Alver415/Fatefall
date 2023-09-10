package com.alver.fatefall.data.entity;

public interface Card<ICardFace extends CardFace<ITemplate>, ITemplate extends Template> extends Entity {

	ICardFace getFront();
	ICardFace getBack();
}
