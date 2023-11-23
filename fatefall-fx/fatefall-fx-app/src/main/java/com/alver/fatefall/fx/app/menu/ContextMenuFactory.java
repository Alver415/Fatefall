package com.alver.fatefall.fx.app.menu;

import javafx.scene.control.ContextMenu;

public interface ContextMenuFactory<T> {

	ContextMenu buildContextMenu(T object);
}
