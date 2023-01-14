package com.alver.fatefall.app.plugin.implementations.cardcollectionview;

import com.alver.fatefall.api.interfaces.CardCollectionView;
import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.interfaces.ComponentFactory;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.app.fx.components.FXMLAutoLoad;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@FXMLAutoLoad
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DefaultCardCollectionView extends ScrollPane implements CardCollectionView<DefaultCardCollectionView> {

    @Autowired
    protected ComponentFactory componentFactory;

    @FXML
    protected FlowPane flowPane;

    protected ObjectProperty<CardCollection> cardCollectionProperty = new SimpleObjectProperty<>();

    public final ObjectProperty<CardCollection> cardCollectionProperty() {
        return cardCollectionProperty;
    }

    public DefaultCardCollectionView() {
        super();
        cardCollectionProperty.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null){
                oldValue.getObservableCards().removeListener(cardListChangeListener);
            }
            if (newValue != null){
                newValue.getObservableCards().addListener(cardListChangeListener);
            }
            refresh();
        });
    }

    ListChangeListener<? super Card> cardListChangeListener = l -> refresh();

    public void refresh(){
        flowPane.getChildren().clear();
        for (Card card : getCardCollection().getCards()) {
            CardView<?> cardView = componentFactory.buildCardView();
            cardView.setCard(card);
            flowPane.getChildren().add(cardView.getFxViewNode());
        }
    }
}