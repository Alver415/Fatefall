package com.alver.fatefall.app.fx.view.entity.workspace;

import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import com.alver.fatefall.app.fx.view.FxView;
import com.alver.fatefall.data.entity.Workspace;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;

public interface WorkspaceView<T extends Node> extends FxView<T> {

    ObjectProperty<WorkspaceFX> workspaceProperty();

    default WorkspaceFX getWorkspace() {
        return workspaceProperty().get();
    }

    default void setWorkspace(WorkspaceFX IWorkspace) {
        workspaceProperty().set(IWorkspace);
    }

}