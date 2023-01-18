
package com.alver.fatefall.app.fx.components.settings;

import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.interfaces.ComponentFactory;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.FatefallApplication;
import com.alver.fatefall.app.fx.components.about.AboutView;
import com.alver.fatefall.app.fx.components.plugins.PluginManagerView;
import com.dlsc.formsfx.model.structure.DoubleField;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.StringField;
import com.dlsc.preferencesfx.PreferencesFx;
import com.dlsc.preferencesfx.formsfx.view.controls.DoubleSliderControl;
import com.dlsc.preferencesfx.formsfx.view.controls.SimpleTextControl;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import com.dlsc.preferencesfx.model.Setting;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.image.Image;
import org.pf4j.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class FatefallPreferences {

	protected FatefallProperties properties;
	protected PluginManager pluginManager;
	protected PluginManagerView pluginManagerView;
	protected ComponentFactory componentFactory;
	protected AboutView aboutView;

	@Autowired
	public FatefallPreferences(
			FatefallProperties properties,
			PluginManager pluginManager,
			PluginManagerView pluginManagerView,
			ComponentFactory componentFactory,
			AboutView aboutView) {
		this.properties = properties;
		this.pluginManager = pluginManager;
		this.pluginManagerView = pluginManagerView;
		this.componentFactory = componentFactory;
		this.aboutView = aboutView;

		//Initializes
		Platform.runLater(this::buildPreferencesFX);
	}

	protected PreferencesFx buildPreferencesFX() {
		return PreferencesFx.of(FatefallApplication.class,
						buildAppearanceCategory(),
						buildPluginsCategory(),
						buildAboutCategory())
				.dialogTitle("Fatefall Preferences")
				.dialogIcon(logo);
	}

	public void show() {
		buildPreferencesFX().show();
	}

	private static final Image logo = new Image(Objects.requireNonNull(FatefallApplication.class.getResource("icon.png")).toExternalForm());


    /* ============
     * Categories *
     ============ */

	private static final ObservableList<BlurType> blurTypeOptions =
			FXCollections.observableList(Arrays.stream(BlurType.values()).toList());

	private Category buildAppearanceCategory() {
		Node exampleCardViewNode = componentFactory.buildCardView(new Card()).getFxViewNode();

		return Category.of("Appearance",
						Group.of("Application Theme",
								Setting.of("Stylesheet", properties.getStylesheetOptions(), properties.getStylesheetSelection()),
								Setting.of("Custom CSS", multiline(properties.getCustomCss()), properties.getCustomCss()),
								Setting.of("Base Color", properties.getBaseColor())))
				.subCategories(Category.of("Card View",
						Group.of("Example", Setting.of(exampleCardViewNode)),
						Group.of("Card Dimensions",
								Setting.of("View Mode", properties.getCardViewSkinOptions(), properties.getCardViewSkinSelection()),
								Setting.of("Scale", Field.ofDoubleType(properties.getCardViewScale()).render(new DoubleSliderControl(0.1, 2.0, 2)), properties.getCardViewScale()),
								Setting.of("Width", properties.getCardBaseWidth()),
								Setting.of("Height", properties.getCardBaseHeight()),
								Setting.of("Arc-Width", properties.getCardBaseArcWidth()),
								Setting.of("Arc-Height", properties.getCardBaseArcHeight())),
						Group.of("Shadow Effect",
								Setting.of("Color", properties.getCardFaceShadow().colorProperty()),
								Setting.of("Blur Type", blurTypeOptions, properties.getCardFaceShadow().blurTypeProperty()),
								Setting.of("Radius", Field.ofDoubleType(properties.getCardFaceShadow().radiusProperty()).render(new DoubleSliderControl(0, 127, 0)), properties.getCardFaceShadow().radiusProperty()),
								Setting.of("Spread", Field.ofDoubleType(properties.getCardFaceShadow().spreadProperty()).render(new DoubleSliderControl(0, 1, 2)), properties.getCardFaceShadow().spreadProperty()),
								Setting.of("Offset-X", properties.getCardFaceShadow().offsetXProperty()),
								Setting.of("Offset-Y", properties.getCardFaceShadow().offsetYProperty()))))
				.expand();
	}

	private StringField multiline(StringProperty property) {
		return Field.ofStringType(property)
				.multiline(true)
				.render(new SimpleTextControl());
	}

	private Category buildPluginsCategory() {
		Category[] subCategories = pluginManager.getExtensions(PreferenceCategoryProvider.class).stream()
				.map(PreferenceCategoryProvider::getCategory)
				.toArray(Category[]::new);

		return Category.of("Plugin Management", Setting.of(pluginManagerView))
				.subCategories(subCategories).expand();
	}

	private Category buildAboutCategory() {
		return Category.of("About", Setting.of(aboutView));
	}
}