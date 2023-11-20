package com.alver.fatefall.fx.app.view.entity.workspace;

import com.alver.fatefall.fx.app.view.entity.card.CardView;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.utils.FXAsyncUtils;
import com.alver.fatefall.fx.core.utils.JFXSmoothScroll;
import javafx.beans.property.ListProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import org.springframework.beans.factory.BeanFactory;

import java.util.function.Function;
import java.util.function.Predicate;

public class WorkspaceSkin extends SkinBase<WorkspaceView> {

	private final TextField filterField;
	private final TableView<CardFX> tableView;

	protected WorkspaceSkin(WorkspaceView control, BeanFactory beanFactory) {
		super(control);

		filterField = new TextField();
		filterField.setPromptText("Filter");

		tableView = new TableView<>();
		tableView.setEditable(true);
		FXAsyncUtils.runAsync(() -> JFXSmoothScroll.smoothScrolling(tableView, 0.1), 1000);

		TableColumn<CardFX, CardFX> cardColumn = new TableColumn<>("Card View");
		cardColumn.setCellFactory(new Callback<>() {
			@Override
			public TableCell<CardFX, CardFX> call(TableColumn<CardFX, CardFX> param) {
				CardView cardView = beanFactory.getBean(CardView.class);
				TableCell<CardFX, CardFX> cell = new TableCell<>() {
					@Override
					protected void updateItem(CardFX item, boolean empty) {
						super.updateItem(item, empty);
						cardView.setCard(getTableRow().getItem());
					}
				};
				cell.setGraphic(cardView);
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});

//        TableColumn<CardFX, String> dataColumn = new TableColumn<>("Data");
//        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
//        dataColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        dataColumn.setEditable(true);

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
