package com.alver.fatefall.scryfall.plugin;

import com.alver.fatefall.fx.core.interfaces.AppPreferenceCategoryProvider;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import org.pf4j.Extension;
import org.pf4j.ExtensionPoint;
import org.springframework.stereotype.Component;


@Extension
@Component
public class ScryfallPreferences implements AppPreferenceCategoryProvider, ExtensionPoint {

    @Override
    public Category getCategory() {
        return Category.of("Scryfall", Group.of("Scryfall Plugin Settings"));
    }
}
