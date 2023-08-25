package com.alver.fatefall.app.fx.view.entity.workspace;

import com.alver.fatefall.app.fx.entity.CardFX;
import com.alver.fatefall.app.fx.view.entity.card.CardView;
import com.alver.fatefall.utils.FXAsyncUtils;
import javafx.beans.property.ListProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.alver.fatefall.utils.JFXSmoothScroll.smoothScrolling;

public class WorkspaceSkin extends SkinBase<WorkspaceView> {

    private final TextField filterField;
    private final TableView<CardFX> tableView;

    protected WorkspaceSkin(WorkspaceView control) {
        super(control);

        filterField = new TextField();
        filterField.setPromptText("Filter");

        tableView = new TableView<>();
        tableView.setEditable(true);
        FXAsyncUtils.runAsync(() -> smoothScrolling(tableView, 0.1), 1000);

        TableColumn<CardFX, CardView> cardColumn = new TableColumn<>("Card View");
//        DoubleBinding cardViewMaxWidth = properties.getCardScaledWidth().multiply(2).add(20);
//        cardColumn.minWidthProperty().bind(cardViewMaxWidth);
//        cardColumn.maxWidthProperty().bind(cardViewMaxWidth);
//        cardColumn.prefWidthProperty().bind(cardViewMaxWidth);
        cardColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<CardFX, CardView> call(TableColumn<CardFX, CardView> param) {
                TableCell<CardFX, CardView> cell = new TableCell<>() {
                    @Override
                    protected void updateItem(CardView item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            CardFX card = getTableRow().getItem();
                            CardView cardView = new CardView();
                            cardView.setCard(card);
                            setGraphic(cardView);
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

        TableColumn<CardFX, String> dataColumn = new TableColumn<>("Data");
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        dataColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dataColumn.setEditable(true);

        tableView.getColumns().add(cardColumn);
//        tableView.getColumns().add(dataColumn);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(filterField);
        borderPane.setCenter(tableView);
        getChildren().setAll(borderPane);

        control.workspaceProperty.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                oldValue.cardsProperty().removeListener(refreshListener);
            }
            if (newValue != null) {
                newValue.cardsProperty().addListener(refreshListener);
            }
        });
        refresh();
    }

    private final ListChangeListener<? super CardFX> refreshListener = l -> refresh();

    private void refresh() {
        ListProperty<CardFX> cards = getSkinnable().getWorkspace().cardsProperty();
        FilteredList<CardFX> filtered = new FilteredList<>(cards);
        Function<String, Predicate<? super CardFX>> filterFunc = filter -> card ->
                card.getName().toLowerCase().contains(filter.toLowerCase());
        filtered.predicateProperty().bind(filterField.textProperty().map(filterFunc));
        tableView.setItems(filtered);
    }

}
