package com.alver.fatefall.scryfall.plugin;


import com.alver.fatefall.plugin.FatefallPlugin;
import com.alver.fatefall.scryfall.ScryfallConfiguration;
import org.pf4j.PluginWrapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScryfallPlugin extends FatefallPlugin {

    public ScryfallPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    protected AnnotationConfigApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = super.createApplicationContext();
        applicationContext.registerBean(ScryfallPlugin.class, () -> ScryfallPlugin.this);
        applicationContext.register(ScryfallConfiguration.class);

        return applicationContext;
    }


}

