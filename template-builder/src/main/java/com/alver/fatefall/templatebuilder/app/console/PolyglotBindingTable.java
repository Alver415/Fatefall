package com.alver.fatefall.templatebuilder.app.console;

import com.alver.aspect.fxml.FXMLComponent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import org.graalvm.polyglot.Value;

@FXMLComponent
public class PolyglotBindingTable extends TableView<PolyglotBindingTable.Binding> {

    public PolyglotBindingTable() {
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void refresh(Value bindings) {
        getItems().clear();
        for (String key : bindings.getMemberKeys()) {
            Value value = bindings.getMember(key);
            String metaName;
            try {
                metaName = value.getMetaSimpleName();
            } catch (Exception e) {
                metaName = null;
            }
            getItems().add(new Binding(key, metaName, value));
        }
    }

    protected static class Binding {
        private final StringProperty key;
        private final StringProperty type;
        private final ObjectProperty<Value> value;

        public Binding(String key, String type, Value value) {
            this.key = new SimpleStringProperty(key);
            this.type = new SimpleStringProperty(type);
            this.value = new SimpleObjectProperty<>(value);
        }

        public String getKey() {
            return key.get();
        }

        public StringProperty keyProperty() {
            return key;
        }

        public void setKey(String key) {
            this.key.set(key);
        }

        public String getType() {
            return type.get();
        }

        public StringProperty typeProperty() {
            return type;
        }

        public void setType(String type) {
            this.type.set(type);
        }

        public Value getValue() {
            return value.get();
        }

        public ObjectProperty<Value> valueProperty() {
            return value;
        }

        public void setValue(Value value) {
            this.value.set(value);
        }
    }
}
