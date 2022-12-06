package com.alver.fatefall.app.plugin.implementations.cardcollectionview;

import com.alver.fatefall.app.fx.components.FXMLLoadable;
import com.alver.fatefall.app.plugin.PluginManager;
import com.alver.fatefall.app.plugin.interfaces.CardCollectionView;
import com.alver.fatefall.app.plugin.interfaces.CardView;
import com.alver.fatefall.app.plugin.models.Card;
import com.alver.fatefall.app.plugin.models.CardCollection;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DefaultCardCollectionView extends ScrollPane implements CardCollectionView, FXMLLoadable {

    @Autowired
    protected PluginManager pluginManager;

    @FXML
    protected FlowPane flowPane;

    protected ObjectProperty<CardCollection> cardCollectionProperty = new SimpleObjectProperty<>();

    public final ObjectProperty<CardCollection> cardCollectionProperty() {
        return cardCollectionProperty;
    }

    public DefaultCardCollectionView() {
        super();
        loadFxml();
        cardCollectionProperty.addListener((observable, oldValue, newValue) -> {
            flowPane.getChildren().clear();
            for (Card card : newValue.getCards()) {
                CardView cardView = pluginManager.buildCardView();
                cardView.setCard(card);
                flowPane.getChildren().add(cardView.getFxViewNode());
            }
        });
    }
}