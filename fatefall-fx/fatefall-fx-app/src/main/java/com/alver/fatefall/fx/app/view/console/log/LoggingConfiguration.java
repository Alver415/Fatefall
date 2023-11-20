package com.alver.fatefall.fx.app.view.console.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.ext.spring.ApplicationContextHolder;
import ch.qos.logback.ext.spring.DelegatingLogbackAppender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {

    /**
     * This bean is needed for the {@link DelegatingLogbackAppender} to hook into the Spring context.
     */
    @Bean
    public ApplicationContextHolder getConfigurationContextHolder() {
        return new ApplicationContextHolder();
    }

    @Bean
    public ObservableList<ILoggingEvent> getLoggingEvents() {
        return FXCollections.observableArrayList();
    }
}
