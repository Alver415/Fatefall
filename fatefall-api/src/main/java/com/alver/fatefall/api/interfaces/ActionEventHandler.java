package com.alver.fatefall.api.interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.pf4j.ExtensionPoint;

public interface ActionEventHandler extends EventHandler<ActionEvent>, ExtensionPoint {
    String getName();
    default String getDescription(){
        return null;
    }
}
