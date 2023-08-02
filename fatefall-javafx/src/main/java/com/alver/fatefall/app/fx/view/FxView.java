package com.alver.fatefall.app.fx.view;

import javafx.scene.Node;

public interface FxView<T extends Node>{

    default T getFxViewNode(){
        //TODO: Should check if instance of T, but generic erasure makes that tricky.
        if (Node.class.isAssignableFrom(this.getClass())){
            return (T) this;
        }
        throw new RuntimeException("Not Implemented.");
    }
}