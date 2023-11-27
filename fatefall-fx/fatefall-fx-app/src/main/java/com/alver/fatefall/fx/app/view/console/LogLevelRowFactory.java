package com.alver.fatefall.fx.app.view.console;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import javafx.css.PseudoClass;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LogLevelRowFactory implements Callback<TableView<ILoggingEvent>, TableRow<ILoggingEvent>> {

    private static final Map<Level, PseudoClass> PSEUDO_CLASSES =
            Set.of(Level.TRACE, Level.DEBUG, Level.INFO, Level.WARN, Level.ERROR).stream()
                    .collect(Collectors.toMap(k -> k, v -> PseudoClass.getPseudoClass(v.levelStr)));

    @Override
    public TableRow<ILoggingEvent> call(TableView<ILoggingEvent> param) {
        return new TableRow<>() {
            @Override
            public void updateItem(ILoggingEvent item, boolean empty) {
                super.updateItem(item, empty);
                PSEUDO_CLASSES.values().forEach(pseudoClass -> pseudoClassStateChanged(pseudoClass, false));
                if (item != null) {
                    PseudoClass pseudoClass = PSEUDO_CLASSES.get(item.getLevel());
                    pseudoClassStateChanged(pseudoClass, true);
                }
            }
        };
    }
}
