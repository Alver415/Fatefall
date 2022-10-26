package app;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.StringProperty;

public class CardProperty {
    public ReadOnlyStringProperty id;

    public ReadOnlyStringProperty idProperty() {
        return id;
    }

    public StringProperty value;

    public StringProperty valueProperty() {
        return value;
    }
}
