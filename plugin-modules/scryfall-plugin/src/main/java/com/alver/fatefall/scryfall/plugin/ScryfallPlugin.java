package com.alver.fatefall.scryfall.plugin;


import com.alver.fatefall.plugin.Action;
import com.alver.fatefall.plugin.BasePlugin;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.List;

public class ScryfallPlugin extends BasePlugin {

    public ScryfallPlugin() throws IOException {
    }

    @Override
    public List<Action> getActions(){
        return List.of(new Action() {
            @Override
            public String getName(){
                return "Say Hello";
            }
            @Override
            public String getDescription(){
                return "Say Hello";
            }
            @Override
            public void execute() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Hello!");
                alert.show();
            }
        });
    }
}
