package com.alver.fatefall.app.fx.view.console;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class MessageValueFactory implements Callback<TableColumn.CellDataFeatures<ILoggingEvent, String>, ObservableValue<String>> {
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<ILoggingEvent, String> param) {
        String message = param.getValue().getMessage();
        if (param.getValue().getThrowableProxy() != null) {
            message += System.lineSeparator() + ThrowableProxyUtil.asString(param.getValue().getThrowableProxy());
        }
        return new SimpleStringProperty(message);
    }
}
