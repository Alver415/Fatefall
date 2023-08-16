package com.alver.fatefall.plugin;

import com.alver.fatefall.app.fx.component.mainstage.ApplicationView;
import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import com.alver.fatefall.data.entity.Workspace;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class FatefallPluginManager extends SpringPluginManager {

    protected ApplicationView applicationView;
    protected ObservableList<WorkspaceFX> workspaces;

    @Autowired
    public FatefallPluginManager(
            @Value("${directory.plugins}") Path pluginsDirectory,
            @Lazy ApplicationView applicationView,
            @Lazy ObservableList<WorkspaceFX> workspaces){
        super(pluginsDirectory);
        this.applicationView = applicationView;
        this.workspaces = workspaces;
    }

    public void createToolTab(String name, Node content) {
        Tab tab = new Tab(name);
        tab.setContent(content);
        applicationView.getTabPane().getTabs().add(tab);
    }
    public void createWorkspace(WorkspaceFX workspace) {
        this.workspaces.add(workspace);
    }

    @EventListener
    public void onEventReady(ApplicationReadyEvent event){
        this.applicationView.buildPluginMenu(this);
    }
}