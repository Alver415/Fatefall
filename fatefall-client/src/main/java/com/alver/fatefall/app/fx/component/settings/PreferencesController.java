
package com.alver.fatefall.app.fx.component.settings;

import com.alver.fatefall.StageManager;
import com.alver.springfx.SpringFXLoader;
import com.alver.springfx.annotations.FXMLComponent;
import com.dlsc.preferencesfx.PreferencesFx;
import com.dlsc.preferencesfx.model.Category;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import org.pf4j.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@FXMLComponent
public class PreferencesController {

    protected final FatefallProperties properties;
    protected final PluginManager pluginManager;
    protected final SpringFXLoader springFXLoader;
    protected final StageManager stageManager;
    protected final List<PreferenceCategoryProvider> categoryProviders;
    protected final ObjectProperty<Image> iconProperty;
    @Autowired
    public PreferencesController(
            FatefallProperties properties,
            PluginManager pluginManager,
            SpringFXLoader springFXLoader,
            StageManager stageManager,
            List<PreferenceCategoryProvider> categoryProviders,
            ObjectProperty<Image> iconProperty) {
        this.properties = properties;
        this.pluginManager = pluginManager;
        this.springFXLoader = springFXLoader;
        this.stageManager = stageManager;
        this.categoryProviders = categoryProviders;
        this.iconProperty = iconProperty;
    }

    @EventListener
    private void ready(ApplicationReadyEvent event){
        Platform.runLater(this::buildPreferencesFX);
    }

    protected PreferencesFx buildPreferencesFX() {
        Category[] array = categoryProviders.stream()
                .map(PreferenceCategoryProvider::getCategory)
                .toArray(Category[]::new);
        return PreferencesFx.of(getClass(), array)
                .dialogTitle("Fatefall - Preferences")
                .dialogIcon(iconProperty.get());
    }

    public void show() {
        PreferencesFx preferencesFx = buildPreferencesFX();
        preferencesFx.show();
    }

}