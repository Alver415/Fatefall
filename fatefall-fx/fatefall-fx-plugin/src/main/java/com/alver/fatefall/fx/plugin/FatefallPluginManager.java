package com.alver.fatefall.fx.plugin;

import com.alver.fatefall.fx.core.interfaces.AppController;
import com.alver.fatefall.fx.core.interfaces.AppView;
import com.alver.fatefall.fx.core.model.WorkspaceFX;
import javafx.collections.ObservableList;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class FatefallPluginManager extends SpringPluginManager {

    protected AppController applicationController;
    protected ObservableList<WorkspaceFX> workspaces;

    @Autowired
    public FatefallPluginManager(
            @Lazy AppController applicationController,
            @Lazy ObservableList<WorkspaceFX> workspaces) {
        super();
        this.applicationController = applicationController;
        this.workspaces = workspaces;
    }

    public void addView(AppView appView) {
        applicationController.registerView(appView);
    }

    public void createWorkspace(WorkspaceFX workspace) {
        this.workspaces.add(workspace);
    }
}