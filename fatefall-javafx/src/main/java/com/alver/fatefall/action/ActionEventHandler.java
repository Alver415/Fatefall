package com.alver.fatefall.action;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.pf4j.ExtensionPoint;

public interface ActionEventHandler extends EventHandler<ActionEvent>, ExtensionPoint {
    String getTitle();
    default String getDescription(){
        return null;
    }
}