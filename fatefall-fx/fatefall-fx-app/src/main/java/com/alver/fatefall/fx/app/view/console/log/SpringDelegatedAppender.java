package com.alver.fatefall.fx.app.view.console.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringDelegatedAppender extends AppenderBase<ILoggingEvent> {

    private final ObservableList<ILoggingEvent> loggingEvents;

    @Autowired
    public SpringDelegatedAppender(ObservableList<ILoggingEvent> loggingEvents){
        this.loggingEvents = loggingEvents;
    }

    @Override
    protected void append(ILoggingEvent loggingEvent) {
        loggingEvents.add(loggingEvent);
    }
}