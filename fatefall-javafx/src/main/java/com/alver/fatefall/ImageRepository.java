package com.alver.fatefall;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.image.Image;

import java.util.concurrent.ExecutionException;

public class ImageRepository {

    private final Cache<String, Image> cache;

    public ImageRepository() {
        this.cache = CacheBuilder.newBuilder().build();
    }

    public ImageRepository(Cache<String, Image> cache) {
        this.cache = cache;
    }


    public Image get(String url, boolean async)  {
        try {
            return cache.get(url, () -> new Image(url, async));
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Image get(String url)  {
        return get(url, false);
    }

    public ReadOnlyDoubleProperty getAsync(String url)  {
        return get(url, true).progressProperty();
    }
}
