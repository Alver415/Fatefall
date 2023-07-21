package com.alver.fatefall.app.fx.view.entity.field;

import com.alver.fatefall.data.entity.Field;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class FieldControl extends Control {

    protected final ObjectProperty<Field> attributeProperty = new SimpleObjectProperty<>();

    public FieldControl(){
        setSkin(createDefaultSkin());
    }

    @Override
    public Skin<FieldControl> createDefaultSkin() {
        return new FieldSkinImpl(this);
    }
}
