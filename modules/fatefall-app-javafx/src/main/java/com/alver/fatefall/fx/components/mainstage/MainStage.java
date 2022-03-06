package com.alver.fatefall.fx.components.mainstage;

import com.alver.fatefall.FxComponent;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.client.FatefallApiClient;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.api.models.ImageUri;
import com.alver.fatefall.api.models.Layouts;
import com.alver.fatefall.fx.components.cardcollection.CardCollectionPane;
import com.alver.fatefall.fx.components.cardcollection.CardGridPane;
import com.alver.fatefall.fx.components.cardcollection.ScryfallSearchPane;
import com.alver.fatefall.fx.components.cardinfo.CardInfo;
import com.alver.fatefall.fx.components.settings.Settings;
import com.alver.fatefall.services.DialogService;
import com.alver.scryfall.api.ScryfallClient;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import mse.SetManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MainStage extends Stage implements FxComponent {

    private static final Logger LOGGER = LogManager.getLogger(MainStage.class);

    /**
     * Spring Dependency Injection
     */
    @Autowired
    protected ScryfallClient client;
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
    protected CardInfo cardInfo;

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
        addScryfallTab();

        collectionsList.setCellFactory(cardCollectionCellFactory);
        collectionsList.setItems(FXCollections.observableList(fatefallApiClient.getCardCollectionApi().findAll()));
        collectionsList.getItems().stream().findFirst().ifPresent(this::addCollectionTab);

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                addScryfallTab();
            } else if (newValue.getContent() instanceof CardGridPane) {
                cardInfo.cardProperty().bind(((CardGridPane) newValue.getContent()).selectedCardProperty());
            }
        });

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

        try {
            if (!file.getName().endsWith(".mse-set")) {
                return;
            }
            SetManager setManager = SetManager.importMseSet(name, file.toPath());
            List<Card> convertedCards = setManager.getSet().cards.stream().map(c -> {
                        String cardName = c.fields.get("name");
                        String fileName = cardName
                                .replace("\"", "")
                                .replace(",", "")
                                .replace(" ", "_");
                        String image = setManager.getImagesPath()
                                .resolve(fileName + ".png")
                                .toFile().toURI().toString();
                        return new Card()
                                .withLayout(Layouts.NORMAL)
                                .withName(cardName)
                                .withManaCost(c.fields.get("casting_cost"))
                                .withImageUris(new ImageUri()
                                        .withNormal(image)
                                        .withPng(image)
                                );
                    })
                    .toList();

            CardCollection collection = createCollection(name);
            collection.getCards().addAll(convertedCards);
        } catch (IOException e) {
            LOGGER.error("Failed to import from MSE.", e);
            dialogService.error("Must have extension .mse-set");
        }
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
//        cardCollectionService.save(selectedItem);
    }

    public void addCard() {
        CardCollection selectedCollection = collectionsList.getSelectionModel().getSelectedItem();
        Card selectedCard = cardInfo.getCard();
//        selectedCollection.addCards(selectedCard);
    }

    public void removeCard() {
        CardCollection selectedCollection = collectionsList.getSelectionModel().getSelectedItem();
        Card selectedCard = cardInfo.getCard();
//        selectedCollection.removeCards(selectedCard);
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
//        save.setOnAction(a -> cardCollectionService.save(cell.getItem()));
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