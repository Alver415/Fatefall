package com.alver.fatefall.fx.components.settings;

import com.alver.fatefall.FxComponent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

import static com.alver.fatefall.FatefallApplication.APP_ICON;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Lazy
@Component
@Scope(SCOPE_SINGLETON)
public class Settings extends Stage implements FxComponent {

    private static final Logger LOGGER = LogManager.getLogger(Settings.class.getName());

    protected Properties properties = new Properties();

    @FXML
    protected ComboBox<NamedValue<String>> stylesheet;

    public void initialize() {
        initModality(Modality.APPLICATION_MODAL);
        getIcons().add(APP_ICON);
        setTitle("Fatefall Settings");

        stylesheet.getItems().addAll(Arrays.asList(StandardStylesheet.values()));
        stylesheet.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Window.getWindows().stream().map(Window::getScene).forEach(scene -> {
                scene.getStylesheets().clear();
                scene.getStylesheets().add(newValue.getValue());
            });
        });
        stylesheet.setCellFactory((c) -> {
            return new ComboBoxListCell<>() {
                @Override
                public void updateItem(NamedValue<String> item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty && item != null) {
                        setText(item.getName());
                    }
                }
            };
        });
        load();

        //Ensure this window is also initialized with the selected item.
        setStylesheet(getScene(), stylesheet.getSelectionModel().getSelectedItem());
    }

    private void setStylesheet(Scene scene, NamedValue<String> stylesheet) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(stylesheet.getValue());
    }

    public NamedValue<String> getSelectedStylesheet() {
        return stylesheet.getSelectionModel().getSelectedItem();
    }

    public void load() {
        try (InputStream input = new FileInputStream("fatefall.properties")) {
            properties.load(input);
            stylesheet.getSelectionModel().select(StandardStylesheet.valueOf(((String)properties.get("stylesheet")).toUpperCase()));
        } catch (IOException e) {
            LOGGER.error("Error saving Fatefall application settings.", e);
        }
    }

    public void save() {
        try (OutputStream output = new FileOutputStream("fatefall.properties")) {
            properties.setProperty("stylesheet", stylesheet.getSelectionModel().getSelectedItem().getName());
            properties.setProperty("db.url", "localhost");
            properties.setProperty("db.password", "password");
            properties.store(output, "Fatefall Application Properties");
        } catch (IOException e) {
            LOGGER.error("Error saving Fatefall application settings.", e);
        }
    }
}
