package com.alver.fatefall.data.entity;

public interface CardFace<ITemplate extends Template> extends Entity {

	//TODO: Get rid of this insane generic typing nonsense
	<ICard extends Card<ICardFace, ITemplate>, ICardFace extends CardFace<ITemplate>> ICard getCard();
	ITemplate getTemplate();

}
