package com.alver.fatefall.templatebuilder.components.builders;

import com.alver.fatefall.templatebuilder.components.block.Card;
import com.alver.fatefall.templatebuilder.components.block.CardFace;

public class CardBuilder extends AbstractBuilder<Card> {

    protected CardBuilder() {
        super(new Card());
    }
    public static CardBuilder builder(){
        return new CardBuilder();
    }

    public CardBuilder front(CardFace front) {
        object.setFront(front);
        return this;
    }

    public CardBuilder back(CardFace back) {
        object.setBack(back);
        return this;
    }

}
