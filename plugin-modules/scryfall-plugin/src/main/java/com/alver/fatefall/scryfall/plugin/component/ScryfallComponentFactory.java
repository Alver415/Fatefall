package com.alver.fatefall.scryfall.plugin.component;

import com.alver.fatefall.app.fx.component.mainstage.ApplicationController;
import com.alver.fatefall.app.fx.model.entity.CardFX;
import com.alver.fatefall.app.fx.view.entity.card.CardView;
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

@Component
public class ScryfallComponentFactory {

	@Autowired
	@Lazy
	protected ApplicationController applicationController;

	@Autowired
	protected ObjectMapper objectMapper;

	private MenuItem buildOpenInWebViewMenuItem(CardView cardView) {
		MenuItem openWebView = new MenuItem();
		openWebView.setText("Open in WebView.");
		openWebView.setOnAction(a -> {
			TabPane tabPane = applicationController.getTabPane();
			Tab tab = new Tab("Scryfall - " + cardView.getCard().getName());
			WebView webView = new WebView();
			webView.getEngine().load(getUrl(cardView.getCard()));
			tab.setContent(webView);
			tabPane.getTabs().add(tab);
			tabPane.getSelectionModel().select(tab);
		});
		return openWebView;
	}

	private MenuItem buildOpenInBrowserMenuItem(CardView cardView) {
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

	private String getUrl(CardFX card) {
		try {
			JsonNode json = objectMapper.readTree(card.getJson());
			return json.get("scryfall_url").asText();
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
