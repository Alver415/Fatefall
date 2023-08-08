package com.alver.fatefall.plugin;

import com.alver.fatefall.data.entity.Workspace;
import javafx.scene.Node;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class FatefallPlugin extends SpringPlugin {

    public FatefallPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }


    @Override
    public void start() {
        System.out.println("START: " + getWrapper().getPluginId());
        try {
            ((AnnotationConfigApplicationContext) getApplicationContext()).refresh();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void stop() {
        System.out.println("STOP:  " + getWrapper().getPluginId());
        super.stop(); // to close applicationContext
    }

    protected AnnotationConfigApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        if (wrapper.getPluginManager() instanceof SpringPluginManager springPluginManager) {
            applicationContext.setParent(springPluginManager.getApplicationContext());
        }
        applicationContext.setClassLoader(getWrapper().getPluginClassLoader());
        return applicationContext;
    }

    public void createToolTab(String name, Node content) {
        PluginManager pluginManager = getWrapper().getPluginManager();
        if (pluginManager instanceof FatefallPluginManager fatefallPluginManager) {
            fatefallPluginManager.createToolTab(name, content);
        }
    }

    public void createWorkspace(Workspace IWorkspace) {
        PluginManager pluginManager = getWrapper().getPluginManager();
        if (pluginManager instanceof FatefallPluginManager fatefallPluginManager) {
            fatefallPluginManager.createWorkspace(IWorkspace);
        }
    }

}
