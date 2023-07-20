package com.alver.fatefall.app.plugin.implementations.cardcollectionview;

import com.alver.fatefall.api.interfaces.WorkspaceView;
import com.alver.fatefall.api.models.Workspace;
import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.components.settings.FatefallProperties;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import org.springframework.beans.factory.BeanFactory;

@Prototype
public class WorkspaceViewImpl extends Control implements WorkspaceView<WorkspaceViewImpl> {

	protected final BeanFactory beanFactory;
	protected final FatefallProperties properties;

	protected ObjectProperty<Workspace> workspaceProperty = new SimpleObjectProperty<>();

	public ObjectProperty<Workspace> workspaceProperty() {
		return workspaceProperty;
	}

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