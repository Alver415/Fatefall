package com.alver.fatefall.app.fx.component.plugins;

import com.alver.fatefall.utils.BindingUtil;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import org.pf4j.PluginManager;
import org.pf4j.PluginState;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLPrototype
public class PluginManagerController {

    protected PluginManager pluginManager;
    protected ObservableList<PluginWrapper> pluginWrappers;
    protected ObservableList<PluginRow> pluginRows;

    public ObservableList<PluginRow> getPluginRows(){
        return pluginRows;
    }

    @Autowired
    public PluginManagerController(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
        this.pluginRows = FXCollections.observableArrayList();
        this.pluginWrappers = FXCollections.observableList(pluginManager.getPlugins());
        BindingUtil.mapContent(pluginRows, pluginWrappers, PluginRow::new);
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
