package com.alver.fatefall.scryfall.plugin.component;


import com.alver.fatefall.app.plugin.implementations.DefaultPlugin;
import com.alver.fatefall.app.plugin.interfaces.ComponentFactory;
import com.alver.fatefall.app.plugin.interfaces.ContextMenuFactory;

public class ScryfallPlugin extends DefaultPlugin {

    public ScryfallPlugin(){
        super("Scryfall Plugin", "v1.0");
    }
    @Override
    public ComponentFactory getComponentFactory(){
        return new ScryfallComponentFactory();
    }
    @Override
    public ContextMenuFactory getContextMenuFactory(){
        return new ScryfallContextMenuFactory();
    }
}
