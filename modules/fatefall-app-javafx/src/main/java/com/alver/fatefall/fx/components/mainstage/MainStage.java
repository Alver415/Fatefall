package com.alver.fatefall.fx.components.mainstage;

import com.alver.fatefall.FxComponent;
import com.alver.fatefall.api.client.FatefallApiClient;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.fx.components.cardcollection.CardCollectionPane;
import com.alver.fatefall.fx.components.cardcollection.CardGridPane;
import com.alver.fatefall.fx.components.cardcollection.ScryfallSearchPane;
import com.alver.fatefall.fx.components.cardinfo.CardInfo;
import com.alver.fatefall.fx.components.settings.Settings;
import com.alver.fatefall.services.DialogService;
import com.alver.scryfall.api.ScryfallApiClient;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

public class MainStage extends Stage implements FxComponent {

    private static final Logger LOGGER = LogManager.getLogger(MainStage.class);

    /**
     * Spring Dependency Injection
     */
    @Autowired
    protected ScryfallApiClient client;
    @Autowired
    protected FatefallApiClient fatefallApiClient;
    @Autowired
    protected DialogService dialogService;
    @Autowired
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
    protected MenuItem importFromMse;

    @FXML
    protected MenuItem openSettings;

    public MainStage() {
        initFxml();
    }

    @FXML
    public void initialize() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                //This *should* only happen if there are no more tabs.
                addScryfallTab();
            }
        });
        //So there's always something there on startup.
        addScryfallTab();

        collectionsList.setCellFactory(cardCollectionCellFactory);
        collectionsList.setItems(FXCollections.observableList(fatefallApiClient.getCardCollectionApi().findAll()));
        collectionsList.getItems().stream().findFirst().ifPresent(this::addCollectionTab);

        newCollection.setOnAction(a -> newCollection());
        saveCollection.setOnAction(a -> saveCollection());
        importFromMse.setOnAction(a -> importFromMse());

    }
    private void importFromMse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import from Magic Set Editor");
        File file = fileChooser.showOpenDialog(this);

        String name = dialogService.textInput(
                        "Import from Magic Set Editor",
                        "Enter a name for the new collection.")
                .orElse(file.getName());

        if (!file.getName().endsWith(".mse-set")) {
            return;
        }

        runAsync(() -> {
            CardCollection cardCollection = fatefallApiClient.getCardCollectionApi().importFromMse(name, file);
            runFx(() -> {
                collectionsList.getItems().add(cardCollection);
            });
        });

    }

    private void addScryfallTab() {
        Tab scryfallTab = new Tab("Scryfall");
        scryfallTab.setOnCloseRequest(Event::consume);
        scryfallTab.setContent(new ScryfallSearchPane());
        tabPane.getTabs().add(scryfallTab);
    }

    @FXML
    private void openSettings() {
        settings.show();
    }

    private Tab addCollectionTab(CardCollection cardCollection) {
        CardCollectionPane cardCollectionPane = new CardCollectionPane();
        cardCollectionPane.setCardCollection(cardCollection);

        Tab tab = new Tab(cardCollection.getName());
        tab.setContent(cardCollectionPane);

        tabPane.getTabs().add(tab);
        return tab;
    }

    public void newCollection() {
        Optional<String> response = dialogService
                .textInput("New Collection", "Enter a name for the new collection.");
        response.ifPresent(this::createCollection);
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
        fatefallApiClient.getCardCollectionApi().save(selectedItem);
    }

    private Callback<ListView<CardCollection>, ListCell<CardCollection>> cardCollectionCellFactory = (z) -> {
        ListCell<CardCollection> cell = new ListCell<>() {
            @Override
            protected void updateItem(CardCollection item, boolean empty) {
                super.updateItem(item, empty);
                setText(null);
                if (!empty) {
                    //Show name with unsaved symbol is pk is null (not hibernate controlled).
                    String unsavedSymbol = item.getPk() == null ? " *" : "";
                    setText(item.getName() + unsavedSymbol);
                }
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
        save.setOnAction(a -> fatefallApiClient.getCardCollectionApi().save(cell.getItem()));
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