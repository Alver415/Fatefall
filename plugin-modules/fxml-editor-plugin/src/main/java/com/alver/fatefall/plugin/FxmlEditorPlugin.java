package com.alver.fatefall.plugin;

import com.alver.fatefall.plugin.implementations.DefaultComponentFactory;
import com.alver.fatefall.plugin.implementations.DefaultPlugin;
import com.alver.fatefall.plugin.interfaces.CardView;
import com.alver.fatefall.plugin.interfaces.ComponentFactory;

public class FxmlEditorPlugin extends DefaultPlugin {

    public FxmlEditorPlugin(){
        super("Fxml Editor", "v1.0");
    }
    @Override
    public ComponentFactory getComponentFactory() {
        return new DefaultComponentFactory(){
            @Override
            public CardView buildCardView(){
                return new FxmlEditorCardView();
            }
        };
    }

}
