package com.alver.fatefall.app.fx.view.entity.field;

import com.alver.fatefall.data.entity.Field;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

public class FieldTreeTableView extends TreeTableView<Field> {


    public FieldTreeTableView() {
        setShowRoot(false);
        TreeTableColumn<Field, String> fieldColumn = new TreeTableColumn<>("Name");
        fieldColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        getColumns().add(fieldColumn);

        TreeTableColumn<Field, Object> valueColumn = new TreeTableColumn<>("Value");
        valueColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("value"));
        valueColumn.setCellFactory(param -> new AttributeValueCell());
        getColumns().add(valueColumn);

        fieldColumn.prefWidthProperty().bind(widthProperty().multiply(0.3));
        valueColumn.prefWidthProperty().bind(widthProperty().multiply(0.7));
        setBorder(null);
        setPadding(Insets.EMPTY);
    }

    private static class AttributeValueCell extends TreeTableCell<Field, Object> {

        {
            setBorder(null);
            setPadding(Insets.EMPTY);
        }

        @Override
        public void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                Field attribute = getTableRow().getTreeItem().getValue();
                Node valueEditor = buildEditor(attribute);
                setGraphic(valueEditor);
            }
        }

        private static Node buildEditor(Field attribute) {
            FieldControl attributeControl = new FieldControl();
            attributeControl.attributeProperty.set(attribute);
            return attributeControl;
        }
    }
}
