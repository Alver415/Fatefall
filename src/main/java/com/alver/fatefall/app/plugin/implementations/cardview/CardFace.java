package com.alver.fatefall.app.plugin.implementations.cardview;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class CardFace extends AnchorPane {

    public CardFace(){
        super();
        setMinWidth(250);
        setMinHeight(350);
        setMaxWidth(250);
        setMaxHeight(350);
        setPrefWidth(250);
        setPrefHeight(350);
    }

    public CardFace(Node... children){
        this();
        getChildren().addAll(children);
    }
}
