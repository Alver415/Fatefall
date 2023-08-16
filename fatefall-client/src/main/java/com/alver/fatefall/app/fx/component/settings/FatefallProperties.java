package com.alver.fatefall.app.fx.component.settings;

import com.sun.javafx.css.StyleManager;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.CssParser;
import javafx.css.Stylesheet;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Configuration
public class FatefallProperties {

	@Value("${directory.installation}")
	private Path installationDirectory;

	@Bean
	public ListProperty<String> getAdditionalStylesheetsOptions() {
		Path styleSheetsDirectory = installationDirectory.resolve("stylesheets");
		try {
			Files.createDirectories(styleSheetsDirectory);
			try (Stream<Path> paths = Files.walk(styleSheetsDirectory)) {
				return new SimpleListProperty<>(FXCollections.observableArrayList(paths
						.filter(p -> Files.isRegularFile(p) && p.toString().endsWith(".css"))
						.map(Path::toString)
						.toList()));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Bean
	public ListProperty<String> getAdditionalStylesheetsSelections() {
		ListProperty<String> customCss = new SimpleListProperty<>(FXCollections.observableArrayList());
		customCss.addListener((ListChangeListener<? super String>) c -> {
			while (c.next()) {
				for (String removed : c.getRemoved()) {
					setUserAgentStylesheet(removed, "");
				}
				for (String added : c.getAddedSubList()) {
					try {
						setUserAgentStylesheet(added, Files.readString(Path.of(added)));
					} catch (IOException e) {
						System.out.println(e);
					}
				}
			}
		});
		return customCss;
	}

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
		stylesheetSelection.set("Modena");
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
		SimpleObjectProperty<Color> baseColor = new SimpleObjectProperty<>();
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
		// Set default after adding listener so that it triggers and applies the change.
		baseColor.set(Color.BLACK);
		return baseColor;
	}

	@Bean
	public ObservableList<String> getCardViewSkinOptions() {
		return FXCollections.observableList(List.of("Flippable", "Stacked", "Adjacent"));
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
		BlurType blurType = BlurType.GAUSSIAN;
		Color color = new Color(0, 0, 0, 0.2);
		int radius = 20;
		double spread = 0.5;
		int offsetX = 10;
		int offsetY = 10;
		return new DropShadow(blurType, color, radius, spread, offsetX, offsetY);
	}

	public static void setUserAgentStylesheet(String id, String css) {
		try {
			Stylesheet stylesheet = new CssParser().parse(id, css);
			StyleManager.getInstance().removeUserAgentStylesheet(id);
			StyleManager.getInstance().addUserAgentStylesheet(null, stylesheet);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
