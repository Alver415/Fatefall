package com.alver.fatefall.scryfall.plugin.actions;

import com.alver.fatefall.fx.core.interfaces.AppEvent;
import com.alver.fatefall.fx.core.interfaces.AppView;
import com.alver.fatefall.scryfall.plugin.ScryfallPlugin;
import com.alver.fatefall.scryfall.plugin.component.ScryfallSearchController;
import com.alver.springfx.SpringFX;
import com.alver.springfx.model.FXMLControllerAndView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import org.pf4j.Extension;
import org.pf4j.ExtensionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Extension
public class CreateScryfallSearchViewAction implements AppEvent, ExtensionPoint {

    private static final String title = "Scryfall Search Tab";
    private static final String description = "Query the Scryfall Database to browse cards.";

    @Autowired
    protected ScryfallPlugin plugin;

    @Autowired
    protected SpringFX loader;


    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void handle(ActionEvent event) {
        FXMLControllerAndView<ScryfallSearchController, Object> load = loader.load(ScryfallSearchController.class);
        plugin.addView(AppView.of(title, (Node) load.view()));
    }
}
