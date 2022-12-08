package com.alver.fatefall.app.fx.components.settings;

public class StringSetting extends SettingBase<String> implements Setting<String> {

    public StringSetting(Object bean, String name, String description, String initialValue) {
        super(bean, name, description, initialValue);
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

}
