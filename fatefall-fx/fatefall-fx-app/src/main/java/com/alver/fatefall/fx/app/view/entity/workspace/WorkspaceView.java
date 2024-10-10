package com.alver.fatefall.fx.app.view.entity.workspace;

import com.alver.fatefall.fx.core.model.WorkspaceFX;
import com.alver.springfx.annotations.Prototype;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

@Prototype
public class WorkspaceView extends Control {

	public static final URL FXML = WorkspaceView.class.getResource("WorkspaceView.fxml");

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