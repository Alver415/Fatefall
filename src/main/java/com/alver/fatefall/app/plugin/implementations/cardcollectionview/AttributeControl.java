package com.alver.fatefall.app.plugin.implementations.cardcollectionview;

import com.alver.fatefall.api.models.Attribute;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class AttributeControl extends Control {

    protected final ObjectProperty<Attribute> attributeProperty = new SimpleObjectProperty<>();

    public AttributeControl(){
        setSkin(createDefaultSkin());
    }

    @Override
    public Skin<AttributeControl> createDefaultSkin() {
        return new AttributeSkinImpl(this);
    }
}
