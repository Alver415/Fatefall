package component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import utils.ImageInterpolator;
import utils.LinearImageInterpolator;

public class CompositeImageComponent extends ImageComponent {

    public CompositeImageComponent() {
        super();
        firstImage.addListener((observable, oldValue, newValue) -> refresh());
        secondImage.addListener((observable, oldValue, newValue) -> refresh());
    }

    public void refresh() {
        if (firstImage.get() == null || secondImage.get() == null) {
            return;
        }
        try {
            imageProperty().set(imageInterpolator.get().interpolate(getFirstImage(), getSecondImage()));
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

    public ObjectProperty<Image> secondImage = new SimpleObjectProperty<Image>(this, "secondImage", null);

    public ObjectProperty<Image> secondImageProperty() {
        return secondImage;

    }

    public Image getSecondImage() {
        return secondImage.get();
    }

    public void setSecondImage(Image secondImage) {
        this.secondImage.set(secondImage);
    }
}
