package com.alver.fatefall.scryfall.plugin;


import com.alver.fatefall.api.FatefallPluginManager;
import javafx.scene.Node;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScryfallPlugin extends SpringPlugin {

    public ScryfallPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }


    @Override
    public void start() {
        System.out.println("ScryfallPlugin.start()");
    }

    @Override
    public void stop() {
        System.out.println("ScryfallPlugin.stop()");
        super.stop(); // to close applicationContext
    }

    @Override
    protected ApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.setClassLoader(getWrapper().getPluginClassLoader());
        applicationContext.registerBean(ScryfallPlugin.class, () -> ScryfallPlugin.this);
        applicationContext.register(ScryfallConfiguration.class);
        applicationContext.refresh();

        return applicationContext;
    }

    public void createToolTab(String name, Node content){
        PluginManager pluginManager = getWrapper().getPluginManager();
        if (pluginManager instanceof FatefallPluginManager fatefallPluginManager){
            fatefallPluginManager.createToolTab(name, content);
        }
    }

}

