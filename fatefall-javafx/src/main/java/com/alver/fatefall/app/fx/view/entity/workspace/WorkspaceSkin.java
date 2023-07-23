package com.alver.fatefall.app.fx.view.entity.workspace;

import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.view.entity.card.CardView;
import com.alver.fatefall.app.fx.component.settings.FatefallProperties;
import com.alver.fatefall.app.fx.view.entity.card.CardViewImpl;
import com.alver.fatefall.app.services.AsyncService;
import com.alver.fatefall.data.entity.Card;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.FXCollections;
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

import static com.alver.fatefall.app.fx.util.JFXSmoothScroll.smoothScrolling;

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
        AsyncService.runAsync(() -> smoothScrolling(tableView, 0.1), 1000);


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

        TableColumn<Card, String> dataColumn = new TableColumn<>("Data");
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        dataColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dataColumn.setEditable(true);

        tableView.getColumns().add(cardColumn);
        tableView.getColumns().add(dataColumn);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(filterField);
        borderPane.setCenter(tableView);
        getChildren().setAll(borderPane);

        control.workspaceProperty.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
//                oldValue.getObservableCards().removeListener(refreshListener);
            }
            if (newValue != null) {
//                newValue.getObservableCards().addListener(refreshListener);
            }
        });
        refresh();
    }

    private final ListChangeListener<? super Card> refreshListener = l -> refresh();

    private void refresh() {
        ObservableList<Card> cards = FXCollections.observableList(getSkinnable().getWorkspace().getCards().stream().toList());
        FilteredList<Card> filteredList = new FilteredList<>(cards, f -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filteredList.setPredicate(card -> card.getName().contains(newValue));
            }
        });

        tableView.setItems(filteredList);
    }

}
