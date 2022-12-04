package com.alver.fatefall.scryfall.plugin.component;

import com.alver.fatefall.plugin.PluginManager;
import com.alver.fatefall.plugin.implementations.DefaultComponentFactory;
import com.alver.fatefall.plugin.interfaces.CardCollectionView;
import com.alver.fatefall.plugin.interfaces.CardView;
import com.alver.fatefall.plugin.models.Card;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ScryfallComponentFactory extends DefaultComponentFactory {

    @Override
    public CardView buildCardView() {
        CardView cardView = super.buildCardView();
        Node cardViewNode = cardView.getFxViewNode();

        cardViewNode.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                Card card = cardView.getCard();
                ContextMenu contextMenu = new ContextMenu();
                JsonNode data = (JsonNode) card.getData();
                String url = data.findValue("scryfall_uri").asText();

                if (java.awt.Desktop.isDesktopSupported()) {
                    MenuItem menuItem = new MenuItem();
                    menuItem.setText("Open in default browser.");
                    menuItem.setOnAction(a -> {
                        try {
                            java.awt.Desktop.getDesktop().browse(new URI(url));
                        } catch (IOException | URISyntaxException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    contextMenu.getItems().add(menuItem);
                }

                MenuItem menuItem = new MenuItem();
                menuItem.setText("Open in WebView.");
                menuItem.setOnAction(a -> {
                    TabPane tabPane = PluginManager.getTabPane();
                    Tab tab = new Tab("Scryfall - " + card.getName());
                    WebView webView = new WebView();
                    webView.getEngine().load(url);
                    tab.setContent(webView);
                    tabPane.getTabs().add(tab);
                    tabPane.getSelectionModel().select(tab);
                });
                contextMenu.getItems().add(menuItem);
                contextMenu.show(cardViewNode, event.getScreenX(), event.getScreenY());
            }
        });

        return cardView;
    }

    @Override
    public CardCollectionView buildCardCollectionView() {
        return new ScryfallSearchView();
    }
}
