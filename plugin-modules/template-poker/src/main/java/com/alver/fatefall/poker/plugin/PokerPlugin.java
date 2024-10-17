package com.alver.fatefall.poker.plugin;

import com.alver.fatefall.fx.plugin.FatefallPlugin;
import com.alver.fatefall.poker.PokerConfiguration;
import org.pf4j.PluginWrapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PokerPlugin extends FatefallPlugin {

    public PokerPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }
    @Override
    protected AnnotationConfigApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = super.createApplicationContext();
        applicationContext.registerBean(PokerPlugin.class, () -> PokerPlugin.this);
        applicationContext.register(PokerConfiguration.class);

        return applicationContext;
    }

}
