package com.alver.fatefall.fx.components.cardcollection;

import com.alver.fatefall.FxComponent;
import com.alver.scryfall.api.models.Card;
import com.alver.scryfall.api.models.CardCollection;
import com.alver.scryfall.api.models.CardList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

public class CardCollectionPane extends CardGridPane implements FxComponent {

    protected ObjectProperty<CardCollection> cardCollectionProperty = new SimpleObjectProperty<>();
    public final ObjectProperty<CardCollection> cardCollectionProperty() {
        return cardCollectionProperty;
    }
    public final void setCardCollection(CardCollection value) {
        cardCollectionProperty.set(value);
    }
    public final CardCollection getCardCollection() {
        return cardCollectionProperty.get();
    }

    public CardCollectionPane() {
        this(CardGridPane.class);
    }
    protected CardCollectionPane(Class<?> clazz) {
        initFxml(clazz);
        cardCollectionProperty.addListener((observable, oldValue, newValue) -> {
//            newValue.getCards().addListener((ListChangeListener<? super Card>) c ->{
//                redraw(newValue.getCards());
//            });
            redraw(newValue.getCards());
        });
    }

    public ObservableList<Card> getCards() {
        return FXCollections.observableList(getCardCollection().getCards());
    }

    @Override
    public void search(String query) {
        if (query.isBlank()) {
            redraw(getCardCollection().getCards());
        } else {
            runAsync(() -> {
                CardList cards = client.cards().search(query);
                if (cards == null || cards.data().isEmpty()) {
                    errorHandler.alert(INFORMATION,
                            "No Results",
                            "There were no results for the search query.",
                            query);
                    return;
                }
                Set<String> resultNames = cards.data().stream().map(Card::name).collect(Collectors.toSet());
                List<Card> filtered = getCards().stream()
                        .filter(c -> {
                            return resultNames.contains(c.name());
                        }).toList();

                runFx(() -> redraw(filtered));
            });
        }
    }
}