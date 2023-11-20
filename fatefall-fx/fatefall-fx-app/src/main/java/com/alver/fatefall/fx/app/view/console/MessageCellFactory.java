package com.alver.fatefall.fx.app.view.console;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class MessageCellFactory implements Callback<TableColumn<ILoggingEvent, ILoggingEvent>, TableCell<ILoggingEvent, ILoggingEvent>> {

    @Override
    public TableCell<ILoggingEvent, ILoggingEvent> call(TableColumn<ILoggingEvent, ILoggingEvent> param) {
        return new TableCell<>() {
            boolean showStack = false;

            @Override
            public void updateItem(ILoggingEvent value, boolean empty) {
                super.updateItem(value, empty);
                if (value != null && !empty) {
                    Text textNode = new Text();
                    String message = value.getFormattedMessage();
                    textNode.setText(message);
                    if (value.getThrowableProxy() != null) {
                        String stackTrace = ThrowableProxyUtil.asString(value.getThrowableProxy());
                        textNode.setOnMouseClicked(click -> {
                            showStack = !showStack;
                            textNode.setText(!showStack ?
                                    message :
                                    message + ": " + stackTrace);
                        });
                    }
                    setGraphic(textNode);
                } else {
                    setGraphic(null);
                }

            }
        };
    }
}
