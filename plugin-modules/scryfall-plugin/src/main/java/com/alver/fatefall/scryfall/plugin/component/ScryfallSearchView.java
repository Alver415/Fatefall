package com.alver.fatefall.scryfall.plugin.component;

import com.alver.fatefall.api.interfaces.WorkspaceView;
import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.Workspace;
import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.plugin.implementations.cardview.CardViewImpl;
import com.alver.fatefall.scryfall.api.CardApiResult;
import com.alver.fatefall.scryfall.api.ScryfallApiClient;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Prototype
public class ScryfallSearchView extends BorderPane implements WorkspaceView<ScryfallSearchView> {

    @Autowired
    protected ScryfallApiClient client;
    @Autowired
    protected ScryfallComponentFactory componentFactory;
    @Autowired
    protected BeanFactory beanFactory;

    @FXML
    protected TextField queryInput;
    @FXML
    protected FlowPane flowPane;

    public ScryfallSearchView() {
        loadFXML();
        workspaceProperty().addListener(change -> {
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

    protected ObjectProperty<Workspace> workspaceProperty = new SimpleObjectProperty<>();

    @Override
    public ObjectProperty<Workspace> workspaceProperty() {
        return workspaceProperty;
    }

    @FXML
    public void executeQuery() {
        String query = queryInput.getText();
        CardApiResult result = client.getCardApi().search(query);

        Workspace newCollection = new Workspace();
        List<Card> cards = result.data();
        cards.forEach(card -> newCollection.addCard(card));

        setWorkspace(newCollection);
    }

    protected void refresh() {
        flowPane.getChildren().clear();
        for (Card card : getWorkspace().getCards()) {
            CardView<?> cardView = beanFactory.getBean(CardViewImpl.class);
            cardView.setCard(card);
            Node cardViewNode = cardView.getFxViewNode();
            flowPane.getChildren().add(cardViewNode);
        }
    }
}
