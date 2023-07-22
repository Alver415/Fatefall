package com.alver.fatefall.scryfall.plugin.component;


import com.alver.fatefall.app.fx.component.mainstage.ApplicationView;
import com.alver.fatefall.app.fx.view.entity.card.CardView;
import com.alver.fatefall.app.fx.view.entity.workspace.WorkspaceView;
import com.alver.fatefall.app.plugin.implementations.ComponentFactoryImpl;
import com.alver.fatefall.data.entity.Card;
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


    public List<MenuItem> buildCardViewContextMenuItems(CardView<?> cardView) {
        ArrayList<MenuItem> items = new ArrayList<>(super.buildCardViewContextMenuItems(cardView));
        items.add(buildOpenInBrowserMenuItem(cardView));
        items.add(buildOpenInWebViewMenuItem(cardView));
        return items;
    }

    private MenuItem buildOpenInWebViewMenuItem(CardView<?> cardView) {
        MenuItem openWebView = new MenuItem();
        openWebView.setText("Open in WebView.");
        openWebView.setOnAction(a -> {
            TabPane tabPane = applicationView.getTabPane();
            Tab tab = new Tab("Scryfall - " + cardView.getCard().getName());
            WebView webView = new WebView();
            webView.getEngine().load(getUrl(cardView.getCard()));
            tab.setContent(webView);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        });
        return openWebView;
    }

    private static MenuItem buildOpenInBrowserMenuItem(CardView<?> cardView) {
        MenuItem openBrowser = new MenuItem();
        openBrowser.setText("Open in default browser.");
        openBrowser.setOnAction(a -> {
            try {
                java.awt.Desktop.getDesktop().browse(new URI(getUrl(cardView.getCard())));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
        return openBrowser;
    }

    private static String getUrl(Card card) {
        return card.getFields().get("scryfall_url").getValue();
    }


    @Override
    public WorkspaceView buildWorkspaceView() {
        return new ScryfallSearchView();
    }
}
