package com.alver.fatefall.scryfall.plugin.component;

import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.entity.CardFX;
import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import com.alver.fatefall.app.fx.view.entity.card.CardView;
import com.alver.fatefall.app.fx.view.entity.card.CardViewImpl;
import com.alver.fatefall.app.fx.view.entity.workspace.WorkspaceView;
import com.alver.fatefall.app.services.FXAsyncUtils;
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

@Prototype
public class ScryfallSearchView extends BorderPane implements WorkspaceView<ScryfallSearchView> {

    @Autowired
    protected ScryfallApiClient client;
    @Autowired
    protected BeanFactory beanFactory;

    @FXML
    protected TextField queryInput;
    @FXML
    protected FlowPane flowPane;


    protected ObjectProperty<WorkspaceFX> workspaceProperty = new SimpleObjectProperty<>();

    @Override
    public ObjectProperty<WorkspaceFX> workspaceProperty() {
        return workspaceProperty;
    }


    public ScryfallSearchView() {
        loadFXML();
        workspaceProperty().addListener(change -> refresh());
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

    @FXML
    public void executeQuery() {
        client.getCardApi().search(queryInput.getText(), result -> {
            WorkspaceFX newWorkspace = new WorkspaceFX();
            newWorkspace.addCards(result.data());
            FXAsyncUtils.runFx(() -> setWorkspace(newWorkspace));
        });
    }

    protected void refresh() {
        flowPane.getChildren().clear();
        for (CardFX card : getWorkspace().getCards()) {
            CardView<?> cardView = beanFactory.getBean(CardViewImpl.class);
            cardView.setCard(card);
            Node cardViewNode = cardView.getFxViewNode();
            flowPane.getChildren().add(cardViewNode);
        }
    }
}
