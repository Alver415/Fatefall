package com.alver.fatefall.app.fx.component.plugins;

import com.alver.fatefall.plugin.FatefallPluginManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.pf4j.PluginState;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PluginManagerView extends BorderPane {

    @Autowired
    protected FatefallPluginManager pluginManager;

    @FXML
    protected TableView<PluginRow> tableView;

    @FXML
    public void initialize() {
        tableView.getItems().setAll(pluginManager.getPlugins()
                .stream().map(PluginRow::new).toList());
    }

    @FXML
    public void reload() {
        pluginManager.unloadPlugins();
        pluginManager.loadPlugins();
        pluginManager.startPlugins();
        tableView.getItems().setAll(pluginManager.getPlugins()
                .stream().map(PluginRow::new).toList());
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
            if (state.equals(PluginState.FAILED)){
                message += " - " + pluginWrapper.getFailedException().getMessage();
            }
            return message;
        }
    }
}
