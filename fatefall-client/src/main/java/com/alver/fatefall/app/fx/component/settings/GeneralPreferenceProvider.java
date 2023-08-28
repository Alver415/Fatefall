package com.alver.fatefall.app.fx.component.settings;

import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Setting;
import javafx.beans.property.ObjectProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(value = 0)
public class GeneralPreferenceProvider implements PreferenceCategoryProvider {

    protected FatefallProperties properties;

    public GeneralPreferenceProvider(FatefallProperties properties) {
        this.properties = properties;
    }

    @Override
    public Category getCategory() {
        ObjectProperty<FatefallProperties.LocaleOption> locale = properties.getLocale();
        if (locale.get() == null){
            locale.set(FatefallProperties.LocaleOption.ENGLISH);
        }
        return Category.of("General",
                Setting.of("Language", properties.getLocaleOptions(), locale));
    }

}
