
package com.alver.fatefall.app.fx.component.settings;

import com.alver.fatefall.app.fx.component.about.AboutView;
import com.alver.fatefall.app.fx.component.plugins.PluginManagerController;
import com.alver.fatefall.app.fx.view.entity.card.CardView;
import com.alver.fatefall.utils.ResourceUtil;
import com.alver.springfx.SpringFXLoader;
import com.alver.springfx.annotations.FXMLComponent;
import com.alver.springfx.model.FXMLControllerAndView;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.StringField;
import com.dlsc.preferencesfx.PreferencesFx;
import com.dlsc.preferencesfx.formsfx.view.controls.DoubleSliderControl;
import com.dlsc.preferencesfx.formsfx.view.controls.SimpleTextControl;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import com.dlsc.preferencesfx.model.Setting;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.image.Image;
import org.pf4j.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@FXMLComponent
public class PreferencesController {

    private static final Image ICON = ResourceUtil.image("/com/alver/fatefall/app/icon.png");

    protected FatefallProperties properties;
    protected PluginManager pluginManager;
    protected SpringFXLoader springFXLoader;

    @Autowired
    public PreferencesController(
            FatefallProperties properties,
            PluginManager pluginManager,
            SpringFXLoader springFXLoader) {
        this.properties = properties;
        this.pluginManager = pluginManager;
        this.springFXLoader = springFXLoader;

        //Initializes
        Platform.runLater(this::buildPreferencesFX);
    }

    protected PreferencesFx buildPreferencesFX() {
        return PreferencesFx.of(this.getClass(),
                        buildAppearanceCategory(),
                        buildPluginsCategory(),
                        buildAboutCategory())
                .dialogTitle("Fatefall - Preferences")
                .dialogIcon(ICON);
    }

    public void show() {
        buildPreferencesFX().show();
    }

    /* ============
     * Categories *
     ============ */

    private static final ObservableList<BlurType> blurTypeOptions =
            FXCollections.observableList(Arrays.stream(BlurType.values()).toList());

    private Category buildAppearanceCategory() {

        return Category.of("Appearance",
                        Group.of("Application Theme",
                                Setting.of("Base Color", properties.getBaseColor()),
                                Setting.of("User Agent Stylesheet", properties.getStylesheetOptions(), properties.getStylesheetSelection()),
                                Setting.of("Additional Stylesheets", properties.getAdditionalStylesheetsOptions(), properties.getAdditionalStylesheetsSelections())))
                .subCategories(Category.of("Card View",
                        Group.of("Example", Setting.of(new CardView())),
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

        FXMLControllerAndView<PluginManagerController, Object> loaded = springFXLoader.load(PluginManagerController.class);
        return Category.of("Plugin Management", Setting.of((Node) loaded.view()))
                .subCategories(subCategories).expand();
    }

    private Category buildAboutCategory() {
        FXMLControllerAndView<AboutView, Object> loaded = springFXLoader.load(AboutView.class);
        return Category.of("About", Setting.of((Node) loaded.view()));
    }
}