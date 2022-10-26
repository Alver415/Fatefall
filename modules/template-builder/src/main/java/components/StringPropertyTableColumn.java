package components;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class StringPropertyTableColumn<Item> extends TableColumn<Item, String> {

    public StringPropertyTableColumn() {
        super();
    }

    public StringPropertyTableColumn(String text) {
        super(text);
    }

    public void initialize() {
        setCellValueFactory(new PropertyValueFactory<>("test"));
        setCellFactory(TextFieldTableCell.forTableColumn());
        setOnEditCommit(event -> {
            String newValue = event.getNewValue();
            int row = event.getTablePosition().getRow();
            ObservableList<Item> items = event.getTableView().getItems();
            Item item = items.get(row);
            StringProperty property = propertyAccessor.call(item);
            property.setValue(newValue);
        });
    }

    Callback<Item, StringProperty> propertyAccessor;

    public void setPropertyAccessor(Callback<Item, StringProperty> supplier) {
        this.propertyAccessor = supplier;
    }
}
