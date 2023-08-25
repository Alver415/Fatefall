package com.alver.fatefall;

import com.alver.fatefall.app.fx.component.mainstage.ApplicationController;
import com.alver.fatefall.preloader.PreloaderBeanPostProcessor;
import com.alver.fatefall.utils.ResourceUtil;
import com.alver.springfx.SpringFXLoader;
import com.sun.javafx.css.StyleManager;
import com.tangorabox.componentinspector.fx.FXComponentInspectorHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

import static org.pf4j.AbstractPluginManager.MODE_PROPERTY_NAME;
import static org.pf4j.AbstractPluginManager.PLUGINS_DIR_PROPERTY_NAME;
import static org.pf4j.RuntimeMode.DEPLOYMENT;
import static org.pf4j.RuntimeMode.DEVELOPMENT;

public class FatefallFXApplication extends Application {

	private static final Image ICON = ResourceUtil.image("app/icon.png");

	@Value("${title}")
	private String title;

	private ConfigurableApplicationContext applicationContext;
	private SpringFXLoader springFXLoader;

	@Override
	public void init() {
		PreloaderBeanPostProcessor preloaderBeanPostProcessor = new PreloaderBeanPostProcessor();
		notifyPreloader(preloaderBeanPostProcessor);

		ApplicationContextInitializer<GenericApplicationContext> initializer = applicationContext -> {
			applicationContext.registerBean(FatefallFXApplication.class, () -> this);
			applicationContext.registerBean(PreloaderBeanPostProcessor.class, () -> preloaderBeanPostProcessor);
		};

		//TODO: Find a better solution.
		//Problem is that PF4j initializes before you can change the mode programmatically or via spring injection.
		//So for now, we have to do it via system properties before the bean has a chance to init.
		ApplicationListener<ApplicationEnvironmentPreparedEvent> listener = event -> {
			ConfigurableEnvironment environment = event.getEnvironment();
			boolean devProfile = Arrays.asList(environment.getActiveProfiles()).contains("dev");
			if (devProfile){
				System.setProperty(MODE_PROPERTY_NAME, DEVELOPMENT.toString());
				System.setProperty(PLUGINS_DIR_PROPERTY_NAME, "plugin-modules");
			} else {
				System.setProperty(MODE_PROPERTY_NAME, DEPLOYMENT.toString());
				System.setProperty(PLUGINS_DIR_PROPERTY_NAME, ".wd/plugins");
			}
		};

		applicationContext = new SpringApplicationBuilder(FatefallClientApplication.class)
				.listeners(listener)
				.initializers(initializer)
				.run();
		springFXLoader = applicationContext.getBean(SpringFXLoader.class);
	}

	@Override
	public void start(Stage primaryStage) {
		// Eager initialize UserAgentStylesheet so that it doesn't trigger later and undo user preferences.
		setUserAgentStylesheet(Application.STYLESHEET_MODENA);
		StyleManager.getInstance().addUserAgentStylesheet("/com/alver/fatefall/app/application.css");

		Scene scene = new Scene((Parent) (springFXLoader.load(ApplicationController.class).view()));
		scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

		primaryStage.setScene(scene);
		primaryStage.setTitle(title);
		primaryStage.getIcons().add(ICON);
		primaryStage.setOnCloseRequest(e -> stop());
		primaryStage.show();

		FXComponentInspectorHandler.handleAll();
	}

	@Override
	public void stop() {
		applicationContext.close();
		Platform.exit();
	}

}