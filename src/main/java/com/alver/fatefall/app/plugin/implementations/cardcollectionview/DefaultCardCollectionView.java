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
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
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
    @FXML
    protected TreeTableView<Card> treeTable;

    protected ObjectProperty<CardCollection> cardCollectionProperty = new SimpleObjectProperty<>();

    public final ObjectProperty<CardCollection> cardCollectionProperty() {
        return cardCollectionProperty;
    }

    public DefaultCardCollectionView() {
        super();
    }

    @FXML
    private void initialize() {
        TreeTableColumn<Card, CardView<?>> card = new TreeTableColumn<>("Card");
        card.setCellFactory(new Callback<>() {
            @Override
            public TreeTableCell<Card, CardView<?>> call(TreeTableColumn<Card, CardView<?>> param) {
                TreeTableCell<Card, CardView<?>> cell = new TreeTableCell<>() {
                    @Override
                    protected void updateItem(CardView<?> item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Card card = getTableRow().getTreeItem().getValue();
                            CardView<?> cardView = componentFactory.buildRandomCardView();
                            cardView.setCard(card);
                            setGraphic(cardView.getFxViewNode());
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

        treeTable.getColumns().add(card);


        cardCollectionProperty.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                oldValue.getObservableCards().removeListener(cardListChangeListener);
            }
            if (newValue != null) {
                newValue.getObservableCards().addListener(cardListChangeListener);
            }
            refresh();
        });
    }

    ListChangeListener<? super Card> cardListChangeListener = l -> refresh();

    public void refresh() {
        flowPane.getChildren().clear();
        treeTable.setRoot(new TreeItem<>());
        for (Card card : getCardCollection().getCards()) {
            CardView<?> cardView = componentFactory.buildFlipFacesCardView();
            cardView.setCard(card);
            flowPane.getChildren().add(cardView.getFxViewNode());
            treeTable.getRoot().getChildren().add(new TreeItem<>(card));
        }
    }

}