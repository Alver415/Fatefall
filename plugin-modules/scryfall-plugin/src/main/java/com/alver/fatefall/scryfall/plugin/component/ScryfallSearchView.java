package com.alver.fatefall.scryfall.plugin.component;

import com.alver.fatefall.api.interfaces.CardCollectionView;
import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.interfaces.ComponentFactory;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.scryfall.api.ScryfallApiClient;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ScryfallSearchView extends BorderPane implements CardCollectionView {

    protected ScryfallApiClient client = new ScryfallApiClient();

    @Autowired
    protected ComponentFactory componentFactory;

    @FXML
    protected TextField queryInput;
    @FXML
    protected FlowPane flowPane;

    public ScryfallSearchView() {
        loadFXML();
        cardCollectionProperty().addListener(change -> {
            refresh();
        });
    }

    private void loadFXML() {
        try {
            URL fxml = getClass().getResource(getClass().getSimpleName() + ".fxml");
            FXMLLoader loader = new FXMLLoader(fxml);
            loader.setRoot(this);
            loader.setController(this);
            loader.setClassLoader(this.getClass().getClassLoader());
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected ObjectProperty<CardCollection> cardCollectionProperty = new SimpleObjectProperty<>();

    @Override
    public ObjectProperty<CardCollection> cardCollectionProperty() {
        return cardCollectionProperty;
    }

    @FXML
    public void executeQuery() {
        String query = queryInput.getText();
        List<Card> results = client.getCardApi().search(query);

        CardCollection newCollection = new CardCollection();
        newCollection.setCards(results);

        setCardCollection(newCollection);
    }

    protected void refresh() {
        flowPane.getChildren().clear();
        for (Card card : getCardCollection().getCards()) {
            CardView cardView = componentFactory.buildCardView();
            cardView.setCard(card);
            Node cardViewNode = cardView.getFxViewNode();
            flowPane.getChildren().add(cardViewNode);
        }
    }
}
