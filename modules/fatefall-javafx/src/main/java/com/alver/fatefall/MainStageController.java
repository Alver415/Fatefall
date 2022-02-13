package com.alver.fatefall;

import com.alver.fatefall.database.CardCollection;
import com.alver.fatefall.database.DatabaseManager;
import com.alver.fatefall.fx.components.cardcollection.CardCollectionPane;
import com.alver.fatefall.fx.components.cardinfo.CardInfo;
import com.alver.fatefall.services.CardCollectionService;
import com.scryfall.api.ScryfallClient;
import com.scryfall.api.models.Card;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.Objects;

public class MainStageController extends BorderPane {
    public static final URL FXML = MainStageController.class.getResource("card-search.fxml");

    /**
     * Spring Dependency Injection
     */
    @Autowired
    protected ScryfallClient client;
    @Autowired
    protected DatabaseManager databaseManager;
    @Autowired
    protected CardCollectionService cardCollectionService;

    /**
     * FXML Injection
     */
    @FXML
    protected ListView<CardCollection> collectionsList;
    @FXML
    protected Button createCollection;
    @FXML
    protected Button saveCollection;
    @FXML
    protected Button deleteCollection;
    @FXML
    protected Button addCard;
    @FXML
    protected Button removeCard;
    @FXML
    protected TabPane tabPane;
    @FXML
    protected CardInfo cardInfo;

    public MainStageController() {
        FatefallApplication.autowire(this);
    }

    @FXML
    public void initialize() {

        collectionsList.setCellFactory(cardCollectionCellFactory);
        collectionsList.setItems(cardCollectionService.getAllCardCollections());
        collectionsList.getItems().stream().findFirst().ifPresent(this::addCollectionTab);

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            cardInfo.cardProperty().bind(((CardCollectionPane) newValue.getContent()).selectedCardProperty());
        });
    }

    private Tab addCollectionTab(CardCollection cardCollection) {
        CardCollectionPane cardCollectionPane = new CardCollectionPane();
        cardCollectionPane.setCardCollection(cardCollection);

        Tab tab = new Tab(cardCollection.getName());
        tab.setContent(cardCollectionPane);

        tabPane.getTabs().add(tab);
        return tab;
    }

    public void createCollection() {
        FatefallApplication.textDialog("New Collection", "Enter a name for the new collection.")
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
        if (true){
            throw new RuntimeException("Test exception");
        }
        int selectedIndex = collectionsList.getSelectionModel().getSelectedIndex();
        CardCollection selectedItem = collectionsList.getSelectionModel().getSelectedItem();
        CardCollection saved = databaseManager.save(selectedItem);
        collectionsList.getItems().remove(selectedIndex);
        collectionsList.getItems().add(selectedIndex, saved);
        collectionsList.getSelectionModel().select(saved);
    }

    public void deleteCollection() {
        CardCollection selectedItem = collectionsList.getSelectionModel().getSelectedItem();
        collectionsList.getItems().remove(selectedItem);
        databaseManager.delete(selectedItem);
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

    private Callback<ListView<CardCollection>, ListCell<CardCollection>> cardCollectionCellFactory = (a) -> {
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
            if (e.getClickCount() == 2) {
                openCollection(cell.getItem());
            }
        });
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