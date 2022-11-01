package com.alver.fatefall.templatebuilder.components.block;

import com.alver.fatefall.templatebuilder.components.editor.image.StringImageConverter;
import javafx.beans.DefaultProperty;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

@DefaultProperty("image")
public class ImageBlock extends Block<Image> {

    private static final URL FXML = FXMLLoadable.fxmlResource(ImageBlock.class);

    @FXML
    protected ImageView imageView;

    public ImageBlock() {
        super();
        load(ImageBlock.class, FXML);
    }

    @FXML
    protected void initialize() {
        super.initialize();
        imageProperty().bindBidirectional(imageView.imageProperty());
        urlProperty().bindBidirectional(imageProperty(), new StringImageConverter());

        imageView.setPreserveRatio(false);
        imageView.fitWidthProperty().bind(widthProperty());
        imageView.fitHeightProperty().bind(heightProperty());

        valueProperty().bindBidirectional(imageProperty());
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

    public DoubleProperty fitWidthProperty() {
        return imageView.fitWidthProperty();
    }

    public double getFitWidth() {
        return fitWidthProperty().get();
    }

    public void setFitWidth(double value) {
        fitWidthProperty().set(value);
    }

    public DoubleProperty fitHeightProperty() {
        return imageView.fitWidthProperty();
    }

    public double getFitHeight() {
        return fitHeightProperty().get();
    }

    public void setFitHeight(double value) {
        fitHeightProperty().set(value);
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
