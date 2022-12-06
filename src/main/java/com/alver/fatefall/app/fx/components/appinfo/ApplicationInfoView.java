package com.alver.fatefall.app.fx.components.appinfo;

import com.alver.fatefall.app.fx.components.FXMLLoadable;
import com.alver.fatefall.app.fx.components.settings.SettingsView;
import jakarta.annotation.PostConstruct;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInfoView extends BorderPane implements FXMLLoadable {

    @Autowired
    protected SettingsView settingsView;

    @PostConstruct
    public void reloadFxml(){
        loadFxml();
        settingsView.reloadListeners.add(this);
    }
}
