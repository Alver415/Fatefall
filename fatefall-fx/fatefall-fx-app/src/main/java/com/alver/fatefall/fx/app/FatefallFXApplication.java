package com.alver.fatefall.fx.app;

import com.alver.fatefall.fx.app.component.mainstage.ApplicationController;
import com.alver.fatefall.fx.core.preloader.PreloaderBeanPostProcessor;
import com.alver.fatefall.fx.core.utils.StageManager;
import com.alver.springfx.SpringFX;
import com.alver.springfx.SpringFXApplication;
import com.tangorabox.componentinspector.fx.FXComponentInspectorHandler;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationContext;
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

public class FatefallFXApplication extends SpringFXApplication {

	private ConfigurableApplicationContext applicationContext;
	private SpringFX springFX;
	private StageManager stageManager;

	@Override
	public ApplicationContext initApplicationContext() {
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
			if (devProfile) {
				System.setProperty(MODE_PROPERTY_NAME, DEVELOPMENT.toString());
				System.setProperty(PLUGINS_DIR_PROPERTY_NAME, "../plugin-modules");
			} else {
				System.setProperty(MODE_PROPERTY_NAME, DEPLOYMENT.toString());
				System.setProperty(PLUGINS_DIR_PROPERTY_NAME, "plugins");
			}
		};

		applicationContext = new SpringApplicationBuilder(FatefallFXApp.class)
				.headless(false)
				.listeners(listener)
				.initializers(initializer)
				.run();
		springFX = applicationContext.getBean(SpringFX.class);
		stageManager = applicationContext.getBean(StageManager.class);
		return applicationContext;
	}

	@Override
	public void start(Stage stage) {
		stage = stageManager.create((Parent) springFX.loadView(ApplicationController.class));
		stage.setOnCloseRequest(_ -> stop());
		stage.show();

		FXComponentInspectorHandler.handleAll();
	}

	@Override
	public void stop() {
		applicationContext.close();
		Platform.exit();
	}

}