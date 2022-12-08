package com.alver.fatefall.plugin;

@FunctionalInterface
public interface Action {

    default String getName() {
        return null;
    }

    default String getDescription(){
        return null;
    }

    void execute();
}
