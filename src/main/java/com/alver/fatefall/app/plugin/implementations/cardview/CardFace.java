package com.alver.fatefall.app.plugin.implementations.cardview;

import com.alver.fatefall.app.fx.components.settings.SettingsView;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class CardFace extends AnchorPane {

    private static final String styleSheet = Objects.requireNonNull(CardFace.class.getResource("CardFace.css")).toExternalForm();

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
