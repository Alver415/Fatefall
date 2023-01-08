package com.alver.fatefall.persistence;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class FileNode<T> {

    protected String name;

    protected FileNode<?> parent;
    protected Set<FileNode<?>> children = new HashSet<>();

    protected Supplier<T> supplier;
    protected T cachedObject;
    protected Map<String, File> data;


    public String getName() {
        return name;
    }

    public T get() {
        if (cachedObject == null) {
            cachedObject = supplier.get();
        }
        return cachedObject;
    }

    public FileNode<?> getRoot() {
        return parent == null ? this : parent.getRoot();
    }

}
