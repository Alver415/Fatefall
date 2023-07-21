package com.alver.fatefall.scryfall.plugin;

import com.alver.fatefall.app.fx.component.settings.PreferenceCategoryProvider;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import org.pf4j.Extension;
import org.springframework.stereotype.Component;


@Extension
@Component
public class ScryfallPreferences implements PreferenceCategoryProvider {

    @Override
    public Category getCategory() {
        return Category.of("Scryfall", Group.of("Scryfall Plugin Settings"));
    }
}
