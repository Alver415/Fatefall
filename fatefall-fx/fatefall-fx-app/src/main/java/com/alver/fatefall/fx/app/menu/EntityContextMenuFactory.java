package com.alver.fatefall.fx.app.menu;

import com.alver.fatefall.core.api.EntityApi;
import com.alver.fatefall.fx.core.model.EntityFX;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class EntityContextMenuFactory<T extends EntityFX> implements ContextMenuFactory<T> {

	protected final EntityApi<T> api;

	public EntityContextMenuFactory(EntityApi<T> entityApi) {
		this.api = entityApi;
	}

	public ContextMenu buildContextMenu(T entity) {
		ContextMenu contextMenu = new ContextMenu();

		MenuItem save = new MenuItem("Save");
		save.setOnAction(a -> {
			if (entity.getId() == null) {
				api.create(entity);
			} else {
				api.update(entity.getId(), entity);
			}
		});

		MenuItem delete = new MenuItem("Delete");
		delete.setOnAction(a -> api.delete(entity.getId()));

		contextMenu.getItems().addAll(save, delete);
		return contextMenu;
	}
}
