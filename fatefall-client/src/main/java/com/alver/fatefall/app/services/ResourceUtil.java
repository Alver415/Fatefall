package com.alver.fatefall.app.services;

import javafx.scene.image.Image;

import java.util.Objects;

public class ResourceUtil {

    public static String css(Class<?> clazz, String name){
        return Objects.requireNonNull(clazz.getResource(name)).toExternalForm();
    }


    public static Image image(String resource){
        try {
            String className = Thread.currentThread().getStackTrace()[2].getClassName();
            Class<?> clazz = Class.forName(className);
            return image(clazz, resource);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Image image(Object object, String resource){
        return image(object.getClass(), resource);
    }

    public static Image image(Class<?> clazz, String resource){
        return new Image(Objects.requireNonNull(clazz.getResourceAsStream(resource)));
    }

}
