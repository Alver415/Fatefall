package com.alver.fatefall.plugin.interfaces;

import com.alver.fatefall.plugin.models.Card;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class MtgCardView implements CardView {

    protected ObjectProperty<Card> cardProperty = new SimpleObjectProperty<>();

    @Override
    public ObjectProperty<Card> cardProperty() {
        return cardProperty;
    }

    public Node getFxViewNode(){
        return new Button("Test");
    }
}
