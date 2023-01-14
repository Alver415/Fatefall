package com.other.fatefall.actions;

import com.alver.fatefall.api.interfaces.ActionEventHandler;
import com.alver.fatefall.api.models.Card;
import com.other.fatefall.components.FxmlEditorCardView;
import com.other.fatefall.plugin.FxmlEditorPlugin;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Extension
public class CreateFXMLEditorCardViewAction implements ActionEventHandler {

    @Autowired
    protected FxmlEditorPlugin plugin;

    @Override
    public String getName() {
        return "FXML Editor";
    }

    @Override
    public void handle(ActionEvent event) {
        FxmlEditorCardView cardView = plugin.getApplicationContext().getBean(FxmlEditorCardView.class);
        cardView.setCard(new Card());
        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-border-color:red;");
        stackPane.getChildren().add(cardView);
        plugin.createToolTab(getName(), stackPane);
    }
}
