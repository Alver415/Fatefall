package com.alver.fatefall.app.fx.components.mainstage;

import com.alver.fatefall.app.fx.components.FxComponent;
import com.alver.fatefall.app.fx.components.settings.Settings;
import com.alver.fatefall.app.services.Repository;
import com.alver.fatefall.plugin.PluginManager;
import com.alver.fatefall.plugin.interfaces.CardCollectionView;
import com.alver.fatefall.plugin.models.CardCollection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class MainStage extends Stage implements FxComponent {

	private static final Logger LOGGER = LogManager.getLogger(MainStage.class);

	protected Settings settings;

	/**
	 * FXML Injection
	 */
	@FXML
	protected ListView<CardCollection> collectionsList;
	@FXML
	protected TabPane tabPane;

	@FXML
	protected MenuItem newCollection;
	@FXML
	protected MenuItem saveCollection;

	@FXML
	protected MenuItem openSettings;
	@FXML
	protected MenuItem openPlugins;

	protected Repository repository = new Repository();

	public MainStage() {
		initFxml();
	}

	@FXML
	public void initialize() {
		collectionsList.setCellFactory(cardCollectionCellFactory);
		collectionsList.setItems(FXCollections.observableList(repository.getCardCollections()));

		newCollection.setOnAction(a -> newCollection());
		saveCollection.setOnAction(a -> saveCollection());

		PluginManager.setMainStage(this);
	}

	public TabPane getTabPane(){
		return tabPane;
	}

	@FXML
	private void openSettings() {
		settings.show();
	}
	@FXML
	private void openPlugins() {
		PluginManager.show();
	}

	private Tab addCollectionTab(CardCollection cardCollection) {
		CardCollectionView cardCollectionView = PluginManager.buildCardCollectionView();
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