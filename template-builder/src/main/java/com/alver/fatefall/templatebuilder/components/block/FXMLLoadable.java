package com.alver.fatefall.templatebuilder.components.block;

import javafx.fxml.FXMLLoader;

import java.net.URL;
import java.util.Objects;

public interface FXMLLoadable {

    default void load(Class<?> level, URL fxml) {
        if (true || getClass().equals(level)){
            load(fxml);
        }
    }

    private void load(URL fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(fxml);
            loader.setController(this);
            loader.setRoot(this);
            loader.setClassLoader(this.getClass().getClassLoader());
            loader.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    default void load(URL fxml, Object... nu) {
        try {
            FXMLLoader loader = new FXMLLoader(fxml);
            loader.setRoot(this);
            loader.setClassLoader(this.getClass().getClassLoader());
            loader.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static URL fxmlResource(Class<?> clazz){
        String clazzName = clazz.getSimpleName();
        String fxmlName = clazzName + ".fxml";
        URL fxml = clazz.getResource(fxmlName);
        return Objects.requireNonNull(fxml);
    }
}
