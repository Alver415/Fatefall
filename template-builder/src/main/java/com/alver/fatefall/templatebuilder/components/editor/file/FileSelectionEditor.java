package com.alver.fatefall.templatebuilder.components.editor.file;

import javafx.beans.value.ObservableValue;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.AbstractPropertyEditor;

import java.io.File;

public class FileSelectionEditor extends AbstractPropertyEditor<File, FileSelectionField> {

    public FileSelectionEditor(PropertySheet.Item property) {
        this(property, new FileSelectionField());
    }

    public FileSelectionEditor(PropertySheet.Item property, FileSelectionField fileField) {
        super(property, fileField);
    }

    @Override
    protected ObservableValue<File> getObservableValue() {
        return getEditor().fileProperty();
    }

    @Override
    public File getValue() {
        return getEditor().fileProperty().get();
    }

    @Override
    public void setValue(File value) {
        getEditor().fileProperty().set(value);
    }
}