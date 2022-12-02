package com.alver.fatefall.app;

import com.alver.fatefall.app.fx.components.mainstage.MainStage;
import com.alver.fatefall.app.fx.components.settings.Settings;
import com.alver.fatefall.app.services.AsyncService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class FatefallApplication extends Application {

	public static class Launcher {
		public static void main(String... args) {
			Application.launch(FatefallApplication.class);
		}
	}

	private Settings settings;

	@Override
	public void start(Stage primaryStage) {
		//Disregard the original stage, create our own.

		Stage mainStage = new MainStage();
//		mainStage.getScene().getStylesheets().add(settings.getStylesheet());
		mainStage.setTitle("Fatefall");
		mainStage.getIcons().add(new Image(Objects.requireNonNull(
				FatefallApplication.class.getResource("icon.png")).toExternalForm()));
		mainStage.show();
	}

}