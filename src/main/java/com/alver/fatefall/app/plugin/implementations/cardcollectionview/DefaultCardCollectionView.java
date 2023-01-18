package com.alver.fatefall.app.plugin.implementations.cardcollectionview;

import com.alver.fatefall.api.interfaces.CardCollectionView;
import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.interfaces.ComponentFactory;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.app.fx.components.FXMLAutoLoad;
import com.alver.fatefall.app.fx.components.settings.FatefallProperties;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@FXMLAutoLoad
@Component
@Scope(SCOPE_PROTOTYPE)
public class DefaultCardCollectionView extends ScrollPane implements CardCollectionView<DefaultCardCollectionView> {

    @Autowired
    protected ComponentFactory componentFactory;
    @Autowired
    protected FatefallProperties properties;

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
        treeTable.setEditable(true);
        treeTable.setTableMenuButtonVisible(true);

        TreeTableColumn<Card, CardView<?>> cardColumn = new TreeTableColumn<>("Card");
        DoubleBinding columnWidth = properties.getCardScaledWidth().multiply(2).add(20);
        cardColumn.minWidthProperty().bind(columnWidth);
        cardColumn.maxWidthProperty().bind(columnWidth);
        cardColumn.prefWidthProperty().bind(columnWidth);
        cardColumn.setCellFactory(new Callback<>() {
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
                            CardView<?> cardView = componentFactory.buildCardView(card);
                            setGraphic(cardView.getFxViewNode());
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

        TreeTableColumn<Card, String> dataColumn = new TreeTableColumn<>("Data");
        dataColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("data"));
        dataColumn.setCellFactory(TextAreaTreeTableCell.forTreeTableColumn());
        dataColumn.setEditable(true);

        treeTable.getColumns().add(cardColumn);
        treeTable.getColumns().add(dataColumn);

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
            CardView<?> cardView = componentFactory.buildCardView(card);
            flowPane.getChildren().add(cardView.getFxViewNode());
            treeTable.getRoot().getChildren().add(new TreeItem<>(card));
        }
    }

}