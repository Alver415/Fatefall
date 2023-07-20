package com.alver.fatefall.app;

import com.alver.fatefall.FatefallLauncher;
import com.alver.fatefall.app.fx.components.mainstage.ApplicationView;
import com.jpro.webapi.JProApplication;
import com.sun.javafx.application.ParametersImpl;
import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.css.CssParser;
import javafx.css.Stylesheet;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;
import java.util.Objects;

public class FatefallApplication extends JProApplication {

    static final String APPLICATION_CSS = Objects.requireNonNull(FatefallApplication.class.getResource("application.css")).toExternalForm();
    static final Image ICON = new Image(Objects.requireNonNull(FatefallApplication.class.getResourceAsStream("icon.png")));

    private ConfigurableApplicationContext context;

    @Autowired
    protected ApplicationView applicationView;

    @Value("${title}")
    private String title;

    @Override
    public void init() {
        // Eager initialize UserAgentStylesheet so that it doesn't trigger later and undo user preferences.
        setUserAgentStylesheet(Application.STYLESHEET_MODENA);

        ApplicationContextInitializer<GenericApplicationContext> initializer = ac -> {
            ac.registerBean(FatefallApplication.class, () -> FatefallApplication.this);
            ac.registerBean(HostServices.class, this::getHostServices);
            ac.registerBean(Parameters.class, this::getParameters);
        };

        if (getParameters() == null) {
            ParametersImpl.registerParameters(this, new ParametersImpl());
        } else {
            System.err.println(getParameters().getRaw());
        }
        this.context = new SpringApplicationBuilder()
                .sources(FatefallLauncher.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(String[]::new));
    }

    @Override
    public void start(Stage primaryStage) {
        StyleManager.getInstance().addUserAgentStylesheet(APPLICATION_CSS);
        Scene scene = new Scene(applicationView);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(ICON);
        primaryStage.setTitle(title);
        primaryStage.setOnCloseRequest(e -> stop());
        primaryStage.show();
    }

    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
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