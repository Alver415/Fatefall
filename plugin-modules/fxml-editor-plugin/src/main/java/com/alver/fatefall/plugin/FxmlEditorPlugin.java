package com.alver.fatefall.plugin;

import com.alver.fatefall.app.plugin.implementations.DefaultComponentFactory;
import com.alver.fatefall.app.plugin.implementations.SpringPlugin;
import com.alver.fatefall.app.plugin.interfaces.CardView;
import com.alver.fatefall.app.plugin.interfaces.ComponentFactory;

public class FxmlEditorPlugin extends SpringPlugin {

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
