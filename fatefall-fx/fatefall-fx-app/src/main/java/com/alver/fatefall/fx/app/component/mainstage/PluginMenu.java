package com.alver.fatefall.fx.app.component.mainstage;

import com.alver.fatefall.fx.core.interfaces.AppEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PluginMenu extends Menu {

	@Autowired
	public PluginMenu(@Autowired(required = false) PluginManager pluginManager){
		setText("Plugins");
		List<PluginWrapper> plugins = pluginManager == null ? List.of() : pluginManager.getPlugins();
		List<Menu> menuList = plugins.stream()
				.map(plugin -> {
					Menu menu = new Menu(plugin.getPluginId());
					List<MenuItem> menuItems = pluginManager
							.getExtensions(AppEvent.class, plugin.getPluginId())
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
