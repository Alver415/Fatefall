package com.alver.fatefall.scryfall.plugin.component;

import com.alver.fatefall.plugin.PluginManager;
import com.alver.fatefall.plugin.implementations.DefaultContextMenuFactory;
import com.alver.fatefall.plugin.models.Card;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

public class ScryfallContextMenuFactory extends DefaultContextMenuFactory {

    @Override
    public Collection<MenuItem> buildCardViewMenuItems(Card card) {
        JsonNode data = (JsonNode) card.getData();
        String url = data.findValue("scryfall_uri").asText();

        MenuItem openBrowser = new MenuItem();
        openBrowser.setText("Open in default browser.");
        openBrowser.setOnAction(a -> {
            try {
                java.awt.Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });


        MenuItem openWebView = new MenuItem();
        openWebView.setText("Open in WebView.");
        openWebView.setOnAction(a -> {
            TabPane tabPane = PluginManager.getTabPane();
            Tab tab = new Tab("Scryfall - " + card.getName());
            WebView webView = new WebView();
            webView.getEngine().load(url);
            tab.setContent(webView);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        });

        return List.of(openBrowser, openWebView);
    }
}