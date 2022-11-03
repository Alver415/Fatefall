package com.alver.fatefall.templatebuilder.app.console;

import com.alver.fatefall.templatebuilder.components.block.FXMLLoadable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.textfield.CustomTextField;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Language;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.*;

public class PolyglotConsole extends TabPane implements FXMLLoadable {
    private static final URL FXML = FXMLLoadable.fxmlResource(PolyglotConsole.class);
    private static final Logger log = LogManager.getLogger(PolyglotConsole.class);

    protected final Context context;
    protected final Map<String, Value> bindingsMap = new LinkedHashMap<>();
    protected final Map<String, PolyglotBindingTable> bindingsPaneMap = new LinkedHashMap<>();

    @FXML
    protected TextFlow output;
    @FXML
    protected ScrollPane scrollPane;

    @FXML
    protected CustomTextField input;
    @FXML
    protected ComboBox<Language> language;

    protected History history;


    public PolyglotConsole() {
        load(PolyglotConsole.class, FXML);
        context = Context.newBuilder()
                .out(getOutputStream())
                .allowAllAccess(true)
                .build();

        setupLanguageDropdown();
        setupBindings();

        history = new History();

        input.setFont(Font.font("monospaced", 12));
        output.heightProperty().addListener((obs, o, n) -> {
            scrollPane.setVvalue(1);
        });
    }

    @FXML
    public void initialize() {
        input.setOnKeyPressed(e -> {
            if (e.isMetaDown() || e.isControlDown()) {
                switch (e.getCode()) {
                    case UP -> input.setText(history.getLast());
                    case DOWN -> input.setText(history.getNext());
                    case ENTER -> submit();
                }
                e.consume();
            }
        });
    }

    public Context getContext() {
        return context;
    }


    private void setupBindings() {
        bindingsMap.put("Polyglot", context.getPolyglotBindings());
        for (Language language : context.getEngine().getLanguages().values()) {
            bindingsMap.put(language.getName(), context.getBindings(language.getId()));
        }

        for (Map.Entry<String, Value> entry : bindingsMap.entrySet()) {
            Tab tab = new Tab();
            tab.setText(entry.getKey());
            PolyglotBindingTable table = new PolyglotBindingTable();
            tab.setContent(table);
            table.refresh(entry.getValue());
            bindingsPaneMap.put(entry.getKey(), table);
            getTabs().add(tab);
        }
    }

    private void refreshBindings() {
        bindingsMap.put("Polyglot", context.getPolyglotBindings());
        for (Language language : context.getEngine().getLanguages().values()) {
            bindingsMap.put(language.getName(), context.getBindings(language.getId()));
        }
        for (Map.Entry<String, PolyglotBindingTable> entry : bindingsPaneMap.entrySet()) {
            PolyglotBindingTable table = entry.getValue();
            table.refresh(bindingsMap.get(entry.getKey()));
        }
    }

    public void execute(Language language, String text) {
        log.info("_execute(%s,%s)".formatted(language, text));
        try {
            println("[%s]> %s".formatted(language.getName(), text), Color.DARKVIOLET);

            Value result = context.eval(language.getId(), text);
            println(result.toString(), Color.GREEN);

            refreshBindings();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            println(stackTrace, Color.DARKRED);
        }
    }


    protected void setupLanguageDropdown() {
        Callback<ListView<Language>, ListCell<Language>> cellFactory = f -> new ListCell<Language>() {
            @Override
            public void updateItem(Language item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setText(item.getName());
                }
            }
        };

        language.setButtonCell(cellFactory.call(null));
        language.setCellFactory(cellFactory);
        language.getItems().addAll(getLanguages());
        language.getSelectionModel().select(1);
    }

    private Collection<Language> getLanguages() {
        return getContext().getEngine().getLanguages().values();
    }

    private void submit() {
        String text = input.getText();
        input.clear();
        history.add(text);
        execute(getSelectedLanguage(), text);
    }

    private Language getSelectedLanguage() {
        return language.getSelectionModel().getSelectedItem();
    }

    public void println(String line) {
        println(line, Color.BLACK);
    }

    public void println(String line, Color color) {
        print(line, color);
        print(System.lineSeparator(), Color.BLACK);
    }

    public void print(String line, Color color) {
        Text text = new Text(line);
        text.setFill(color);
        text.setFont(Font.font("monospaced", 12));
        output.getChildren().add(text);
    }

    public OutputStream getOutputStream() {
        return new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                print(String.valueOf((char) b), Color.BLACK);
            }
        };
    }

    protected static class History {
        protected int index = 0;
        protected List<String> list = new ArrayList<>();

        public void add(String text) {
            index = -1;
            list.add(0, text);
        }

        public String getNext() {
            index = Math.min(index + 1, list.size() - 1);
            return list.get(index);
        }

        public String getLast() {
            index = Math.max(index - 1, 0);
            return list.get(index);
        }
    }
}


