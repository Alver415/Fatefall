package com.alver.fatefall.app.plugin.implementations.cardview;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class CardFace extends AnchorPane {

    public CardFace(){
        super();
        setPrefWidth(250);
        setPrefHeight(350);
    }

    public CardFace(Node... children){
        this();
        getChildren().addAll(children);
    }
}
