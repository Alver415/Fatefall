package com.alver.fatefall.app.fx.components.settings;

import com.dlsc.preferencesfx.model.Category;
import org.pf4j.ExtensionPoint;

public interface PreferenceCategoryProvider extends ExtensionPoint {
    Category getCategory();
}
