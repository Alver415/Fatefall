package com.alver.fatefall.scryfall.plugin.component;


import com.alver.fatefall.api.interfaces.CardCollectionView;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.fx.components.mainstage.ApplicationView;
import com.alver.fatefall.app.plugin.implementations.ComponentFactoryImpl;
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
import java.util.ArrayList;
import java.util.List;

@Component
public class ScryfallComponentFactory extends ComponentFactoryImpl {

    @Autowired
    @Lazy
    protected ApplicationView applicationView;


    public List<MenuItem> buildCardViewContextMenuItems(Card card) {
        ArrayList<MenuItem> items = new ArrayList<>(super.buildCardViewContextMenuItems(card));
        items.add(buildOpenInBrowserMenuItem(card));
        items.add(buildOpenInWebViewMenuItem(card));
        return items;
    }

    private MenuItem buildOpenInWebViewMenuItem(Card card) {
        MenuItem openWebView = new MenuItem();
        openWebView.setText("Open in WebView.");
        openWebView.setOnAction(a -> {
            TabPane tabPane = applicationView.getTabPane();
            Tab tab = new Tab("Scryfall - " + card.getName());
            WebView webView = new WebView();
            webView.getEngine().load(getUrl(card));
            tab.setContent(webView);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        });
        return openWebView;
    }

    private static MenuItem buildOpenInBrowserMenuItem(Card card) {
        MenuItem openBrowser = new MenuItem();
        openBrowser.setText("Open in default browser.");
        openBrowser.setOnAction(a -> {
            try {
                java.awt.Desktop.getDesktop().browse(new URI(getUrl(card)));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
        return openBrowser;
    }

    private static String getUrl(Card card) {
        try {
            JsonNode data = new ObjectMapper().readTree(card.getData());
            return data.findValue("scryfall_uri").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public CardCollectionView buildCardCollectionView() {
        return new ScryfallSearchView();
    }
}
