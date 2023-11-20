package com.alver.fatefall.fx.plugin;

import com.alver.fatefall.fx.core.interfaces.AppPreferenceCategoryProvider;
import com.alver.springfx.SpringFX;
import com.alver.springfx.model.FXMLControllerAndView;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Setting;
import javafx.scene.Node;
import org.pf4j.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PluginsPreferenceProvider implements AppPreferenceCategoryProvider {

    protected PluginManager pluginManager;
    protected SpringFX springFX;

    @Autowired
    public PluginsPreferenceProvider(PluginManager pluginManager, SpringFX springFX) {
        this.pluginManager = pluginManager;
        this.springFX = springFX;
    }

    @Override
    public Category getCategory() {
        Category[] subCategories = pluginManager.getExtensions(AppPreferenceCategoryProvider.class).stream()
                .map(AppPreferenceCategoryProvider::getCategory)
                .toArray(Category[]::new);

        FXMLControllerAndView<PluginManagerController, Object> loaded = springFX.load(PluginManagerController.class);
        return Category.of("Plugin Management", Setting.of((Node) loaded.view()))
                .subCategories(subCategories);

    }

}
