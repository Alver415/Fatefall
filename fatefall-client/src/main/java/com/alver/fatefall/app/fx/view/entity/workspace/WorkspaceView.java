package com.alver.fatefall.app.fx.view.entity.workspace;

import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class WorkspaceView extends Control {

	protected final ObjectProperty<WorkspaceFX> workspaceProperty = new SimpleObjectProperty<>();
	public ObjectProperty<WorkspaceFX> workspaceProperty() {
		return workspaceProperty;
	}
	public WorkspaceFX getWorkspace() {
		return workspaceProperty().get();
	}
	public void setWorkspace(WorkspaceFX workspace) {
		workspaceProperty().set(workspace);
	}

	public WorkspaceView() {}

	@Override
	public Skin<WorkspaceView> createDefaultSkin() {
		return new WorkspaceSkin(this);
	}

}