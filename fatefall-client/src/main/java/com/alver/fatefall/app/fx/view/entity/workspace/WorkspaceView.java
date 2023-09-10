package com.alver.fatefall.app.fx.view.entity.workspace;

import com.alver.fatefall.app.fx.model.entity.WorkspaceFX;
import com.alver.springfx.annotations.Prototype;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Prototype
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

	private final BeanFactory beanFactory;

	@Autowired
	public WorkspaceView(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	@Override
	public Skin<WorkspaceView> createDefaultSkin() {
		return new WorkspaceSkin(this, beanFactory);
	}

}