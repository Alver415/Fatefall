package com.alver.fatefall.api;

import javafx.scene.Node;
import org.pf4j.spring.SpringPluginManager;

public abstract class FatefallPluginManager extends SpringPluginManager {

    public abstract void createToolTab(String name, Node content);
}
