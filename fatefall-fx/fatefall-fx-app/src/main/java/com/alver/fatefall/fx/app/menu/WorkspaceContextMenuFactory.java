package com.alver.fatefall.fx.app.menu;

import com.alver.fatefall.core.api.EntityApi;
import com.alver.fatefall.fx.core.model.WorkspaceFX;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceContextMenuFactory extends EntityContextMenuFactory<WorkspaceFX>{

	@Autowired
	public WorkspaceContextMenuFactory(EntityApi<WorkspaceFX> entityApi) {
		super(entityApi);
	}

	@Override
	public ContextMenu buildContextMenu(WorkspaceFX workspaceFX) {
		ContextMenu contextMenu = super.buildContextMenu(workspaceFX);
		MenuItem add = new MenuItem("Add Card");
		contextMenu.getItems().add(add);
		return contextMenu;
	}
}
