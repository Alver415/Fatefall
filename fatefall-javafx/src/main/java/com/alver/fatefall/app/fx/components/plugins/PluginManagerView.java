package com.alver.fatefall.app.fx.components.plugins;

import com.alver.fatefall.app.fx.components.FXMLAutoLoad;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@FXMLAutoLoad
@Component
public class PluginManagerView extends BorderPane {

    @Autowired
    protected PluginManager pluginManager;

    @FXML
    protected TableView<PluginRow> tableView;

    @FXML
    public void initialize(){
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
                return pluginWrapper.getPluginState().name();
            }
        }
}
