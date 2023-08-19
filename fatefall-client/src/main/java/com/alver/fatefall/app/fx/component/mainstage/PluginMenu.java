package com.alver.fatefall.app.fx.component.mainstage;

import com.alver.fatefall.action.ActionEventHandler;
import com.alver.fatefall.plugin.FatefallPluginManager;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.pf4j.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PluginMenu extends Menu {

	@Autowired
	public PluginMenu(PluginManager pluginManager){
		setText("Plugins");
		List<Menu> menuList = pluginManager.getPlugins().stream()
				.map(plugin -> {
					Menu menu = new Menu(plugin.getPluginId());
					List<MenuItem> menuItems = pluginManager
							.getExtensions(ActionEventHandler.class, plugin.getPluginId())
							.stream().map(action -> {
								MenuItem menuItem = new MenuItem(action.getTitle());
								menuItem.setOnAction(action);
								return menuItem;
							}).toList();
					menu.getItems().setAll(menuItems);
					return menu;
				}).toList();
		getItems().setAll(menuList);
	}
}
