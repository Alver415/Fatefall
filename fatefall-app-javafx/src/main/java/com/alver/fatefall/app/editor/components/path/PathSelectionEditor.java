package com.alver.fatefall.app.editor.components.path;

import javafx.beans.value.ObservableValue;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.AbstractPropertyEditor;

import java.nio.file.Path;

public class PathSelectionEditor extends AbstractPropertyEditor<Path, PathSelectionField> {

    public PathSelectionEditor(PropertySheet.Item property) {
        this(property, new PathSelectionField());
    }

    public PathSelectionEditor(PropertySheet.Item property, PathSelectionField fileField) {
        super(property, fileField);
    }

    @Override
    protected ObservableValue<Path> getObservableValue() {
        return getEditor().pathProperty();
    }

    @Override
    public Path getValue() {
        return getEditor().pathProperty().get();
    }

    @Override
    public void setValue(Path value) {
        getEditor().pathProperty().set(value);
    }
}