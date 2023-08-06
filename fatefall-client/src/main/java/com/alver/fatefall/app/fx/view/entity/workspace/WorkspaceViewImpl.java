package com.alver.fatefall.app.fx.view.entity.workspace;

import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.component.settings.FatefallProperties;
import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Prototype
public class WorkspaceViewImpl extends Control implements WorkspaceView<WorkspaceViewImpl> {

	protected final BeanFactory beanFactory;
	protected final FatefallProperties properties;

	protected ObjectProperty<WorkspaceFX> workspaceProperty = new SimpleObjectProperty<>();

	public ObjectProperty<WorkspaceFX> workspaceProperty() {
		return workspaceProperty;
	}

    @Autowired
	public WorkspaceViewImpl(
			BeanFactory beanFactory,
			FatefallProperties properties) {
		this.beanFactory = beanFactory;
		this.properties = properties;
	}

	@Override
	public Skin<WorkspaceViewImpl> createDefaultSkin() {
		return beanFactory.getBean(WorkspaceSkin.class, this, properties, beanFactory);
	}

}