package com.alver.fatefall.scryfall.plugin.component;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.fx.components.mainstage.ApplicationView;
import com.alver.fatefall.app.plugin.implementations.DefaultContextMenuFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class ScryfallContextMenuFactory extends DefaultContextMenuFactory {

    @Autowired
    @Lazy
    protected ApplicationView applicationView;

    public List<MenuItem> buildCardViewMenuItems(Card card) {
        List<MenuItem> menuItems = super.buildCardViewMenuItems(card);
        JsonNode data = null;
        try {
            data = new ObjectMapper().readTree(card.getData());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
        menuItems.add(openBrowser);

        MenuItem openWebView = new MenuItem();
        openWebView.setText("Open in WebView.");
        openWebView.setOnAction(a -> {
            TabPane tabPane = applicationView.getTabPane();
            Tab tab = new Tab("Scryfall - " + card.getName());
            WebView webView = new WebView();
            webView.getEngine().load(url);
            tab.setContent(webView);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        });
        menuItems.add(openWebView);
        return menuItems;
    }
}