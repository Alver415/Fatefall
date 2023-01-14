package com.alver.fatefall.api.interfaces;

import com.alver.fatefall.api.models.CardCollection;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;

public interface CardCollectionView<T extends Node> extends FxView<T> {

    ObjectProperty<CardCollection> cardCollectionProperty();

    default CardCollection getCardCollection() {
        return cardCollectionProperty().get();
    }

    default void setCardCollection(CardCollection value) {
        cardCollectionProperty().set(value);
    }

}