package com.alver.fatefall.fx.app.view.console;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLPrototype
public class ConsoleController {

    private final ObservableList<ILoggingEvent> loggingEvents;

    @FXML
    private TableView<ILoggingEvent> tableView;

    @Autowired
    public ConsoleController(ObservableList<ILoggingEvent> loggingEvents){
        this.loggingEvents = loggingEvents;
    }

    @FXML
    public void initialize() {
        tableView.setItems(loggingEvents);
    }
}
