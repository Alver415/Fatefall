package com.alver.fatefall;

import com.alver.fatefall.app.fx.component.mainstage.ApplicationView;
import com.alver.fatefall.preloader.PreloaderBeanPostProcessor;
import com.alver.fatefall.utils.ResourceUtil;
import com.sun.javafx.css.StyleManager;
import com.tangorabox.componentinspector.fx.FXComponentInspectorHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class FatefallFXApplication extends Application {

	private static final Image ICON = ResourceUtil.image("app/icon.png");

	@Value("${title}")
	private String title;

	private ConfigurableApplicationContext applicationContext;

	@Override
	public void init() {
		PreloaderBeanPostProcessor listener = new PreloaderBeanPostProcessor();
		notifyPreloader(listener);

		ApplicationContextInitializer<GenericApplicationContext> initializer = applicationContext -> {
			applicationContext.registerBean(FatefallFXApplication.class, () -> this);
			applicationContext.registerBean(PreloaderBeanPostProcessor.class, () -> listener);
		};

		applicationContext = new SpringApplicationBuilder(FatefallClientApplication.class)
				.initializers(initializer)
				.run();
	}

	@Override
	public void start(Stage primaryStage) {
		// Eager initialize UserAgentStylesheet so that it doesn't trigger later and undo user preferences.
		setUserAgentStylesheet(Application.STYLESHEET_MODENA);
		StyleManager.getInstance().addUserAgentStylesheet("/com/alver/fatefall/app/application.css");

		Scene scene = new Scene(applicationContext.getBean(ApplicationView.class));
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