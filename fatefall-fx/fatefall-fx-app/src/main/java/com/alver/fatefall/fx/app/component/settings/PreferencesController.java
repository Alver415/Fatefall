
package com.alver.fatefall.fx.app.component.settings;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.core.interfaces.AppPreferenceCategoryProvider;
import com.alver.springfx.annotations.FXMLComponent;
import com.dlsc.preferencesfx.PreferencesFx;
import com.dlsc.preferencesfx.model.Category;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@FXMLComponent
public class PreferencesController {

    protected final FatefallProperties properties;
    protected final List<AppPreferenceCategoryProvider> categoryProviders;
    protected final ObjectProperty<Image> iconProperty;

    @Autowired
    public PreferencesController(
            FatefallProperties properties,
            List<AppPreferenceCategoryProvider> categoryProviders,
            ObjectProperty<Image> iconProperty) {
        this.properties = properties;
        this.categoryProviders = categoryProviders;
        this.iconProperty = iconProperty;
    }

    @EventListener
    private void ready(ApplicationReadyEvent event){
        Platform.runLater(this::buildPreferencesFX);
    }

    protected PreferencesFx buildPreferencesFX() {
        Category[] array = categoryProviders.stream()
                .map(AppPreferenceCategoryProvider::getCategory)
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