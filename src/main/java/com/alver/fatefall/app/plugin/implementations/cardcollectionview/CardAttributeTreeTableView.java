package com.alver.fatefall.app.plugin.implementations.cardcollectionview;

import com.alver.fatefall.api.models.Attribute;
import com.alver.fatefall.api.models.attributes.BooleanAttribute;
import com.alver.fatefall.api.models.attributes.DoubleAttribute;
import com.alver.fatefall.api.models.attributes.IntegerAttribute;
import com.alver.fatefall.api.models.attributes.StringAttribute;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class CardAttributeTreeTableView extends TreeTableView<Attribute> {


    public CardAttributeTreeTableView() {
        setShowRoot(false);
        TreeTableColumn<Attribute, String> fieldColumn = new TreeTableColumn<>("Name");
        fieldColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        getColumns().add(fieldColumn);

        TreeTableColumn<Attribute, Object> valueColumn = new TreeTableColumn<>("Value");
        valueColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("value"));
        valueColumn.setCellFactory(param -> new AttributeValueCell());
        getColumns().add(valueColumn);

        fieldColumn.prefWidthProperty().bind(widthProperty().multiply(0.3));
        valueColumn.prefWidthProperty().bind(widthProperty().multiply(0.7));
        setBorder(null);
        setPadding(Insets.EMPTY);
    }

    private static class AttributeValueCell extends TreeTableCell<Attribute, Object> {

        private static final DoubleStringConverter DOUBLE_CONVERTER = new DoubleStringConverter();
        private static final IntegerStringConverter INTEGER_CONVERTER = new IntegerStringConverter();
        private static final BooleanStringConverter BOOLEAN_CONVERTER = new BooleanStringConverter();
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
                Attribute attribute = getTableRow().getTreeItem().getValue();
                Node valueEditor = buildEditor(attribute);
                setGraphic(valueEditor);
            }
        }

        private static <T> Node buildEditor(Attribute<T> attribute) {
            if (attribute instanceof StringAttribute stringAttribute) {
                TextField textField = new TextField();
                textField.textProperty().bindBidirectional(stringAttribute);
                return textField;
            } else if (attribute instanceof DoubleAttribute doubleAttribute) {
                TextField textField = new TextField();
                textField.textProperty().bindBidirectional(doubleAttribute, DOUBLE_CONVERTER);
                return textField;
            } else if (attribute instanceof IntegerAttribute integerAttribute) {
                TextField textField = new TextField();
                textField.textProperty().bindBidirectional(integerAttribute, INTEGER_CONVERTER);
                return textField;
            } else if (attribute instanceof BooleanAttribute booleanAttribute) {
                TextField textField = new TextField();
                textField.textProperty().bindBidirectional(booleanAttribute, BOOLEAN_CONVERTER);
                return textField;
            } else {
                throw new RuntimeException("Unexpected type: " + attribute.getClass());
            }
        }
    }
}
