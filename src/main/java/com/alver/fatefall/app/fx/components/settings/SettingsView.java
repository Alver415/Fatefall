package com.alver.fatefall.app.fx.components.settings;

import com.alver.fatefall.app.fx.components.FXMLLoadable;
import com.alver.fatefall.app.fx.components.mainstage.ApplicationView;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.PropertySheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SettingsView extends BorderPane implements FXMLLoadable {

    private static final Logger log = LogManager.getLogger(SettingsView.class);
    @FXML
    protected PropertySheet propertySheet;

    @Lazy
    @Autowired
    protected ApplicationView applicationView;

    protected Map<String, Setting<?>> settingsMap = new ConcurrentHashMap<>();

    public void registerSetting(Setting<?> setting) {
        settingsMap.put(setting.getName(), setting);
        propertySheet.getItems().add(setting);
    }

    public final Setting<Color> fxBase = new SettingBase<>(this, "fxBase", "", null) {
        {
            addListener(listener -> {
                Color color = fxBase.getValue();
                String hexColor = String.format("#%02X%02X%02X",
                        (int) (color.getRed() * 255),
                        (int) (color.getGreen() * 255),
                        (int) (color.getBlue() * 255));
                applicationView.setStyle(String.format("-fx-base: %s;}", hexColor));
            });
        }

        @Override
        public void set(String value) {
            super.set(Color.valueOf(value));
        }

        @Override
        public Class<Color> getType() {
            return Color.class;
        }
    };
    public final StringSetting styleSheet = new StringSetting(this, "styleSheet", "", null);
    public final StringSetting locale = new StringSetting(this, "locale", "Change the Language", Locale.getDefault().toLanguageTag()) {
        {
            addListener(listener -> {
                Locale.setDefault(Locale.forLanguageTag(locale.get()));
                reloadListeners.forEach(FXMLLoadable::loadFxml);
            });
        }
    };
    public List<FXMLLoadable> reloadListeners = new ArrayList<>();


    private final Properties properties = new Properties();

    public SettingsView() {
        loadFxml();
        registerSetting(styleSheet);
        registerSetting(locale);
        registerSetting(fxBase);
        loadSettings();
    }

    public void loadSettings() {
        try {
            properties.load(new FileReader("settings"));
            properties.forEach((k, v) -> {
                String key = String.valueOf(k);
                String value = String.valueOf(v);

                if (settingsMap.containsKey(key)) {
                    settingsMap.get(key).set(value);
                }
            });
        } catch (IOException e) {
            log.error("Error saving Fatefall application settings.", e);
        }
    }

    public void saveSettings() {
        try (OutputStream output = new FileOutputStream("settings")) {
            settingsMap.forEach((name, setting) -> {
                String value = setting.getValue() == null ? "" : String.valueOf(setting.getValue());
                properties.setProperty(name, value);
            });
            properties.store(output, "Fatefall Application Properties");
        } catch (IOException e) {
            log.error("Error saving Fatefall application settings.", e);
        }
    }
}
