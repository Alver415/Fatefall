package com.alver.fatefall.fx.app.view.entity.card.face;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.app.view.entity.card.template.TemplateController;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.fatefall.fx.core.model.Source;
import com.alver.fatefall.fx.core.utils.*;
import com.alver.fatefall.fx.plugin.FatefallPluginManager;
import com.alver.springfx.SpringFXLoader;
import com.alver.springfx.annotations.Prototype;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Map;

@Prototype
public class CardFaceView extends SubScene {
    private static final Logger log = LoggerFactory.getLogger(CardFaceView.class);

    protected final BeanFactory beanFactory;

    @Autowired
    public CardFaceView(BeanFactory beanFactory, FatefallProperties properties) {
        Group root = new Group();
        super(root, 400, 400);
        userAgentStylesheetProperty().bind(properties.getSubSceneStylesheetSelection()
                .map(s -> FatefallProperties.getStylesheetByNameMap().get(s)));

        //Replace binding with card.width and card.height properties instead of using properties for default.
        widthProperty().bind(properties.getCardScaledWidth());
        heightProperty().bind(properties.getCardScaledHeight());
        setClip(new SimpleClip(
                properties.getCardScaledWidth(),
                properties.getCardScaledHeight(),
                properties.getCardScaledArcWidth(),
                properties.getCardScaledArcHeight()));

        this.beanFactory = beanFactory;

        cardFace.subscribe(value -> {
            if (value == null) {
                FXUtils.runFx(() -> setRoot(loadDefaultCardFace()));
                return;
            }
            FXUtils.runAsync(() -> {
                SpringFXLoader loader = beanFactory.getBean(SpringFXLoader.class);
                try {
                    String imageUrl = value.getTemplate().getImageUrl();
                    String fxmlUrl = value.getTemplate().getFxmlUrl();
                    URL fxml = fxmlUrl != null ? new URL(fxmlUrl) :
                            imageUrl != null ? TemplateController.class.getResource("ImageTemplate.fxml") :
                                    TemplateController.class.getResource("PlaceholderTemplate.fxml");
                    loader.setLocation(fxml);

                    data = TreePropertyBuilder.buildAndBind(Map.of(
                            Source.CARD, value.getCard().dataProperty(),
                            Source.CARD_FACE, value.dataProperty(),
                            Source.TEMPLATE, value.getTemplate().dataProperty()));

                    loader.getNamespace().put("data", data);
                    FatefallPluginManager pluginManager = beanFactory.getBean(FatefallPluginManager.class);
                    PluginWrapper plugin = pluginManager.getPlugin("Magic: The Gathering");
                    ApplicationContext applicationContext = ((SpringPlugin) plugin.getPlugin()).getApplicationContext();
                    loader.setClassLoader(plugin.getPluginClassLoader());
                    loader.setApplicationContext(applicationContext);
                    loader.setControllerFactory(applicationContext::getBean);
                    setRoot(loader.load());
                    TemplateController controller = loader.getController();
                    controller.setData(data);
                    controller.imageProperty().set(imageUrl == null ? null : new Image(imageUrl));

                    getRoot().setOnContextMenuRequested(event -> {
                        ContextMenu contextMenu = new ContextMenu(controller.getContextMenuItems().toArray(new MenuItem[0]));
                        contextMenu.show((Node) event.getSource(), event.getScreenX(), event.getScreenY());
                    });

                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });
            root.setId(isFrontFace() ? "front" : "back");
        });
    }


    private boolean isFrontFace() {
        return cardFaceProperty().flatMap(CardFaceFX::cardProperty)
                .flatMap(CardFX::frontProperty)
                .getValue() == getCardFace();
    }

    private Parent loadDefaultCardFace(){
        Pane pane = new Pane();
        pane.setBackground(BackgroundUtil.checkeredBackground());
        return pane;
    }

    public void loadFxml(String fxml) {
        FXUtils.runFx(() -> setRoot(loadDefaultCardFace()));
        SpringFXLoader loader = beanFactory.getBean(SpringFXLoader.class);
        try {
            CardFaceFX cardFace = getCardFace();
            TreeProperty<Object> data = TreePropertyBuilder.buildAndBind(Map.of(
                    Source.CARD, cardFace.getCard().dataProperty(),
                    Source.CARD_FACE, cardFace.dataProperty(),
                    Source.TEMPLATE, cardFace.getTemplate().dataProperty()));

            loader.getNamespace().put("data", data);

            ByteArrayInputStream fxmlBytes = new ByteArrayInputStream(fxml.getBytes());
            setRoot(loader.load(fxmlBytes));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    protected TreeProperty<Object> data;

    //region Properties

    protected final ObjectProperty<CardFaceFX> cardFace = new SimpleObjectProperty<>(this, "cardFace");

    public ObjectProperty<CardFaceFX> cardFaceProperty() {
        return cardFace;
    }

    public CardFaceFX getCardFace() {
        return cardFaceProperty().get();
    }

    public void setCardFace(CardFaceFX cardFace) {
        this.cardFaceProperty().set(cardFace);
    }


    //endregion Properties


}
