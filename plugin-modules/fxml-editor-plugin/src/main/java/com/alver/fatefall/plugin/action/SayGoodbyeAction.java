package com.alver.fatefall.plugin.action;

import com.alver.fatefall.api.interfaces.ActionEventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import org.pf4j.Extension;

@Extension
public class SayGoodbyeAction implements ActionEventHandler {
    @Override
    public String getName() {
        return "Say Goodbye";
    }

    @Override
    public String getDescription() {
        return "Creates an alert that says goodbye.";
    }

    @Override
    public void handle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Goodbye");
        alert.showAndWait();
        System.exit(0);
    }
}
