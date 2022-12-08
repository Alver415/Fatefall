package com.alver.fatefall.plugin;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;

public class PluginClassLoader extends URLClassLoader {

    public PluginClassLoader(URL url) {
        super(new URL[]{url});
    }

    @Override
    public URL getResource(String name) {
        Objects.requireNonNull(name);
        URL url = findResource(name);
        if (url == null){
            super.findResource(name);
        }
        return url;
    }
}
