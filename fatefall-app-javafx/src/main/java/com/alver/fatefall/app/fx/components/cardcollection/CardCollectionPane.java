package com.alver.fatefall.app.fx.components.cardcollection;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.app.fx.components.FxComponent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

public class CardCollectionPane extends CardGridPane implements FxComponent {

    public CardCollectionPane() {
        this(CardGridPane.class);
    }
    protected CardCollectionPane(Class<?> clazz) {
        initFxml(clazz);
        cardCollectionProperty.addListener((observable, oldValue, newValue) -> {
            redraw(newValue.getCards());
        });
    }

    protected ObjectProperty<CardCollection> cardCollectionProperty = new SimpleObjectProperty<>();
    public final ObjectProperty<CardCollection> cardCollectionProperty() {
        return cardCollectionProperty;
    }
    public final CardCollection getCardCollection() {
        return cardCollectionProperty.get();
    }
    public final void setCardCollection(CardCollection value) {
        cardCollectionProperty.set(value);
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
                List<Card> cards = scryfallClient.getCardApi().search(query);
                if (cards.isEmpty()) {
                    errorHandler.alert(INFORMATION,
                            "No Results",
                            "There were no results for the search query.",
                            query);
                    return;
                }
                Set<String> resultNames = cards.stream()
                        .map(c -> c.getData().path("name").textValue())
                        .collect(Collectors.toSet());
                List<Card> filtered = getCards().stream()
                        .filter(c -> resultNames.contains(c.getData().path("name").textValue()))
                        .toList();

                runFx(() -> redraw(filtered));
            });
        }
    }
}