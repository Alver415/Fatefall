package com.alver.fatefall.app.plugin.implementations.cardcollectionview;

import com.alver.fatefall.api.interfaces.CardCollectionView;
import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.interfaces.ComponentFactory;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.fx.components.FXMLAutoLoad;
import com.alver.fatefall.app.fx.components.settings.FatefallProperties;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Prototype
public class CardCollectionViewImpl extends Control implements CardCollectionView<CardCollectionViewImpl> {

    @Autowired
    protected BeanFactory beanFactory;
    @Autowired
    protected FatefallProperties properties;

    protected ObjectProperty<CardCollection> cardCollectionProperty = new SimpleObjectProperty<>();

    public final ObjectProperty<CardCollection> cardCollectionProperty() {
        return cardCollectionProperty;
    }

    @Override
    public Skin<CardCollectionViewImpl> createDefaultSkin(){
        return beanFactory.getBean(CardCollectionSkin.class, this, properties, beanFactory);
    }

}