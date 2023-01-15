package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.app.fx.components.settings.SettingsView;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CardFace extends AnchorPane {

    private static final String styleSheet = Objects.requireNonNull(CardFace.class.getResource("CardFace.css")).toExternalForm();

    @Autowired
    protected SettingsView settingsView = SettingsView.INSTANCE;

    public CardFace() {
        super();
        getStylesheets().add(styleSheet);
        minHeightProperty().bind(settingsView.cardHeight);
        minWidthProperty().bind(settingsView.cardWidth);
        maxHeightProperty().bind(settingsView.cardHeight);
        maxWidthProperty().bind(settingsView.cardWidth);
    }

    public CardFace(Node... children) {
        this();
        getChildren().addAll(children);
    }
}
