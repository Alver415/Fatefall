package com.alver.fatefall.fx.app.view.entity.card.template;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.core.utils.FXUtils;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLPrototype(location = "PlaceholderTemplate.fxml")
public class TemplateController {

    protected DoubleProperty widthProperty = new SimpleDoubleProperty();
    protected DoubleProperty heightProperty = new SimpleDoubleProperty();
    protected DoubleProperty arcWidthProperty = new SimpleDoubleProperty();
    protected DoubleProperty arcHeightProperty = new SimpleDoubleProperty();

    protected ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

    @Autowired
    public TemplateController(FatefallProperties properties){
        FXUtils.runFx(() -> {
            widthProperty.bind(properties.getCardBaseWidth());
            heightProperty.bind(properties.getCardBaseHeight());
            arcWidthProperty.bind(properties.getCardBaseArcWidth());
            arcHeightProperty.bind(properties.getCardBaseArcHeight());
        });
    }

    public double getWidth() {
        return widthProperty.get();
    }

    public DoubleProperty widthProperty() {
        return widthProperty;
    }

    public void setWidth(double widthProperty) {
        this.widthProperty.set(widthProperty);
    }

    public double getHeight() {
        return heightProperty.get();
    }

    public DoubleProperty heightProperty() {
        return heightProperty;
    }

    public void setHeight(double heightProperty) {
        this.heightProperty.set(heightProperty);
    }

    public double getArcWidth() {
        return arcWidthProperty.get();
    }

    public DoubleProperty arcWidthProperty() {
        return arcWidthProperty;
    }

    public void setArcWidth(double arcWidthProperty) {
        this.arcWidthProperty.set(arcWidthProperty);
    }

    public double getArcHeight() {
        return arcHeightProperty.get();
    }

    public DoubleProperty arcHeightProperty() {
        return arcHeightProperty;
    }

    public void setArcHeight(double arcHeightProperty) {
        this.arcHeightProperty.set(arcHeightProperty);
    }

    public Image getImage() {
        return imageProperty.get();
    }

    public ObjectProperty<Image> imageProperty() {
        return imageProperty;
    }

    public void setImage(Image image) {
        this.imageProperty.set(image);
    }
}
