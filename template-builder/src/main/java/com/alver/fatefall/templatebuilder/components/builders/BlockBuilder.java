package com.alver.fatefall.templatebuilder.components.builders;

import com.alver.fatefall.templatebuilder.components.block.Block;

public abstract class BlockBuilder<T> extends AbstractBuilder<Block<T>> {

    protected BlockBuilder(Block<T> object) {
        super(object);
    }

    public BlockBuilder<T> id(String id) {
        object.setId(id);
        return this;
    }
    public BlockBuilder<T> value(T value) {
        object.setValue(value);
        return this;
    }
    public BlockBuilder<T> addBlock(Block<?> child) {
        object.getChildren().add(child);
        return this;
    }
}