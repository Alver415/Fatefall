package com.alver.fatefall.api;


import com.alver.fatefall.api.models.CardCollection;
import javafx.scene.Node;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class FatefallPlugin extends SpringPlugin {

    public FatefallPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }


    @Override
    public void start() {
        System.out.println("START: " + getWrapper().getPluginId());
        ((AnnotationConfigApplicationContext) getApplicationContext()).refresh();
    }

    @Override
    public void stop() {
        System.out.println("STOP:  " + getWrapper().getPluginId());
        super.stop(); // to close applicationContext
    }

    public void createToolTab(String name, Node content) {
        PluginManager pluginManager = getWrapper().getPluginManager();
        if (pluginManager instanceof FatefallPluginManager fatefallPluginManager) {
            fatefallPluginManager.createToolTab(name, content);
        }
    }

    public void createCardCollection(CardCollection cardCollection) {
        PluginManager pluginManager = getWrapper().getPluginManager();
        if (pluginManager instanceof FatefallPluginManager fatefallPluginManager) {
            fatefallPluginManager.createCardCollection(cardCollection);
        }
    }

}

