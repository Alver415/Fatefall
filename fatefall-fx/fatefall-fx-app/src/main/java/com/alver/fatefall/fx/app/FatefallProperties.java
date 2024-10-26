package com.alver.fatefall.fx.app;

import atlantafx.base.theme.Theme;
import com.alver.fatefall.fx.core.utils.ResourceUtil;
import com.alver.fatefall.fx.theme.Themes;
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
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class FatefallProperties {

	private static final Logger log = LoggerFactory.getLogger(FatefallProperties.class);
	@Value("${title}")
	protected String title;
	@Value("${directory.installation}")
	protected Path installationDirectory;
	@Value("${web.client.scheme}")
	private String scheme;
	@Value("${web.client.host}")
	private String host;
	@Value("${web.client.port}")
	private Integer port;

	private static FatefallProperties INSTANCE;

	public static FatefallProperties getInstance() {
		if (INSTANCE == null){
			new FatefallProperties();
		}
		return INSTANCE;
	}

	public FatefallProperties() {
		INSTANCE = this;
	}

	@Bean
	public StringProperty schemeProperty() {
		return new SimpleStringProperty(scheme);
	}

	@Bean
	public StringProperty hostProperty() {
		return new SimpleStringProperty(host);
	}

	@Bean
	public IntegerProperty portProperty() {
		return new SimpleIntegerProperty(port);
	}

	@Bean
	public ObjectProperty<Image> iconProperty() {
		return new SimpleObjectProperty<>(ResourceUtil.image("/com/alver/fatefall/icon.png"));
	}

	@Bean
	public StringProperty titleProperty() {
		return new SimpleStringProperty(title);
	}

	@Bean
	public ListProperty<String> getAdditionalStylesheetsOptions() {
		try {
			Path styleSheetsDirectory = installationDirectory.resolve("stylesheets");
			Files.createDirectories(styleSheetsDirectory);
			try (Stream<Path> paths = Files.walk(styleSheetsDirectory)) {
				List<String> cssFileNames = paths
						.filter(p -> Files.isRegularFile(p) && p.toString().endsWith(".css"))
						.map(Path::toString)
						.toList();

				SimpleListProperty<String> additionalStylesheetsOptions = new SimpleListProperty<>(FXCollections.observableArrayList(cssFileNames));
				return additionalStylesheetsOptions;
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
						log.error(e.getMessage(), e);
					}
				}
			}
		});
		return customCss;
	}

	@Bean
	public static Map<String, String> getStylesheetByNameMap() {
		return FXCollections.observableMap(Themes.getThemes().stream()
				.collect(Collectors.toMap(Theme::getName, Theme::getUserAgentStylesheet)));
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
	public ObjectProperty<String> getSubSceneStylesheetSelection() {
		ObjectProperty<String> stylesheetSelection = new SimpleObjectProperty<>();
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

	@Bean
	public ObjectProperty<LocaleOption> getLocale() {
		SimpleObjectProperty<LocaleOption> localeProperty = new SimpleObjectProperty<>(LocaleOption.ENGLISH);
		localeProperty.addListener((observable, oldValue, newValue) -> {
			try {
				Locale.setDefault(newValue.locale);
			} catch (Exception e) {
			}
		});
		if (localeProperty.get() == null) {
			localeProperty.set(LocaleOption.ENGLISH);
		}
		return localeProperty;
	}

	@Bean
	public ObservableList<LocaleOption> getLocaleOptions() {
//		return FXCollections.observableList(Arrays.asList(Locale.getAvailableLocales()));

		return FXCollections.observableList(List.of(
				LocaleOption.ENGLISH,
				LocaleOption.SPANISH,
				LocaleOption.FRENCH));
	}

	public enum LocaleOption {
		ENGLISH(Locale.ENGLISH),
		FRENCH(Locale.FRENCH),
		SPANISH(new Locale("es", "ES"));

		private final Locale locale;
		private final String displayName;

		LocaleOption(Locale locale) {
			this.locale = locale;
			this.displayName = locale.getDisplayLanguage(locale);
		}

		@Override
		public String toString() {
			return displayName;
		}
	}

}
