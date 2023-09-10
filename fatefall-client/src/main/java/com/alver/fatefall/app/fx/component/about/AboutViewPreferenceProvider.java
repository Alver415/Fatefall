package com.alver.fatefall.app.fx.component.about;

import com.alver.fatefall.app.fx.component.settings.PreferenceCategoryProvider;
import com.alver.springfx.SpringFX;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Setting;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AboutViewPreferenceProvider implements PreferenceCategoryProvider {

    protected SpringFX springFX;

    @Autowired
    public AboutViewPreferenceProvider(SpringFX springFX) {
        this.springFX = springFX;
    }

    @Override
    public Category getCategory() {
        return Category.of("About", Setting.of((Node) springFX.load(AboutView.class).view()));

    }

}
