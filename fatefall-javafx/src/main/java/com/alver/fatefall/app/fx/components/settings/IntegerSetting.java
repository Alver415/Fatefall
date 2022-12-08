package com.alver.fatefall.app.fx.components.settings;

public class IntegerSetting extends SettingBase<Integer> implements Setting<Integer> {

    public IntegerSetting(Object bean, String name, String description, Integer initialValue) {
        super(bean, name, description, initialValue);
    }

    @Override
    public void set(String value) {
        super.setValue(Integer.valueOf(value));
    }

    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }
}
