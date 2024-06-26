package com.alver.fatefall.scryfall.plugin.component;

import com.alver.fatefall.fx.app.view.entity.card.CardView;
import com.alver.fatefall.fx.core.interfaces.AppController;
import com.alver.fatefall.fx.core.interfaces.AppView;
import com.alver.fatefall.fx.core.model.CardFX;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.MenuItem;
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
	protected AppController appController;

	@Autowired
	protected ObjectMapper objectMapper;

	public MenuItem buildOpenInWebViewMenuItem(CardView cardView) {
		MenuItem openWebView = new MenuItem();
		openWebView.setText("Open in WebView");
		openWebView.setOnAction(a -> {
			WebView webView = new WebView();
			webView.getEngine().load(getUrl(cardView.getCard()));
			appController.registerView(AppView.of(cardView.cardProperty().map(CardFX::nameProperty).getValue(), webView));
		});
		return openWebView;
	}

	public MenuItem buildOpenInBrowserMenuItem(CardView cardView) {
		MenuItem openBrowser = new MenuItem();
		openBrowser.setText("Open in browser");
		openBrowser.setOnAction(a -> {
			try {
				System.setProperty("java.awt.headless", "false");
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
			return json.get("scryfall_uri").asText();
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
