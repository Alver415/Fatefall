package com.alver.fatefall.app.plugin.implementations.cardcollectionview;

import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.models.Element;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.components.settings.FatefallProperties;
import com.alver.fatefall.app.plugin.implementations.cardview.CardViewImpl;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
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
public class WorkspaceSkin extends SkinBase<WorkspaceViewImpl> {

    private final FatefallProperties properties;
    private final BeanFactory beanFactory;

    private final TextField filterField;
    private final TableView<Card> tableView;

    @Autowired
    protected WorkspaceSkin(
            WorkspaceViewImpl control,
            FatefallProperties properties,
            BeanFactory beanFactory) {
        super(control);
        this.properties = properties;
        this.beanFactory = beanFactory;

        filterField = new TextField();
        filterField.setPromptText("Filter");

        tableView = new TableView<>();
        tableView.setEditable(true);
        tableView.setBorder(null);
        tableView.setPadding(Insets.EMPTY);

        TableColumn<Card, CardView<?>> cardColumn = new TableColumn<>("Card View");
        DoubleBinding cardViewMaxWidth = properties.getCardScaledWidth().multiply(2).add(20);
        cardColumn.minWidthProperty().bind(cardViewMaxWidth);
        cardColumn.maxWidthProperty().bind(cardViewMaxWidth);
        cardColumn.prefWidthProperty().bind(cardViewMaxWidth);
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

        LoadingCache<Card, ScrollPane> cache = CacheBuilder.newBuilder().build(new CacheLoader<>() {
            public ScrollPane load(Card card) { // no checked exception
                CardAttributeTreeTableView view = new CardAttributeTreeTableView();
                TreeItem<Element> root = new TreeItem<>();
                root.setExpanded(true);
                for (Element childAttribute : card.getElement().values()){
                    TreeItem<Element> childItem = new TreeItem<>(childAttribute);
                    buildTreeItem(childItem);
                    childItem.setExpanded(true);
                    root.getChildren().add(childItem);
                }
                view.setRoot(root);
                ScrollPane scrollPane = new ScrollPane(view);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setFitToWidth(true);
                return scrollPane;
            }
        });
        TableColumn<Card, Node> attributesColumn = new TableColumn<>("Attributes");
        attributesColumn.prefWidthProperty().bind(getSkinnable().widthProperty().subtract(cardColumn.widthProperty()));
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
                            setGraphic(cache.getUnchecked(card));
                        }
                    }

                };
                cell.setAlignment(Pos.CENTER);
                cell.setBorder(null);
                cell.setPadding(Insets.EMPTY);
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

        control.workspaceProperty.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                oldValue.getObservableCards().removeListener(refreshListener);
            }
            if (newValue != null) {
                newValue.getObservableCards().addListener(refreshListener);
            }
        });
        refresh();
    }

    private final ListChangeListener<? super Card> refreshListener = l -> refresh();

    private void refresh() {
        ObservableList<Card> cards = getSkinnable().getWorkspace().getObservableCards();
        FilteredList<Card> filteredList = new FilteredList<>(cards, f -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filteredList.setPredicate(card -> card.getName().contains(newValue));
            }
        });

        tableView.setItems(filteredList);
    }

    private void buildTreeItem(TreeItem<Element> parentItem) {
        Element parentAttribute = parentItem.getValue();
        for (Element childAttribute : parentAttribute.getElement().values()){
            TreeItem<Element> childItem = new TreeItem<>(childAttribute);
            childItem.setExpanded(true);
            buildTreeItem(childItem);
            parentItem.getChildren().add(childItem);
        }
    }
}
