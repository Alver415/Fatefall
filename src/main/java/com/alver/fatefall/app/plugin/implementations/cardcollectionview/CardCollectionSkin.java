package com.alver.fatefall.app.plugin.implementations.cardcollectionview;

import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.interfaces.ComponentFactory;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.components.settings.FatefallProperties;
import com.alver.fatefall.app.plugin.implementations.cardview.CardViewImpl;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Prototype
public class CardCollectionSkin extends SkinBase<CardCollectionViewImpl> {

	private final FatefallProperties properties;
	private final BeanFactory beanFactory;

	private final TreeTableView<Card> tableView;

	@Autowired
	protected CardCollectionSkin(
			CardCollectionViewImpl control,
			FatefallProperties properties,
			BeanFactory beanFactory) {
		super(control);
		this.properties = properties;
		this.beanFactory = beanFactory;

		tableView = new TreeTableView<>(new TreeItem<>());
		tableView.setShowRoot(false);
		tableView.setEditable(true);

		TreeTableColumn<Card, CardView<?>> cardColumn = new TreeTableColumn<>("Card View");
		DoubleBinding columnWidth = properties.getCardScaledWidth().multiply(2).add(20);
		cardColumn.minWidthProperty().bind(columnWidth);
		cardColumn.maxWidthProperty().bind(columnWidth);
		cardColumn.prefWidthProperty().bind(columnWidth);
		cardColumn.setCellFactory(new Callback<>() {
			@Override
			public TreeTableCell<Card, CardView<?>> call(TreeTableColumn<Card, CardView<?>> param) {
				TreeTableCell<Card, CardView<?>> cell = new TreeTableCell<>() {
					@Override
					protected void updateItem(CardView<?> item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							Card card = getTableRow().getTreeItem().getValue();
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

		TreeTableColumn<Card, String> dataColumn = new TreeTableColumn<>("Data");
		dataColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("data"));
		dataColumn.setCellFactory(TextAreaTreeTableCell.forTreeTableColumn());
		dataColumn.setEditable(true);

		tableView.getColumns().add(cardColumn);
		tableView.getColumns().add(dataColumn);

		getChildren().add(tableView);

		control.cardCollectionProperty.addListener((observable, oldValue, newValue) -> {
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

	private void refresh(){
		tableView.setRoot(new TreeItem<>());
		CardCollection cardCollection = getSkinnable().cardCollectionProperty.get();
		if (cardCollection == null){
			return;
		}
		for (Card card : cardCollection.getCards()){
			tableView.getRoot().getChildren().add(new TreeItem<>(card));
		}
	}
}
