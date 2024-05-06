package com.alver.fatefall.fx.plugin;

import com.alver.fatefall.fx.core.utils.CollectionBindings;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import org.pf4j.PluginManager;
import org.pf4j.PluginState;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLPrototype
public class PluginManagerController {

	//region Properties
	private final ListProperty<PluginWrapper> pluginWrappers = new SimpleListProperty<>(
			this, "pluginWrappers", FXCollections.observableArrayList());

	public ListProperty<PluginWrapper> pluginWrappersProperty() {
		return pluginWrappers;
	}

	public ObservableList<PluginWrapper> getPluginWrappers() {
		return pluginWrappers.get();
	}

	public void setPluginWrappers(ObservableList<PluginWrapper> pluginWrappers) {
		pluginWrappersProperty().set(pluginWrappers);
	}

	private final ListProperty<PluginRow> pluginRows = new SimpleListProperty<>(
			this, "pluginRows", FXCollections.observableArrayList());

	public ListProperty<PluginRow> pluginRowsProperty() {
		return pluginRows;
	}

	public ObservableList<PluginRow> getPluginRows() {
		return pluginRows.get();
	}

	public void setPluginRows(ObservableList<PluginRow> pluginRows) {
		pluginRowsProperty().set(pluginRows);
	}
	//endregion Properties

	protected PluginManager pluginManager;

	@Autowired
	public PluginManagerController(PluginManager pluginManager) {
		this.pluginManager = pluginManager;
		this.pluginWrappers.addAll(pluginManager.getPlugins());
		CollectionBindings.bind(pluginWrappers, pluginRows, PluginRow::new);
	}

	@FXML
	public void reload() {
		pluginManager.unloadPlugins();
		pluginManager.loadPlugins();
		pluginManager.startPlugins();
		pluginWrappers.setAll(pluginManager.getPlugins());
	}

	public record PluginRow(PluginWrapper pluginWrapper) {
		public String getId() {
			return pluginWrapper.getPluginId();
		}

		public String getVersion() {
			return pluginWrapper.getDescriptor().getVersion();
		}

		public String getDescription() {
			return pluginWrapper.getDescriptor().getPluginDescription();
		}

		public String getState() {
			PluginState state = pluginWrapper.getPluginState();
			String message = state.name();
			if (state.equals(PluginState.FAILED)) {
				message += " - " + pluginWrapper.getFailedException().getMessage();
			}
			return message;
		}
	}
}
