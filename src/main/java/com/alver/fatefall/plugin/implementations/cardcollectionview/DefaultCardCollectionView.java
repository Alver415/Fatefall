package com.alver.fatefall.plugin.implementations.cardcollectionview;

import com.alver.fatefall.app.fx.components.FxComponent;
import com.alver.fatefall.plugin.PluginManager;
import com.alver.fatefall.plugin.interfaces.CardCollectionView;
import com.alver.fatefall.plugin.interfaces.CardView;
import com.alver.fatefall.plugin.models.Card;
import com.alver.fatefall.plugin.models.CardCollection;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

public class DefaultCardCollectionView extends ScrollPane implements CardCollectionView, FxComponent {

    @FXML
    protected FlowPane flowPane;

    protected ObjectProperty<CardCollection> cardCollectionProperty = new SimpleObjectProperty<>();

    public final ObjectProperty<CardCollection> cardCollectionProperty() {
        return cardCollectionProperty;
    }

    public DefaultCardCollectionView() {
        super();
        initFxml();
        cardCollectionProperty.addListener((observable, oldValue, newValue) -> {
            flowPane.getChildren().clear();
            for (Card card : newValue.getCards()) {
                CardView cardView = PluginManager.buildCardView();
                cardView.setCard(card);
                flowPane.getChildren().add(cardView.getFxViewNode());
            }
        });
    }
}