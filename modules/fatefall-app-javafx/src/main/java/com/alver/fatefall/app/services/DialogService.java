package com.alver.fatefall.app.services;

import com.alver.fatefall.app.fx.components.settings.Settings;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.ERROR;

@Service
public class DialogService {

    private static final Logger LOGGER = LogManager.getLogger(DialogService.class);

    private static final String ERROR_TITLE = "Error";
    private static final String DEFAULT_ERROR_MESSAGE = "There was an unknown error.";

    @Autowired
    private Settings settings;

    public void error(String message) {
        error(ERROR_TITLE, message, null);
    }

    public void error(String title, String message) {
        alert(ERROR, title, message, null);
    }

    public void error(String title, String message, String info) {
        alert(ERROR, title, message, info);
    }

    public void alert(Alert.AlertType type, String title, String message) {
        alert(type, title, message, null);
    }

    public void alert(Alert.AlertType type, String title, String message, String info) {
        VBox content = new VBox();
        content.getChildren().add(new Label(message == null ? DEFAULT_ERROR_MESSAGE : message));

        if (info != null) {
            TextArea infoText = new TextArea(info);
            infoText.setEditable(false);
            infoText.setWrapText(false);
            content.getChildren().add(infoText);
        }

        alert(type, title, content);
    }

    public void alert(Alert.AlertType type, String title, Node content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(type);
            alert.getDialogPane().getStylesheets().add(settings.getStylesheet());
            alert.setTitle(title);
            alert.setResizable(true);

            alert.getDialogPane().setContent(content);
            alert.show();
        });
    }

    public Optional<String> textInput(String title, String contentText) {
        Dialog<String> dialog = new TextInputDialog();
        dialog.getDialogPane().getStylesheets().add(settings.getStylesheet());
        dialog.setTitle(title);
        dialog.setGraphic(null);
        dialog.setHeaderText(null);
        dialog.setContentText(contentText);
        return dialog.showAndWait();
    }

}
