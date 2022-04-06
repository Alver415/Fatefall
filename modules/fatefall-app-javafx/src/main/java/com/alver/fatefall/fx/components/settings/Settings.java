package com.alver.fatefall.fx.components.settings;

import com.alver.fatefall.FxComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Lazy
@Component
@Scope(SCOPE_SINGLETON)
public class Settings {

    private static final Logger LOGGER = LogManager.getLogger(Settings.class.getName());

    protected Properties properties = new Properties();

    public Settings(){
        load();
    }

    public void show() {
        load();
        VBox root = new VBox();
        Scene scene = new Scene(root);

        ComboBox<NamedValue<String>> stylesheet = new ComboBox<>();
        stylesheet.getItems().addAll(Arrays.asList(StandardStylesheet.values()));
        stylesheet.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            redrawWindows(newValue);
        });
        stylesheet.setCellFactory(c -> new ComboBoxListCell<>() {
            @Override
            public void updateItem(NamedValue<String> item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty && item != null) {
                    setText(item.getName());
                    setTooltip(new Tooltip(item.getValue()));
                }
            }
        });
        stylesheet.getSelectionModel().select(StandardStylesheet.valueOf(properties.getProperty("stylesheet").toUpperCase()));

        root.getChildren().add(stylesheet);

        //Ensure this window is also initialized with the selected item.
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Fatefall Settings");
        stage.setScene(scene);
        setStylesheet(stage.getScene(), stylesheet.getSelectionModel().getSelectedItem());
        stage.show();
    }

    private void redrawWindows(NamedValue<String> newValue) {
        Window.getWindows().stream().map(Window::getScene).forEach(scene -> {
            properties.setProperty("stylesheet", newValue.getName());
            setStylesheet(scene, newValue);
        });
    }

    private void setStylesheet(Scene scene, NamedValue<String> stylesheet) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(stylesheet.getValue());
    }

    public String getStylesheet() {
        return StandardStylesheet.valueOf(properties.getProperty("stylesheet").toUpperCase()).getValue();
    }

    public void load() {
        try (InputStream input = getClass().getResourceAsStream("fatefall.settings")) {
            properties.load(input);
        } catch (IOException e) {
            LOGGER.error("Error saving Fatefall application settings.", e);
        }
    }

    public void save() {
        try (OutputStream output = new FileOutputStream(
                Objects.requireNonNull(getClass().getResource("fatefall.settings")).getPath())) {
            properties.setProperty("db.url", "localhost");
            properties.setProperty("db.password", "password");
            properties.store(output, "Fatefall Application Properties");
        } catch (IOException e) {
            LOGGER.error("Error saving Fatefall application settings.", e);
        }
    }
}
