package com.alver.fatefall.app.services;

import com.alver.fatefall.app.FatefallApplication;
import com.jpro.webapi.WebAPI;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationWindowManager {

	@Autowired
	protected FatefallApplication application;

	public void show(Dialog<?> dialog){
		if (WebAPI.isBrowser()){
			DialogPane dialogPane = dialog.getDialogPane();
			Scene scene = new Scene(dialogPane);
			Stage stage = new Stage();
			stage.setScene(scene);
			application.getWebAPI().openStageAsPopup(stage);
		} else {
			dialog.show();
		}
	}
}
