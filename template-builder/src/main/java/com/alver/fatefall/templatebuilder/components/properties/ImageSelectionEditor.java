package com.alver.fatefall.templatebuilder.components.properties;

import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.AbstractPropertyEditor;

public class ImageSelectionEditor extends AbstractPropertyEditor<Image, ImageSelectionField> {

    public ImageSelectionEditor(PropertySheet.Item property) {
        this(property, new ImageSelectionField());
    }

    public ImageSelectionEditor(PropertySheet.Item property, ImageSelectionField imageField) {
        super(property, imageField);
    }

    @Override
    protected ObservableValue<Image> getObservableValue() {
        return getEditor().imageProperty();
    }

    @Override
    public Image getValue() {
        return getEditor().imageProperty().get();
    }

    @Override
    public void setValue(Image value) {
        getEditor().imageProperty().set(value);
    }

}