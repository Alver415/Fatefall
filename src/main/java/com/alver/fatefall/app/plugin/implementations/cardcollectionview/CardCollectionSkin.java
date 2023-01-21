package com.alver.fatefall.app.plugin.implementations.cardcollectionview;

import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardAttribute;
import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.components.settings.FatefallProperties;
import com.alver.fatefall.app.plugin.implementations.cardview.CardViewImpl;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Prototype
public class CardCollectionSkin extends SkinBase<CardCollectionViewImpl> {

    private final FatefallProperties properties;
    private final BeanFactory beanFactory;

    private final TextField filterField;
    private final TableView<Card> tableView;

    @Autowired
    protected CardCollectionSkin(
            CardCollectionViewImpl control,
            FatefallProperties properties,
            BeanFactory beanFactory) {
        super(control);
        this.properties = properties;
        this.beanFactory = beanFactory;

        filterField = new TextField();
        filterField.setPromptText("Filter");

        tableView = new TableView<>();
        tableView.setEditable(true);

        TableColumn<Card, CardView<?>> cardColumn = new TableColumn<>("Card View");
        DoubleBinding columnWidth = properties.getCardScaledWidth().multiply(2).add(20);
        cardColumn.minWidthProperty().bind(columnWidth);
        cardColumn.maxWidthProperty().bind(columnWidth);
        cardColumn.prefWidthProperty().bind(columnWidth);
        cardColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Card, CardView<?>> call(TableColumn<Card, CardView<?>> param) {
                TableCell<Card, CardView<?>> cell = new TableCell<>() {
                    @Override
                    protected void updateItem(CardView<?> item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Card card = getTableRow().getItem();
                            CardView<?> cardView = beanFactory.getBean(CardViewImpl.class);
                            cardView.setCard(card);
                            setGraphic(cardView.getFxViewNode());
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

        TableColumn<Card, Node> attributesColumn = new TableColumn<>("Attributes");
        attributesColumn.setPrefWidth(600);
        attributesColumn.setEditable(true);
        attributesColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Card, Node> call(TableColumn<Card, Node> param) {
                TableCell<Card, Node> cell = new TableCell<>() {
                    @Override
                    protected void updateItem(Node item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else if (getTableRow().getItem() != null){
                            Card card = getTableRow().getItem();
                            CardAttributeTreeTableView view = new CardAttributeTreeTableView();
                            TreeItem<CardAttribute<?>> root = new TreeItem<>();
                            root.setExpanded(true);
                            for (CardAttribute<?> childAttribute : card.getAttributeList()){
                                TreeItem<CardAttribute<?>> childItem = new TreeItem<>(childAttribute);
                                buildTreeItem(childItem);
                                childItem.setExpanded(true);
                                root.getChildren().add(childItem);
                            }
                            view.setRoot(root);
                            ScrollPane scrollPane = new ScrollPane(view);
                            scrollPane.setFitToWidth(true);
                            setGraphic(scrollPane);
                        }
                    }

                    private void buildTreeItem(TreeItem<CardAttribute<?>> parentItem) {
                        CardAttribute<?> parentAttribute = parentItem.getValue();
                        for (CardAttribute<?> childAttribute : parentAttribute.getChildren()){
                            TreeItem<CardAttribute<?>> childItem = new TreeItem<>(childAttribute);
                            childItem.setExpanded(true);
                            buildTreeItem(childItem);
                            parentItem.getChildren().add(childItem);
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
        TableColumn<Card, String> dataColumn = new TableColumn<>("Data");
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        dataColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dataColumn.setEditable(true);

        tableView.getColumns().add(cardColumn);
        tableView.getColumns().add(attributesColumn);
//        tableView.getColumns().add(dataColumn);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(filterField);
        borderPane.setCenter(tableView);
        getChildren().setAll(borderPane);

        control.cardCollectionProperty.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                oldValue.getCardList().removeListener(refreshListener);
            }
            if (newValue != null) {
                newValue.getCardList().addListener(refreshListener);
            }
        });
        refresh();
    }

    private final ListChangeListener<? super Card> refreshListener = l -> refresh();

    private void refresh() {
        ObservableList<Card> cards = getSkinnable().getCardCollection().getCardList();
        FilteredList<Card> filteredList = new FilteredList<>(cards, f -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filteredList.setPredicate(card -> card.getName().contains(newValue));
            }
        });

        tableView.setItems(filteredList);
    }
}
