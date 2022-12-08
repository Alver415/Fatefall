package com.alver.fatefall.app.editor.image;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import org.controlsfx.control.textfield.CustomTextField;

public class ImageSelectionField extends CustomTextField {

    public ImageSelectionField(){
        textProperty().bindBidirectional(imageProperty(), new StringImageConverter());
    }

    protected ObjectProperty<Image> image = new SimpleObjectProperty<>(this, "image", null);
    public ObjectProperty<Image> imageProperty(){
        return image;
    }
    public Image getImage(){
        return image.get();
    }
    public void setImage(Image image){
        this.image.set(image);
    }
}
