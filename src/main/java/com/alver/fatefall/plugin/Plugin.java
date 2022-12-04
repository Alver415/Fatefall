package com.alver.fatefall.plugin;

import com.alver.fatefall.plugin.interfaces.ComponentFactory;
import com.alver.fatefall.plugin.interfaces.ContextMenuFactory;

public abstract class Plugin {

    protected final String name;
    protected final String version;
    protected boolean enabled = true;

    protected Plugin(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public final String getName() {
        return name;
    }

    public final String getVersion() {
        return version;
    }

    public final boolean isEnabled() {
        return enabled;
    }

    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public abstract ComponentFactory getComponentFactory();

    public abstract ContextMenuFactory getContextMenuFactory();


    @Override
    public String toString() {
        return String.format("%s - %s", name, version);
    }

}
