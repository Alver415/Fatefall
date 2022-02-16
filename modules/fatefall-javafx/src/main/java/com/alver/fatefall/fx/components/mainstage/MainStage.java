package com.alver.fatefall.fx.components.mainstage;

import com.alver.fatefall.FxComponent;
import com.alver.fatefall.fx.components.cardcollection.CardCollectionPane;
import com.alver.fatefall.fx.components.cardcollection.CardGridPane;
import com.alver.fatefall.fx.components.cardcollection.ScryfallSearchPane;
import com.alver.fatefall.fx.components.cardinfo.CardInfo;
import com.alver.fatefall.fx.components.settings.Settings;
import com.alver.fatefall.repositories.DatabaseManager;
import com.alver.fatefall.repositories.models.CardCollection;
import com.alver.fatefall.services.CardCollectionService;
import com.alver.fatefall.services.DialogService;
import com.scryfall.api.ScryfallClient;
import com.scryfall.api.models.Card;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.beans.EventHandler;
import java.util.Objects;

public class MainStage extends Stage implements FxComponent {

    /**
     * Spring Dependency Injection
     */
    @Autowired
    protected ScryfallClient client;
    @Autowired
    protected DatabaseManager databaseManager;
    @Autowired
    protected CardCollectionService cardCollectionService;
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
    protected CardInfo cardInfo;

    @FXML
    protected MenuItem newCollection;
    @FXML
    protected MenuItem saveCollection;

    @FXML
    protected MenuItem openSettings;

    public MainStage() {
        initFxml();
    }

    @FXML
    public void initialize() {
        addScryfallTab();

        collectionsList.setCellFactory(cardCollectionCellFactory);
        collectionsList.setItems(cardCollectionService.getAllCardCollections());
        collectionsList.getItems().stream().findFirst().ifPresent(this::addCollectionTab);

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                addScryfallTab();
            } else if (newValue.getContent() instanceof CardGridPane){
                cardInfo.cardProperty().bind(((CardGridPane) newValue.getContent()).selectedCardProperty());
            }
        });

        newCollection.setOnAction(a -> newCollection());
        saveCollection.setOnAction(a -> saveCollection());
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
        dialogService.textInput("New Collection", "Enter a name for the new collection.")
                .ifPresent(name -> {
                    boolean nameAlreadyExists = collectionsList.getItems().stream()
                            .map(CardCollection::getName)
                            .anyMatch(n -> Objects.equals(n, name));
                    if (nameAlreadyExists) {
                        throw new RuntimeException("A collection with that name already exists.");
                    }
                    CardCollection cardCollection = new CardCollection();
                    cardCollection.setName(name);
                    collectionsList.getItems().add(cardCollection);
                });
    }

    public void saveCollection() {
        CardCollection selectedItem = collectionsList.getSelectionModel().getSelectedItem();
        cardCollectionService.save(selectedItem);
    }

    public void addCard() {
        CardCollection selectedCollection = collectionsList.getSelectionModel().getSelectedItem();
        Card selectedCard = cardInfo.getCard();
        selectedCollection.addCards(selectedCard);
    }

    public void removeCard() {
        CardCollection selectedCollection = collectionsList.getSelectionModel().getSelectedItem();
        Card selectedCard = cardInfo.getCard();
        selectedCollection.removeCards(selectedCard);
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
        save.setOnAction(a -> cardCollectionService.save(cell.getItem()));
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