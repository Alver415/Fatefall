
package com.alver.fatefall.app.fx.component.settings;

import com.alver.fatefall.app.fx.component.about.AboutView;
import com.alver.fatefall.app.fx.component.plugins.PluginManagerView;
import com.alver.fatefall.app.fx.view.entity.card.CardView;
import com.alver.fatefall.app.ComponentFactory;
import com.alver.fatefall.utils.ResourceUtil;
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
import javafx.scene.effect.BlurType;
import javafx.scene.image.Image;
import org.pf4j.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class FatefallPreferences {

    private static final Image ICON = ResourceUtil.image("/com/alver/fatefall/app/icon.png");

    protected FatefallProperties properties;
    protected PluginManager pluginManager;
    protected PluginManagerView pluginManagerView;
    protected ComponentFactory componentFactory;
    protected AboutView aboutView;
    protected CardView<?> demoCard;

    @Autowired
    public FatefallPreferences(
            FatefallProperties properties,
            PluginManager pluginManager,
            PluginManagerView pluginManagerView,
            ComponentFactory componentFactory,
            AboutView aboutView,
            CardView<?> exampleCard) {
        this.properties = properties;
        this.pluginManager = pluginManager;
        this.pluginManagerView = pluginManagerView;
        this.componentFactory = componentFactory;
        this.aboutView = aboutView;
        this.demoCard = exampleCard;

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
        PreferencesFx preferencesFx = buildPreferencesFX();
        preferencesFx.show();
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
                        Group.of("Example", Setting.of(demoCard.getFxViewNode())),
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