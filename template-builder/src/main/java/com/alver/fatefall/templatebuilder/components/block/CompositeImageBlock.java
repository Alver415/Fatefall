package com.alver.fatefall.templatebuilder.components.block;

import com.alver.fatefall.templatebuilder.components.properties.StringImageConverter;
import com.alver.fatefall.templatebuilder.components.utils.ImageInterpolator;
import com.alver.fatefall.templatebuilder.components.utils.LinearImageInterpolator;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

import java.net.URL;

public class CompositeImageBlock extends ImageBlock {

    private static final URL FXML = FXMLLoadable.fxmlResource(ImageBlock.class);

    public CompositeImageBlock() {
        super();
        load(CompositeImageBlock.class, FXML);
    }

    @FXML
    protected void initialize() {
        super.initialize();
        firstImage.addListener((observable, oldValue, newValue) -> refresh());
        secondImage.addListener((observable, oldValue, newValue) -> refresh());
    }

    public void refresh() {
        if (firstImage.get() == null || secondImage.get() == null) {
            return;
        }
        try {
            imageView.imageProperty().set(imageInterpolator.get().interpolate(getFirstImage(), getSecondImage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObjectProperty<ImageInterpolator> imageInterpolator = new SimpleObjectProperty<>(this, "imageInterpolator", new LinearImageInterpolator(LinearImageInterpolator.Type.VERTICAL));

    public ObjectProperty<ImageInterpolator> imageInterpolatorProperty() {
        return imageInterpolator;
    }

    public ImageInterpolator getImageInterpolator() {
        return imageInterpolator.get();
    }

    public void setImageInterpolator(ImageInterpolator imageInterpolator) {
        this.imageInterpolator.set(imageInterpolator);
    }

    public ObjectProperty<Image> firstImage = new SimpleObjectProperty<>(this, "firstImage", null);

    public ObjectProperty<Image> firstImageProperty() {
        return firstImage;
    }

    public Image getFirstImage() {
        return firstImage.get();
    }

    public void setFirstImage(Image firstImage) {
        this.firstImage.set(firstImage);
    }

    public void setFirstImageUrl(String url) {
        this.firstImage.set(new StringImageConverter().fromString(url));
    }

    public ObjectProperty<Image> secondImage = new SimpleObjectProperty<>(this, "secondImage", null);

    public ObjectProperty<Image> secondImageProperty() {
        return secondImage;

    }

    public Image getSecondImage() {
        return secondImage.get();
    }

    public void setSecondImage(Image secondImage) {
        this.secondImage.set(secondImage);
    }

    public void setSecondImageUrl(String url) {
        this.secondImage.set(new StringImageConverter().fromString(url));
    }
}
