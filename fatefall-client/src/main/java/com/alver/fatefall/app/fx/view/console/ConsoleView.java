package com.alver.fatefall.app.fx.view.console;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.alver.fatefall.app.fx.view.FXMLAutoLoad;
import com.alver.fatefall.app.fx.view.FxView;
import com.alver.fatefall.app.fx.view.console.log.SpringDelegatedAppender;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@FXMLAutoLoad
@Component
public class ConsoleView extends BorderPane implements FxView<ConsoleView> {

	private static final Logger log = LoggerFactory.getLogger(ConsoleView.class);

	@FXML
	private TextArea console;
	@FXML
	private TextField inputField;

	@Autowired
	private SpringDelegatedAppender<?> appender;

	@FXML
	private void initialize() {
		console.textProperty().bind(Bindings.createStringBinding(
				() -> appender.getLogEvents().stream()
						.map(ILoggingEvent::getMessage)
						.collect(Collectors.joining(System.lineSeparator())),
				appender.getLogEvents()
		));
	}

	@FXML
	private void submit() {
		log.info(inputField.getText());
		inputField.setText("");
	}
}
