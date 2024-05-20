package com.alver.fatefall.fx.plugin;

import java.util.Iterator;
import java.util.List;

public class DelegatingClassLoader extends ClassLoader {

    protected final List<ClassLoader> classLoaders;

    public DelegatingClassLoader(List<ClassLoader> classLoaders) {
        this.classLoaders = classLoaders;
    }

    @Override
    public Class loadClass(String name) throws ClassNotFoundException {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(name);
        } catch (ClassNotFoundException e) {
            Iterator<ClassLoader> iterator = classLoaders.iterator();
            while (iterator.hasNext()) {
                try {
                    return iterator.next().loadClass(name);
                } catch (ClassNotFoundException e2) {
                }
            }
            throw e;
        }
    }

}
