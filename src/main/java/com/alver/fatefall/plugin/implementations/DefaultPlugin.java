package com.alver.fatefall.plugin.implementations;

import com.alver.fatefall.plugin.Plugin;
import com.alver.fatefall.plugin.interfaces.ComponentFactory;
import com.alver.fatefall.plugin.interfaces.ContextMenuFactory;

public class DefaultPlugin extends Plugin {

    public DefaultPlugin(){
        this("Default", "v1.0");
    }
    protected DefaultPlugin(String name, String version) {
        super(name, version);
    }

    @Override
    public ComponentFactory getComponentFactory() {
        return new DefaultComponentFactory();
    }

    @Override
    public ContextMenuFactory getContextMenuFactory() {
        return new DefaultContextMenuFactory();
    }

}
