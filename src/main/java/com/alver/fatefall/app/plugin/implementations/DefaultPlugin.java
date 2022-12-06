package com.alver.fatefall.app.plugin.implementations;

import com.alver.fatefall.app.plugin.Plugin;
import com.alver.fatefall.app.plugin.interfaces.ComponentFactory;
import com.alver.fatefall.app.plugin.interfaces.ContextMenuFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultPlugin extends Plugin {

    @Autowired
    protected ComponentFactory componentFactory;

    public DefaultPlugin(){
        this("Default", "v1.0");
    }
    protected DefaultPlugin(String name, String version) {
        super(name, version);
    }

    @Override
    public ComponentFactory getComponentFactory() {
        return componentFactory;
    }

    @Override
    public ContextMenuFactory getContextMenuFactory() {
        return new DefaultContextMenuFactory();
    }

}
