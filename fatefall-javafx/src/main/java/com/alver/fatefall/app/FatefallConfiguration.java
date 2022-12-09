package com.alver.fatefall.app;

import com.alver.fatefall.api.FatefallPluginManager;
import com.alver.fatefall.app.fx.components.mainstage.ApplicationView;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class FatefallConfiguration implements ApplicationContextAware {

    protected ApplicationContext context;
    @Autowired
    @Lazy
    protected ApplicationView applicationView;

    @Bean
    public FatefallPluginManager getPluginManager() {
        FatefallPluginManager pluginManager = new FatefallPluginManager(){
            @Override
            public void createToolTab(String name, Node content) {
                Tab tab = new Tab(name);
                tab.setContent(content);
                applicationView.getTabPane().getTabs().add(tab);
            }
        };
        pluginManager.setApplicationContext(context);
        pluginManager.loadPlugins();
        pluginManager.startPlugins();
        return pluginManager;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}