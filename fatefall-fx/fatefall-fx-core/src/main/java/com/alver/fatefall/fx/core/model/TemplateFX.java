package com.alver.fatefall.fx.core.model;

import com.alver.fatefall.core.entity.Template;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TemplateFX extends EntityFX implements Template {

    private final StringProperty imageUrlProperty = new SimpleStringProperty(this, "imageUrl");
    private final StringProperty fxmlUrlProperty = new SimpleStringProperty(this, "fxmlUrl");

    public TemplateFX() {
        super();
    }

    public TemplateFX(Long id) {
        super(id);
    }

    @Override
    public String getImageUrl() {
        return imageUrlProperty().get();
    }

    public StringProperty imageUrlProperty() {
        return imageUrlProperty;
    }

    public void setImageUrl(String imageUrlProperty) {
        imageUrlProperty().set(imageUrlProperty);
    }

    public String getFxmlUrl() {
        return fxmlUrlProperty().get();
    }

    public StringProperty fxmlUrlProperty() {
        return fxmlUrlProperty;
    }

    public void setFxmlUrl(String fxmlUrlProperty) {
        fxmlUrlProperty().set(fxmlUrlProperty);
    }
}
