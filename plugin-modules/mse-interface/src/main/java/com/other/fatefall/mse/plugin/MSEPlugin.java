package com.other.fatefall.mse.plugin;


import com.alver.fatefall.api.FatefallPlugin;
import com.other.fatefall.mse.MSEConfiguration;
import org.pf4j.PluginWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MSEPlugin extends FatefallPlugin {

    public MSEPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    protected ApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.setClassLoader(getWrapper().getPluginClassLoader());
        applicationContext.registerBean(MSEPlugin.class, () -> MSEPlugin.this);
        applicationContext.register(MSEConfiguration.class);
        applicationContext.refresh();

        return applicationContext;
    }
}

