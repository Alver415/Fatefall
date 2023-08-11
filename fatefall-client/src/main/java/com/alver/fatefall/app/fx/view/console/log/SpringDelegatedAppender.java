package com.alver.fatefall.app.fx.view.console.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.ext.spring.ApplicationContextHolder;
import ch.qos.logback.ext.spring.DelegatingLogbackAppender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringDelegatedAppender<E extends ILoggingEvent> extends AppenderBase<E> {
	private final ObservableList<E> logEvents = FXCollections.observableArrayList();

	@Override
	protected void append(E eventObject) {
		logEvents.add(eventObject);
	}

	public ObservableList<E> getLogEvents() {
		return logEvents;
	}

	/**
	 * This bean is needed for the {@link DelegatingLogbackAppender} to hook into the Spring context.
	 */
	@Bean
	public ApplicationContextHolder getConfigurationContextHolder() {
		return new ApplicationContextHolder();
	}
}