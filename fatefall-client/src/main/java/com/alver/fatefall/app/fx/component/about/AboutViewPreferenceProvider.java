package com.alver.fatefall.app.fx.component.about;

import com.alver.fatefall.app.fx.component.settings.PreferenceCategoryProvider;
import com.alver.springfx.SpringFXLoader;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Setting;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AboutViewPreferenceProvider implements PreferenceCategoryProvider {

    protected SpringFXLoader springFXLoader;

    @Autowired
    public AboutViewPreferenceProvider(SpringFXLoader springFXLoader) {
        this.springFXLoader = springFXLoader;
    }

    @Override
    public Category getCategory() {
        return Category.of("About", Setting.of((Node) springFXLoader.load(AboutView.class).view()));

    }

}
