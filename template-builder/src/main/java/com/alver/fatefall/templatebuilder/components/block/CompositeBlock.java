package com.alver.fatefall.templatebuilder.components.block;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;

public class CompositeBlock<T> extends Block<T> {
    
    public CompositeBlock() {
        super();
        ChangeListener<Block<T>> childChangeListener = (observable, oldValue, newValue) -> {
            getChildren().remove(oldValue);
            getChildren().add(newValue);
        };
        firstBlock.addListener(childChangeListener);
        secondBlock.addListener(childChangeListener);
    }

    public ObjectProperty<Block<T>> firstBlock = new SimpleObjectProperty<>(this, "firstBlock", null);

    public ObjectProperty<Block<T>> firstBlockProperty() {
        return firstBlock;
    }

    public Block<T> getFirstBlock() {
        return firstBlock.get();
    }

    public void setFirstBlock(Block<T> firstBlock) {
        this.firstBlock.set(firstBlock);
    }

    public ObjectProperty<Block<T>> secondBlock = new SimpleObjectProperty<>(this, "secondBlock", null);

    public ObjectProperty<Block<T>> secondBlockProperty() {
        return secondBlock;

    }

    public Block<T> getSecondBlock() {
        return secondBlock.get();
    }

    public void setSecondBlock(Block<T> secondBlock) {
        this.secondBlock.set(secondBlock);
    }

}
