package com.alver.fatefall.scryfall.plugin.actions;

import com.alver.fatefall.app.services.ActionEventHandler;
import com.alver.fatefall.scryfall.plugin.ScryfallPlugin;
import com.alver.fatefall.scryfall.plugin.component.ScryfallSearchView;
import javafx.event.ActionEvent;
import org.pf4j.Extension;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Extension
public class CreateScryfallSearchViewAction implements ActionEventHandler {

    private static final String name = "New Scryfall Search Tab";
    private static final String description = "Query the Scryfall Database to browse cards.";

    @Autowired
    protected ScryfallPlugin plugin;
    @Autowired
    protected BeanFactory beanFactory;


    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void handle(ActionEvent event) {
        plugin.createToolTab(getName(), beanFactory.getBean(ScryfallSearchView.class));
    }
}
