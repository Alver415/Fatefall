package com.alver.fatefall.plugin;

import java.util.List;

public interface Plugin {

    String getName();

    String getVersion();

    void start();

    void stop();

    List<Action> getActions();

}
