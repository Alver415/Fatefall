package com.other.fatefall.mse.plugin;


import com.alver.fatefall.fx.plugin.FatefallPlugin;
import com.other.fatefall.mse.MSEConfiguration;
import org.pf4j.PluginWrapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MSEPlugin extends FatefallPlugin {

    public MSEPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    protected AnnotationConfigApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = super.createApplicationContext();
        applicationContext.registerBean(MSEPlugin.class, () -> MSEPlugin.this);
        applicationContext.register(MSEConfiguration.class);
        return applicationContext;
    }
}

