package com.alver.fatefall.scryfall.plugin;

import com.alver.fatefall.api.interfaces.ActionEventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import org.pf4j.Extension;

@Extension
public class SayHelloAction implements ActionEventHandler {
    @Override
    public String getName() {
        return "Say Hello";
    }

    @Override
    public void handle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Hello!");
        alert.show();
    }
}
