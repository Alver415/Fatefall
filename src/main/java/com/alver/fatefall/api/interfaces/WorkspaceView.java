package com.alver.fatefall.api.interfaces;

import com.alver.fatefall.api.models.Workspace;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;

public interface WorkspaceView<T extends Node> extends FxView<T> {

    ObjectProperty<Workspace> workspaceProperty();

    default Workspace getWorkspace() {
        return workspaceProperty().get();
    }

    default void setWorkspace(Workspace workspace) {
        workspaceProperty().set(workspace);
    }

}