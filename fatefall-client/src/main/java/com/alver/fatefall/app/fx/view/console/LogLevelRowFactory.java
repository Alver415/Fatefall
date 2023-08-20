package com.alver.fatefall.app.fx.view.console;

import ch.qos.logback.classic.spi.ILoggingEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class LogLevelRowFactory implements Callback<TableView<ILoggingEvent>, TableRow<ILoggingEvent>> {

    @Override
    public TableRow<ILoggingEvent> call(TableView<ILoggingEvent> param) {
        return new TableRow<>() {
            @Override
            public void updateItem(ILoggingEvent item, boolean empty) {
                super.updateItem(item, empty);
                getStyleClass().clear();
                if (item != null) {
                    getStyleClass().add(item.getLevel().levelStr.toLowerCase());
                }
            }
        };
    }
}
