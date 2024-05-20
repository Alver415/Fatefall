package com.alver.fatefall.mtg.plugin.template;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.app.view.entity.card.template.TemplateController;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import org.reactfx.value.Var;

import java.util.*;
import java.util.stream.Collectors;

import static com.alver.fatefall.mtg.plugin.template.ColorIdentity.*;

@FXMLPrototype
public class MagicCardController extends TemplateController {

    public MagicCardController(FatefallProperties properties) {
        super(properties);
    }

    @FXML
    protected void initialize() {
        initializeColorBindings();
        initializeArtBindings();
    }

    private void initializeArtBindings() {
        Property<Object> artUrl = Var.mapBidirectional(this.art, Image::getUrl, url -> new Image(url == null || url.toString().isBlank() ? "file:/missing" : (String)url));
        Property<Object> artProp = Var.fromVal(dataProperty().flatMap(a -> a.get("art")), value -> getData().get("art").set(value));
        artUrl.bindBidirectional(artProp);
    }

    private void initializeColorBindings() {
        this.colors.subscribe(colors -> {
            if (colors == null){
                Image colorlessBackground = getBackground(COLORLESS);
                leftBackground.set(colorlessBackground);
                rightBackground.set(colorlessBackground);
                return;
            }
            switch (colors.size()) {
                case 0: {
                    Image colorlessBackground = getBackground(COLORLESS);
                    leftBackground.set(colorlessBackground);
                    rightBackground.set(colorlessBackground);
                    break;
                }
                case 1: {
                    Iterator<? extends Color> i = colors.iterator();
                    Image background = getBackground(of(i.next()));
                    leftBackground.set(background);
                    rightBackground.set(background);
                    break;
                }
                case 2: {
                    Iterator<? extends Color> i = colors.iterator();
                    leftBackground.set(getBackground(ColorIdentity.of(i.next())));
                    rightBackground.set(getBackground(ColorIdentity.of(i.next())));
                    break;
                }
                default: {
                    Image background = getBackground(WUBRG);
                    leftBackground.set(background);
                    rightBackground.set(background);
                    break;
                }
            }
        });

        Property<Object> colorStringProp = Var.mapBidirectional(this.colors,
                set -> set.stream().map(Object::toString).collect(Collectors.joining(",")),
                string -> FXCollections.observableSet(string == null ? Set.of() : Arrays.stream(((String)string).split(",")).map(String::trim).map(Color::valueOf).collect(Collectors.toSet())));

        Property<Object> colorProp = Var.fromVal(dataProperty().flatMap(a -> a.get("colors")), value -> getData().get("colors").set(value));

        colorStringProp.bindBidirectional(colorProp);
    }

    private static final Map<ColorIdentity, Image> backgrounds = Map.of(
            COLORLESS, getImageResource("images/ccard.jpg"),
            MONO_WHITE, getImageResource("images/wcard.jpg"),
            MONO_BLUE, getImageResource("images/ucard.jpg"),
            MONO_BLACK, getImageResource("images/bcard.jpg"),
            MONO_RED, getImageResource("images/rcard.jpg"),
            MONO_GREEN, getImageResource("images/gcard.jpg"),
            WUBRG, getImageResource("images/mcard.jpg"));

    private static Image getImageResource(String location) {
        return new Image(Objects.requireNonNull(MagicCardController.class.getResource(location)).toExternalForm());
    }

    private static Image getBackground(ColorIdentity colorIdentity) {
        return backgrounds.get(colorIdentity);
    }

    //region Properties

    protected final ObjectProperty<Image> leftBackground = new SimpleObjectProperty<>(this, "leftBackground");

    public ObjectProperty<Image> leftBackgroundProperty() {
        return leftBackground;
    }

    public Image getLeftBackground() {
        return leftBackgroundProperty().get();
    }

    public void setLeftBackground(Image image) {
        leftBackgroundProperty().set(image);
    }

    protected final ObjectProperty<Image> rightBackground = new SimpleObjectProperty<>(this, "rightBackground");

    public ObjectProperty<Image> rightBackgroundProperty() {
        return rightBackground;
    }

    public Image getRightBackground() {
        return rightBackgroundProperty().get();
    }

    public void setRightBackground(Image image) {
        rightBackgroundProperty().set(image);
    }
    protected final ObjectProperty<Image> art = new SimpleObjectProperty<>(this, "art");

    public ObjectProperty<Image> artProperty() {
        return art;
    }

    public Image getArt() {
        return artProperty().get();
    }

    public void setArt(Image image) {
        artProperty().set(image);
    }

    protected final StringProperty name = new SimpleStringProperty(this, "name");
    protected final ObjectProperty<ManaCost> manaCost = new SimpleObjectProperty<>(this, "manaCost");
    protected final SetProperty<Color> colors = new SimpleSetProperty<>(this, "colors", FXCollections.observableSet());

    protected final ListProperty<String> cardTypes = new SimpleListProperty<>(this, "cardTypes");
    protected final ListProperty<String> superTypes = new SimpleListProperty<>(this, "superTypes");
    protected final ListProperty<String> subTypes = new SimpleListProperty<>(this, "subTypes");
    protected final ObjectProperty<Rarity> rarity = new SimpleObjectProperty<>(this, "rarity");

    protected final IntegerProperty power = new SimpleIntegerProperty(this, "power");
    protected final IntegerProperty toughness = new SimpleIntegerProperty(this, "toughness");
    protected final IntegerProperty loyalty = new SimpleIntegerProperty(this, "loyalty");

    //endregion Properties

    //region Context Menu

    @Override
    public List<MenuItem> getContextMenuItems(){
        Menu menu = new Menu("Colors");
        menu.getItems().setAll(Arrays.stream(Color.values())
                .map(color -> {
                    MenuItem menuItem = new MenuItem(color.name());
                    menuItem.setOnAction(_ -> {
                        if (colors.contains(color))
                            colors.remove(color);
                        else
                            colors.add(color);
                    });
                    return menuItem;
                })
                .toList());
        return List.of(menu);
    }

    //endregion Context Menu
}
