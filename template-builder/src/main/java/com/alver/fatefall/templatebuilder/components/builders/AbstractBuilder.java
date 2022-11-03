package com.alver.fatefall.templatebuilder.components.builders;

import javafx.util.Builder;

public abstract class AbstractBuilder<T> implements Builder<T> {

    protected final T object;

    protected AbstractBuilder(T object) {
        this.object = object;
    }

    @Override
    public T build() {
        return object;
    }

}
