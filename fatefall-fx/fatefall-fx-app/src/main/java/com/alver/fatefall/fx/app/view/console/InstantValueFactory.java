package com.alver.fatefall.fx.app.view.console;

import ch.qos.logback.classic.spi.ILoggingEvent;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InstantValueFactory implements Callback<TableColumn.CellDataFeatures<ILoggingEvent, String>, ObservableValue<String>> {

    private static final String PATTERN_FORMAT = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
            .withZone(ZoneId.systemDefault());

    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<ILoggingEvent, String> param) {
        Instant instant = param.getValue().getInstant();
        String formatted = DATE_TIME_FORMATTER.format(instant);
        return new SimpleObjectProperty<>(formatted);
    }
}
