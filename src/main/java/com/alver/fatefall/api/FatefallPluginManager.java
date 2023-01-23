package com.alver.fatefall.api;

import com.alver.fatefall.api.models.Workspace;
import com.alver.fatefall.app.fx.components.mainstage.ApplicationView;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class FatefallPluginManager extends SpringPluginManager {

    @Autowired
    @Lazy
    protected ApplicationView applicationView;

    public void createToolTab(String name, Node content) {
        Tab tab = new Tab(name);
        tab.setContent(content);
        applicationView.getTabPane().getTabs().add(tab);
    }
    public void createWorkspace(Workspace workspace) {
        applicationView.getWorkspaceList().getItems().add(workspace);
    }
}
