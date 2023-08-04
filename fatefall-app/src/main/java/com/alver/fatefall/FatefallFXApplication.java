package com.alver.fatefall;

import com.alver.fatefall.app.fx.component.mainstage.ApplicationView;
import com.jpro.webapi.JProApplication;
import com.jpro.webapi.WebAPI;
import com.sun.javafx.application.ParametersImpl;
import com.tangorabox.componentinspector.fx.FXComponentInspectorHandler;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class FatefallFXApplication extends JProApplication {

//    static final String APPLICATION_CSS = Objects.requireNonNull(FatefallFXApplication.class.getResource("application.css")).toExternalForm();
//    static final Image ICON = new Image(Objects.requireNonNull(FatefallFXApplication.class.getResourceAsStream("icon.png")));

    private ConfigurableApplicationContext context;

    @Autowired
    protected ApplicationView applicationView;
//
//    @Value("${title}")
//    private String title;

    @Override
    public void init() {
        // Eager initialize UserAgentStylesheet so that it doesn't trigger later and undo user preferences.
        setUserAgentStylesheet(Application.STYLESHEET_MODENA);

        ApplicationContextInitializer<GenericApplicationContext> initializer = ac -> {
            ac.registerBean(WebAPI.class, FatefallFXApplication.this::getWebAPI);
            ac.registerBean(FatefallFXApplication.class, () -> FatefallFXApplication.this);
            ac.registerBean(HostServices.class, this::getHostServices);
            ac.registerBean(Parameters.class, this::getParameters);
        };

        if (getParameters() == null) {
            ParametersImpl.registerParameters(this, new ParametersImpl());
        } else {
            System.err.println(getParameters().getRaw());
        }
        this.context = new SpringApplicationBuilder()
                .sources(FatefallSpringApplication.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(String[]::new));
    }

    @Override
    public void start(Stage primaryStage) {
//        StyleManager.getInstance().addUserAgentStylesheet(APPLICATION_CSS);
        Scene scene = new Scene(applicationView);
        primaryStage.setScene(scene);
//        primaryStage.getIcons().add(ICON);
//        primaryStage.setTitle(title);
        primaryStage.setOnCloseRequest(e -> stop());
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        primaryStage.show();

        FXComponentInspectorHandler.handleAll();
    }

    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }

}