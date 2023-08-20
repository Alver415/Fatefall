package com.alver.fatefall.app.fx.view.console;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.alver.fatefall.app.fx.view.FXMLAutoLoad;
import com.alver.fatefall.app.fx.view.FxView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@FXMLAutoLoad
@Component
public class ConsoleView extends TableView<ILoggingEvent> implements FxView<ConsoleView> {

    private final ObservableList<ILoggingEvent> loggingEvents;

    @Autowired
    public ConsoleView(ObservableList<ILoggingEvent> loggingEvents){
        this.loggingEvents = loggingEvents;
    }

    @FXML
    public void initialize() {
        setItems(loggingEvents);
    }
}
