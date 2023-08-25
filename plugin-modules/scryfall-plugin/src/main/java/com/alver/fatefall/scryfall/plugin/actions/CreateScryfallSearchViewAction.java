package com.alver.fatefall.scryfall.plugin.actions;

import com.alver.fatefall.action.ActionEventHandler;
import com.alver.fatefall.scryfall.plugin.ScryfallPlugin;
import com.alver.fatefall.scryfall.plugin.component.ScryfallSearchController;
import com.alver.springfx.SpringFXLoader;
import com.alver.springfx.model.FXMLControllerAndView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Extension
public class CreateScryfallSearchViewAction implements ActionEventHandler {

    private static final String name = "New Scryfall Search Tab";
    private static final String description = "Query the Scryfall Database to browse cards.";

    @Autowired
    protected ScryfallPlugin plugin;

    @Autowired
    protected ApplicationContext context;


    @Override
    public String getTitle() {
        return name;
    }
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void handle(ActionEvent event) {
        FXMLControllerAndView<ScryfallSearchController, Object> load = new SpringFXLoader(context, null).load(ScryfallSearchController.class);
        plugin.createToolTab(getTitle(), (Node) load.view());
    }
}
