package com.alver.fatefall.templatebuilder.components.editor.directory;

import javafx.beans.value.ObservableValue;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.AbstractPropertyEditor;

import java.io.File;

public class DirectorySelectionEditor extends AbstractPropertyEditor<File, DirectorySelectionField> {

    public DirectorySelectionEditor(PropertySheet.Item property) {
        this(property, new DirectorySelectionField());
    }

    public DirectorySelectionEditor(PropertySheet.Item property, DirectorySelectionField directoryField) {
        super(property, directoryField);
    }

    @Override
    protected ObservableValue<File> getObservableValue() {
        return getEditor().directoryProperty();
    }

    @Override
    public File getValue() {
        return getEditor().directoryProperty().get();
    }

    @Override
    public void setValue(File value) {
        getEditor().directoryProperty().set(value);
    }
}