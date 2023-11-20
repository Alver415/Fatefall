package com.alver.fatefall.fx.app.editor.block;

import com.alver.fatefall.fx.app.editor.components.image.StringImageConverter;
import javafx.beans.DefaultProperty;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@DefaultProperty("image")
public class ImageBlock extends Block<Image> {

    protected ImageView imageView = new ImageView();

    public ImageBlock() {
        super();
        getChildren().add(imageView);

        imageProperty().bindBidirectional(imageView.imageProperty());
        urlProperty().bindBidirectional(imageProperty(), new StringImageConverter());

        imageView.setPreserveRatio(false);
        imageView.fitWidthProperty().bind(widthProperty());
        imageView.fitHeightProperty().bind(heightProperty());

        valueProperty().bindBidirectional(imageProperty());
    }

    public ImageBlock(Image image) {
        this();
        setImage(image);
    }

    protected StringProperty url = new SimpleStringProperty(this, "url", null);

    public StringProperty urlProperty() {
        return url;
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    protected ObjectProperty<Image> image = new SimpleObjectProperty<>(this, "image", null);

    public ObjectProperty<Image> imageProperty() {
        return image;
    }

    public Image getImage() {
        return imageProperty().get();
    }

    public void setImage(Image image) {
        imageProperty().set(image);
    }

    public void setImageUrl(String url) {
        this.image.set(new StringImageConverter().fromString(url));
    }

    public BooleanProperty preserveRatioProperty() {
        return imageView.preserveRatioProperty();
    }

    public boolean getPreserveRatio() {
        return preserveRatioProperty().get();
    }

    public void setPreserveRatio(boolean value) {
        preserveRatioProperty().set(value);
    }

}
