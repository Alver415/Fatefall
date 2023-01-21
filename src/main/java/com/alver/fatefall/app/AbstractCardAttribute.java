package com.alver.fatefall.app;

import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.AbstractPropertyEditor;
import org.controlsfx.property.editor.PropertyEditor;

import java.util.Optional;

public abstract class AbstractCardAttribute<T> implements PropertySheet.Item {

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public T getValue() {
        return getProperty().getValue();
    }

    public T get() {
        return getValue();
    }

    @Override
    public void setValue(Object value) {
        set(coerceValue(value));
    }

    public void set(T value) {
        getProperty().setValue(value);
    }

    public T coerceValue(Object value) {
        // TODO: Implement type coercion.
        return (T) value;
    }

    @Override
    public Optional<ObservableValue<? extends Object>> getObservableValue() {
        return Optional.of(getProperty());
    }

    @Override
    public Optional<Class<? extends PropertyEditor<?>>> getPropertyEditorClass() {
        if (getType().equals(String.class)) {
            return Optional.of(StringEditor.class);
        }
        return Optional.empty();
    }

    public abstract Property<T> getProperty();

    public static class StringEditor extends AbstractPropertyEditor<String, TextField> {

        public StringEditor(PropertySheet.Item property) {
            this(property, new TextField());
        }

        public StringEditor(PropertySheet.Item property, TextField textField) {
            super(property, textField);
        }

        @Override
        protected ObservableValue<String> getObservableValue() {
            return getEditor().textProperty();
        }

        @Override
        public String getValue() {
            return getEditor().textProperty().get();
        }

        @Override
        public void setValue(String value) {
            getEditor().textProperty().set(value);
        }
    }
}
