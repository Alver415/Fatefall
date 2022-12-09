package com.alver.fatefall.scryfall.plugin;

import com.alver.fatefall.api.interfaces.ActionEventHandler;
import com.alver.fatefall.scryfall.plugin.component.ScryfallSearchView;
import javafx.event.ActionEvent;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Extension
public class CreateScryfallSearchViewAction implements ActionEventHandler {

    @Autowired
    protected ScryfallPlugin plugin;
    @Override
    public String getName() {
        return "New Scryfall Search Tab";
    }

    @Override
    public void handle(ActionEvent event) {
        plugin.createToolTab(getName(), plugin.getApplicationContext().getBean(ScryfallSearchView.class));
        System.out.println("plugin.createToolTab(\"Scryfall Search\", new ScryfallSearchView());");
        System.out.println(plugin);
    }
}
