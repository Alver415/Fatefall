package com.alver.fatefall.app.fx.view.console;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import com.alver.fatefall.app.fx.view.FXMLAutoLoad;
import com.alver.fatefall.app.fx.view.FxView;
import com.alver.fatefall.app.fx.view.console.log.SpringDelegatedAppender;
import com.alver.fatefall.utils.FXAsyncUtils;
import com.google.common.collect.Streams;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.border.Border;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FXMLAutoLoad
@Component
public class ConsoleView extends BorderPane implements FxView<ConsoleView> {

	private static final Logger log = LoggerFactory.getLogger(ConsoleView.class);

	@Autowired
	private SpringDelegatedAppender<ILoggingEvent> appender;

	@FXML
	private ScrollPane scrollPane;
	@FXML
	private TextFlow console;

	@FXML
	public void initialize() {
		appender.getLogEvents().forEach(this::append);
		appender.getLogEvents().addListener((ListChangeListener<ILoggingEvent>) change -> {
			while (change.next()) {
				if (change.wasAdded()) {
					change.getAddedSubList().forEach(this::append);
				}
			}
		});
		console.heightProperty().addListener((observable, oldValue, newValue) -> scrollPane.setVvalue((Double) newValue));

	}
	private void append(ILoggingEvent logEvent) {
		Text level = createText(logEvent.getLevel().levelStr + ": ", getColor(logEvent.getLevel()));
		Text message = createText(logEvent.getMessage());
		Text newline = createText(System.lineSeparator());
		if (logEvent.getThrowableProxy() != null) {
			Tooltip tooltip = new Tooltip();
			String stackTraceString = Arrays.stream(logEvent.getThrowableProxy().getStackTraceElementProxyArray())
					.map(ste -> ste.getStackTraceElement().toString())
					.map(Object::toString).collect(Collectors.joining(System.lineSeparator() + "\t"));
			tooltip.setText(stackTraceString);
			Tooltip.install(message, tooltip);
		}

		FXAsyncUtils.runFx(() -> {
			console.getChildren().addAll(level, message, newline);
		});
	}
	private Text createText(String string) {
		return createText(string, Color.BLACK);
	}
	private Text createText(String string, Color color) {
		Text text = new Text(string);
		text.setFont(Font.font("monospaced"));
		text.setFill(color);
		return text;
	}

	private Color getColor(Level logLevel) {
		return switch (logLevel.levelInt) {
			case Level.ERROR_INT -> Color.RED;
			case Level.WARN_INT -> Color.ORANGE;
			case Level.INFO_INT -> Color.BLACK;
			case Level.DEBUG_INT -> Color.BLUE;
			case Level.TRACE_INT -> Color.GRAY;
			default -> {
				log.warn("Unknown logLevel: {}", logLevel);
				yield Color.BLACK;
			}
		};
	}
}
