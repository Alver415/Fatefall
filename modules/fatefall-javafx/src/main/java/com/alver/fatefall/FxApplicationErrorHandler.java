package com.alver.fatefall;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

import static com.alver.fatefall.FatefallApplication.DEFAULT_STYLE;
import static javafx.scene.control.Alert.AlertType.ERROR;

@Component
public class FxApplicationErrorHandler implements Thread.UncaughtExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(FxApplicationErrorHandler.class);

    private static final String ERROR_TITLE = "Error";
    private static final String DEFAULT_ERROR_MESSAGE = "There was an unknown error.";

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        LOGGER.error(throwable);
        error(ERROR_TITLE, throwable.getMessage(), getStackTraceAsString(throwable));
    }

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
            alert.getDialogPane().getStylesheets().add(DEFAULT_STYLE);
            alert.setTitle(title);
            alert.setResizable(true);

            alert.getDialogPane().setContent(content);
            alert.show();
        });
    }

    private String getStackTraceAsString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
