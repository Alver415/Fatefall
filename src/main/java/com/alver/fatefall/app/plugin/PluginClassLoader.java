package com.alver.fatefall.app.plugin;

import java.net.URL;
import java.net.URLClassLoader;

public class PluginClassLoader extends URLClassLoader {

    public PluginClassLoader(URL url) {
        super(new URL[]{url});
    }
}
