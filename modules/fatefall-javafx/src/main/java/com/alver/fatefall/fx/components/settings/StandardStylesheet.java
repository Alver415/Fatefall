package com.alver.fatefall.fx.components.settings;

import com.alver.fatefall.FatefallApplication;

import java.util.Objects;

public enum StandardStylesheet implements NamedValue<String> {
    DARK("Dark", "dark.css"),
    LIGHT("Light", "light.css");

    private String value;
    private String name;

    StandardStylesheet(String name, String location) {
        this.name = name;
        this.value = Objects.requireNonNull(getClass().getResource(location)).toExternalForm();

    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getValue() {
        return value;
    }
}