package com.alver.fatefall.fx.core.interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface AppEvent extends EventHandler<ActionEvent> {
    String getTitle();
    default String getDescription(){
        return null;
    }
}