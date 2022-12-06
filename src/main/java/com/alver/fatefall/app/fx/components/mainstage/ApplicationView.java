package com.alver.fatefall.app.fx.components.mainstage;

import com.alver.fatefall.app.fx.components.FXMLLoadable;
import com.alver.fatefall.app.fx.components.appinfo.ApplicationInfoView;
import com.alver.fatefall.app.fx.components.settings.SettingsView;
import com.alver.fatefall.app.plugin.PluginManager;
import com.alver.fatefall.app.plugin.interfaces.CardCollectionView;
import com.alver.fatefall.app.plugin.models.CardCollection;
import com.alver.fatefall.app.services.Repository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import org.controlsfx.control.PopOver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ApplicationView extends BorderPane implements FXMLLoadable {

	@Autowired
	protected SettingsView settingsView;
	@Autowired
	protected PluginManager pluginManager;
	@Autowired
	protected ApplicationInfoView applicationInfoView;

	/**
	 * FXML Injection
	 */
	@FXML
	protected ListView<CardCollection> collectionsList;
	@FXML
	protected TabPane tabPane;

	protected Repository repository = new Repository();

	public ApplicationView() {
		loadFxml();
	}

	@FXML
	public void initialize() {
		settingsView = new SettingsView();
		collectionsList.setCellFactory(cardCollectionCellFactory);
		collectionsList.setItems(FXCollections.observableList(repository.getCardCollections()));
	}

	public TabPane getTabPane(){
		return tabPane;
	}

	@FXML
	private void openAppInfo() {
		PopOver popup = new PopOver();
		popup.setContentNode(applicationInfoView);
		popup.show(getScene().getWindow());

	}
	@FXML
	private void openSettings() {
		PopOver popup = new PopOver();
		popup.setContentNode(settingsView);
		popup.show(getScene().getWindow());


	}
	@FXML
	private void openPlugins() {
		PopOver popup = new PopOver();
		popup.setContentNode(pluginManager);
		popup.show(getScene().getWindow());
	}

	private Tab addCollectionTab(CardCollection cardCollection) {
		CardCollectionView cardCollectionView = pluginManager.buildCardCollectionView();
		cardCollectionView.setCardCollection(cardCollection);

		Tab tab = new Tab(cardCollection.getName());
		tab.setContent(cardCollectionView.getFxViewNode());

		tabPane.getTabs().add(tab);
		return tab;
	}

	public void newCollection() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setHeaderText("Create a Collection");
		dialog.setContentText("Enter a name for the new collection.");
		dialog.showAndWait().ifPresent(this::createCollection);
	}

	private CardCollection createCollection(String name) {
		boolean nameAlreadyExists = collectionsList.getItems().stream()
				.map(CardCollection::getName)
				.anyMatch(n -> Objects.equals(n, name));
		if (nameAlreadyExists) {
			throw new RuntimeException("A collection with that name already exists.");
		}
		CardCollection cardCollection = new CardCollection();
		cardCollection.setName(name);
		collectionsList.getItems().add(cardCollection);
		return cardCollection;
	}

	public void saveCollection() {
		CardCollection selectedItem = collectionsList.getSelectionModel().getSelectedItem();
		repository.save(selectedItem);
	}

	private Callback<ListView<CardCollection>, ListCell<CardCollection>> cardCollectionCellFactory = (z) -> {
		ListCell<CardCollection> cell = new ListCell<>() {
			@Override
			protected void updateItem(CardCollection item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? null : item.getName());
			}
		};
		//When double-clicked, open that cardCollection.
		cell.setOnMouseClicked(e -> {
			if (!cell.isEmpty() && cell.getItem() != null && e.getClickCount() == 2) {
				openCollection(cell.getItem());
			}
		});
		ContextMenu contextMenu = new ContextMenu();
		MenuItem save = new MenuItem("Save");
		save.setOnAction(a -> repository.save(cell.getItem()));
		contextMenu.getItems().add(save);

		cell.setContextMenu(contextMenu);
		return cell;
	};

	private void openCollection(CardCollection cardCollection) {
		tabPane.getTabs().stream()
				.filter(tab -> tab.getText().equals(cardCollection.getName()))
				.findFirst()
				.ifPresentOrElse((tab) -> {
					tabPane.getSelectionModel().select(tab);
				}, () -> {
					tabPane.getSelectionModel().select(addCollectionTab(cardCollection));
				});
	}
}