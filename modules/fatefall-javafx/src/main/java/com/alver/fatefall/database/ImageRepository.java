package com.alver.fatefall.database;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ImageRepository {

    @Autowired
    private DatabaseManager databaseManager;

    private final Cache<String, Image> cache = CacheBuilder.newBuilder().build();

    public Image get(String key, boolean async) {
        try {
            return cache.get(key, () -> queryDatabase(key).orElseGet(() -> generateImage(key, async)));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Image generateImage(String key, boolean async) {
        Image image = new Image(key, async);
        runWhenDone(image.progressProperty(), () -> {
            ImageData cachedImage = convert(image);
            databaseManager.inSession((s) -> {
                return s.merge(cachedImage);
            });
        });
        return image;
    }

    private void runWhenDone(ReadOnlyDoubleProperty property, Runnable runnable) {
        //Already done, just run it now.
        if (isDone(property)) {
            runnable.run();
        }
        //Not finished loading. Add a listener for when it's finished.
        else {
            property.addListener(((observable, oldValue, newValue) -> {
                if (isDone(property)) {
                    runnable.run();
                }
            }));
        }

    }

    private boolean isDone(ReadOnlyDoubleProperty property) {
        return property.get() == 1.0;
    }

    public Image get(String url) {
        return get(url, false);
    }

    public ReadOnlyDoubleProperty getAsync(String url) {
        return get(url, true).progressProperty();
    }

    private Optional<Image> queryDatabase(String key) {
        return databaseManager.inReadOnlySession((s) -> {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<ImageData> cq = cb.createQuery(ImageData.class);
            Root<ImageData> root = cq.from(ImageData.class);

            cq.where(cb.equal(root.get("key"), key));

            Query<ImageData> query = s.createQuery(cq);
            List<ImageData> cachedImages = query.list();
            return cachedImages.stream().reduce((a, b) -> {
                throw new IllegalStateException("Multiple CachedImage results: " + cachedImages.size());
            }).map(this::convert);
        });
    }

    private Image convert(ImageData cachedImage) {
        int width = cachedImage.getWidth();
        int height = cachedImage.getHeight();
        byte[] bytes = cachedImage.getBytes();

        WritableImage image = new WritableImage(width, height);
        image.getPixelWriter().setPixels(0, 0, width, height, PixelFormat.getByteBgraInstance(), bytes, 0, width * 4);

        return image;
    }

    private ImageData convert(Image image) {
        if (image.getProgress() < 1) {
            throw new IllegalStateException("Image is still in progress: " + image.getProgress());
        }
        ImageData cachedImage = new ImageData();
        cachedImage.setWidth((int) image.getWidth());
        cachedImage.setHeight((int) image.getHeight());
        cachedImage.setKey(image.getUrl());
        cachedImage.setBytes(getBytes(image));
        return cachedImage;
    }

    private byte[] getBytes(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        byte[] bytes = new byte[height * width * 4];
        image.getPixelReader().getPixels(0, 0, width, height, PixelFormat.getByteBgraInstance(), bytes, 0, width * 4);

        return bytes;
    }
}
