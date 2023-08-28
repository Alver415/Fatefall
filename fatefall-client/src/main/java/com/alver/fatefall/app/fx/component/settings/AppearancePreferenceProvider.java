package com.alver.fatefall.app.fx.component.settings;

import com.alver.fatefall.app.fx.view.entity.card.CardView;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.preferencesfx.formsfx.view.controls.DoubleSliderControl;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import com.dlsc.preferencesfx.model.Setting;
import javafx.collections.FXCollections;
import javafx.scene.effect.BlurType;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AppearancePreferenceProvider implements PreferenceCategoryProvider {

    protected FatefallProperties properties;

    public AppearancePreferenceProvider(FatefallProperties properties) {
        this.properties = properties;
    }

    @Override
    public Category getCategory() {
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
                                Setting.of("Blur Type", FXCollections.observableList(Arrays.stream(BlurType.values()).toList()), properties.getCardFaceShadow().blurTypeProperty()),
                                Setting.of("Radius", Field.ofDoubleType(properties.getCardFaceShadow().radiusProperty()).render(new DoubleSliderControl(0, 127, 0)), properties.getCardFaceShadow().radiusProperty()),
                                Setting.of("Spread", Field.ofDoubleType(properties.getCardFaceShadow().spreadProperty()).render(new DoubleSliderControl(0, 1, 2)), properties.getCardFaceShadow().spreadProperty()),
                                Setting.of("Offset-X", properties.getCardFaceShadow().offsetXProperty()),
                                Setting.of("Offset-Y", properties.getCardFaceShadow().offsetYProperty()))));
    }

}
