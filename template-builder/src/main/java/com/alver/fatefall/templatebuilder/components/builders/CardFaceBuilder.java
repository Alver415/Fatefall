package com.alver.fatefall.templatebuilder.components.builders;

import com.alver.fatefall.templatebuilder.components.block.Block;
import com.alver.fatefall.templatebuilder.components.block.CardFace;

public class CardFaceBuilder extends AbstractBuilder<CardFace> {

    protected CardFaceBuilder() {
        super(new CardFace());
    }

    public static CardFaceBuilder builder() {
        return new CardFaceBuilder();
    }

    public CardFaceBuilder addBlock(Block<?> block) {
        object.getChildren().add(block);
        return this;
    }

}