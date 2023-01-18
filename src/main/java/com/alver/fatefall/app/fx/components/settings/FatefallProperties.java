package com.alver.fatefall.app.fx.components.settings;

import com.sun.javafx.css.StyleManager;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

import static com.alver.fatefall.app.FatefallApplication.setUserAgentStylesheet;

@Configuration
public class FatefallProperties {

	@Bean
	public static Map<String, String> getStylesheetByNameMap() {
		return FXCollections.observableMap(Map.of(
				"Modena", "com/sun/javafx/scene/control/skin/modena/modena.css",
				"Caspian", "com/sun/javafx/scene/control/skin/caspian/caspian.css"));
	}

	@Bean
	public ObservableList<String> getStylesheetOptions() {
		return FXCollections.observableArrayList(getStylesheetByNameMap().keySet());
	}

	@Bean
	public ObjectProperty<String> getStylesheetSelection() {
		ObjectProperty<String> stylesheetSelection = new SimpleObjectProperty<>();
		stylesheetSelection.addListener((obs, oldValue, newValue) -> {
			String stylesheetUrl = getStylesheetByNameMap().get(newValue);
			StyleManager.getInstance().setDefaultUserAgentStylesheet(stylesheetUrl);
		});
		return stylesheetSelection;
	}

	@Bean
	public StringProperty getCustomCss() {
		StringProperty customCss = new SimpleStringProperty();
		customCss.addListener((obs, oldValue, newValue) -> {
			setUserAgentStylesheet("customCss", newValue);
		});
		return customCss;
	}

	@Bean
	public ObjectProperty<Color> getBaseColor() {
		SimpleObjectProperty<Color> baseColor = new SimpleObjectProperty<>(Color.BLACK);
		baseColor.addListener((ChangeListener<? super Color>) (obs, oldValue, newValue) -> {
			setUserAgentStylesheet("baseColor", """
					.root{
					    -fx-base: #%02X%02X%02X;
					    -fx-background: derive(-fx-base,0.26);
					    -fx-control-inner-background: derive(-fx-base,0.8);
					    -fx-control-inner-background-alt: derive(-fx-control-inner-background,-0.02);                        
					}""".formatted(
					(int) (newValue.getRed() * 255),
					(int) (newValue.getGreen() * 255),
					(int) (newValue.getBlue() * 255)));
		});
		return baseColor;
	}

	@Bean
	public ObservableList<String> getCardViewSkinOptions() {
		return FXCollections.observableList(List.of("Flippable", "Stackable", "Adjacent"));
	}

	@Bean
	public SimpleObjectProperty<String> getCardViewSkinSelection() {
		return new SimpleObjectProperty<>(getCardViewSkinOptions().stream().findFirst().orElseThrow());
	}

	@Bean
	public DoubleProperty getCardViewScale() {
		return new SimpleDoubleProperty(1);
	}
	@Bean
	public DoubleProperty getCardBaseWidth() {
		return new SimpleDoubleProperty(250);
	}
	@Bean
	public DoubleProperty getCardBaseHeight() {
		return new SimpleDoubleProperty(350);
	}
	@Bean
	public DoubleProperty getCardBaseArcWidth() {
		return new SimpleDoubleProperty(20);
	}
	@Bean
	public DoubleProperty getCardBaseArcHeight() {
		return new SimpleDoubleProperty(20);
	}
	@Bean
	public DoubleBinding getCardScaledWidth() {
		return getCardBaseWidth().multiply(getCardViewScale());
	}
	@Bean
	public DoubleBinding getCardScaledHeight() {
		return getCardBaseHeight().multiply(getCardViewScale());
	}
	@Bean
	public DoubleBinding getCardScaledArcWidth() {
		return getCardBaseArcWidth().multiply(getCardViewScale());
	}
	@Bean
	public DoubleBinding getCardScaledArcHeight() {
		return getCardBaseArcHeight().multiply(getCardViewScale());
	}

	@Bean
	public DropShadow getCardFaceShadow() {
		return new DropShadow(20, Color.BLACK);
	}
}
