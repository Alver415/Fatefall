package com.alver.fatefall.app.plugin.implementations.cardcollectionview;

import com.alver.fatefall.api.models.Element;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

public class CardAttributeTreeTableView extends TreeTableView<Element> {


    public CardAttributeTreeTableView() {
        setShowRoot(false);
        TreeTableColumn<Element, String> fieldColumn = new TreeTableColumn<>("Name");
        fieldColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        getColumns().add(fieldColumn);

        TreeTableColumn<Element, Object> valueColumn = new TreeTableColumn<>("Value");
        valueColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("value"));
        valueColumn.setCellFactory(param -> new AttributeValueCell());
        getColumns().add(valueColumn);

        fieldColumn.prefWidthProperty().bind(widthProperty().multiply(0.3));
        valueColumn.prefWidthProperty().bind(widthProperty().multiply(0.7));
        setBorder(null);
        setPadding(Insets.EMPTY);
    }

    private static class AttributeValueCell extends TreeTableCell<Element, Object> {

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
                Element attribute = getTableRow().getTreeItem().getValue();
                Node valueEditor = buildEditor(attribute);
                setGraphic(valueEditor);
            }
        }

        private static Node buildEditor(Element attribute) {
            AttributeControl attributeControl = new AttributeControl();
            attributeControl.attributeProperty.set(attribute);
            return attributeControl;
        }
    }
}
