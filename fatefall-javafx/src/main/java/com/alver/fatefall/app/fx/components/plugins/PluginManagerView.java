package com.alver.fatefall.app.fx.components.plugins;

import com.alver.fatefall.app.fx.components.FXMLAutoLoad;
import com.alver.fatefall.plugin.Plugin;
import com.alver.fatefall.plugin.PluginManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@FXMLAutoLoad
@Component
public class PluginManagerView extends BorderPane {

    @Autowired
    protected PluginManager pluginManager;

    @FXML
    protected TableView<Plugin> tableView;

    @FXML
    protected void initialize() throws IOException {
        reload();
    }

    @FXML
    public void reload() throws IOException {
        pluginManager.reload();
        tableView.getItems().setAll(pluginManager.getPlugins());
    }
}
