package com.alver.fatefall.app.services;

import javafx.scene.image.Image;

import java.util.Objects;

public class ResourceUtil {

    public static String css(Class<?> clazz, String name){
        return Objects.requireNonNull(clazz.getResource(name)).toExternalForm();
    }
    public static Image image(Class<?> clazz, String name){
        return new Image(Objects.requireNonNull(clazz.getResourceAsStream(name)));
    }

}
