package com.alver.fatefall.scryfall.plugin.component;

import com.alver.fatefall.app.fx.model.entity.CardFX;
import com.alver.fatefall.app.fx.model.entity.WorkspaceFX;
import com.alver.fatefall.app.fx.view.entity.card.CardView;
import com.alver.fatefall.scryfall.api.ScryfallApiClient;
import com.alver.fatefall.utils.FXAsyncUtils;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLPrototype
public class ScryfallSearchController {

    @Autowired
    protected ScryfallApiClient client;
    @Autowired
    protected BeanFactory beanFactory;

    @FXML
    protected TextField queryInput;
    @FXML
    protected FlowPane flowPane;


    protected final ObjectProperty<WorkspaceFX> workspaceProperty = new SimpleObjectProperty<>();
    public ObjectProperty<WorkspaceFX> workspaceProperty() {
        return workspaceProperty;
    }
    public WorkspaceFX getWorkspace() {
        return workspaceProperty().get();
    }
    public void setWorkspace(WorkspaceFX workspace) {
        workspaceProperty().set(workspace);
    }

    @FXML
    public void initialize(){
        workspaceProperty().addListener(change -> refresh());
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
            CardView cardView = beanFactory.getBean(CardView.class);
            cardView.setCard(card);
            flowPane.getChildren().add(cardView);
        }
    }
}
