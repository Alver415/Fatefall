package com.alver.fatefall.app.fx.view.console;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@FXMLPrototype
public class ConsoleController {

    private final ObservableList<ILoggingEvent> loggingEvents;

    @FXML
    private TableView<ILoggingEvent> tableView;

    public ConsoleController(){
        loggingEvents = FXCollections.observableArrayList();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            LoggingEvent e = new LoggingEvent();
            e.setMessage("Test");
            int rand = random.nextInt(3);
            e.setLevel(switch (rand) {
                case 0 -> Level.INFO;
                case 1 -> Level.WARN;
                case 2 -> Level.ERROR;
                default -> Level.INFO;
            });
            loggingEvents.add(e);
        }
    }

    @Autowired
    public ConsoleController(ObservableList<ILoggingEvent> loggingEvents){
        this.loggingEvents = loggingEvents;
    }

    @FXML
    public void initialize() {
        tableView.setItems(loggingEvents);
    }
}
