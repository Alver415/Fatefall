package com.alver.fatefall.plugin.interfaces;

import javafx.scene.Node;

public interface FxView {

    default Node getFxViewNode(){
        if (this instanceof Node node){
            return node;
        }
        throw new RuntimeException("Not Implemented.");
    }
}
