package com.alver.fatefall.app.services;

import com.alver.fatefall.FatefallFXApplication;
import com.alver.fatefall.app.services.fileselector.FileSelector;
import com.alver.fatefall.app.services.fileselector.FileSelectorImpl;
import com.alver.fatefall.app.services.fileselector.JProFileSelector;
import com.alver.fatefall.app.services.modal.Modal;
import com.jpro.webapi.WebAPI;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.function.Consumer;

@Component
public class DialogManager {

    @Autowired
    protected FatefallFXApplication application;

    public void show(Dialog<?> dialog) {
        if (WebAPI.isBrowser()) {
            DialogPane dialogPane = dialog.getDialogPane();
            Scene scene = new Scene(dialogPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            application.getWebAPI().openStageAsPopup(stage);
        } else {
            dialog.show();
        }
    }

    public void showStage(Stage stage) {
        if (WebAPI.isBrowser()) {
            application.getWebAPI().openStageAsPopup(stage);
        } else {
            stage.show();
        }
    }

    public void showFileSelector(Consumer<File> onFileSubmitted) {
        FileSelector fileSelector = WebAPI.isBrowser() ?
                new JProFileSelector(application.getWebAPI(), onFileSubmitted) :
                new FileSelectorImpl(onFileSubmitted);
        fileSelector.show();
    }

    public void showAlert(String message) {
        new Modal("Title", message).show();
    }
}
