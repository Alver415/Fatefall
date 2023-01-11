package com.alver.fatefall.scryfall.plugin;


import com.alver.fatefall.api.FatefallPlugin;
import com.alver.fatefall.scryfall.ScryfallConfiguration;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScryfallPlugin extends FatefallPlugin {

    public ScryfallPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    protected ApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        if (wrapper.getPluginManager() instanceof SpringPluginManager springPluginManager) {
            applicationContext.setParent(springPluginManager.getApplicationContext());
        }
        applicationContext.setClassLoader(getWrapper().getPluginClassLoader());
        applicationContext.registerBean(ScryfallPlugin.class, () -> ScryfallPlugin.this);
        applicationContext.register(ScryfallConfiguration.class);

        return applicationContext;
    }


}

