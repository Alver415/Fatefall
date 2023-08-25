package com.alver.fatefall.plugin;

import com.alver.fatefall.app.fx.component.mainstage.ApplicationController;
import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class FatefallPluginManager extends SpringPluginManager {

    protected ApplicationController applicationController;
    protected ObservableList<WorkspaceFX> workspaces;

    @Autowired
    public FatefallPluginManager(
            @Lazy ApplicationController applicationController,
            @Lazy ObservableList<WorkspaceFX> workspaces) {
        super();
        this.applicationController = applicationController;
        this.workspaces = workspaces;
    }

    public void createToolTab(String name, Node content) {
        Tab tab = new Tab(name);
        tab.setContent(content);
        applicationController.getTabPane().getTabs().add(tab);
    }

    public void createWorkspace(WorkspaceFX workspace) {
        this.workspaces.add(workspace);
    }
}