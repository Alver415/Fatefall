package com.alver.plugin;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import javafx.scene.shape.*;

import java.awt.*;
import java.io.*;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestApp extends Application {

    Stage stage;
    Scene scene;
    BorderPane root;
    TextArea input;
    TextArea output;
    ImageView imageView;
    StackPane stackPane;

    String JS_PATH = "scripts/image_generator.js";
    String IMG_PATH = "card_image.png";

    int BLACK = -16777216;
    int GREY = -8684677;
    int LIGHT_GREY = -3618616;
    int WHITE = -1;
    int CLEAR = 0;

    Border GREEN_BORDER = new Border(new BorderStroke(
            Color.LIGHTGREEN,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(3)));
    Border RED_BORDER = new Border(new BorderStroke(
            Color.RED,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(3)));

    @Override
    public void start(Stage stage) throws Exception {
        this.root = new BorderPane();
        this.input = new TextArea("var hw = 'Hello World'; \r\nhw;");
        this.output = new TextArea("Output...");
        this.imageView = new ImageView();
        this.scene = new Scene(root);
        this.stage = stage;
        root.setCenter(input);
        root.setBottom(output);
        stage.setScene(scene);

        stackPane = new StackPane();
        stackPane.setPadding(new Insets(10, 10, 10, 10));
        stackPane.getChildren().add(imageView);
        root.setRight(stackPane);

        int size = 64;
        int[] data = new int[size * size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int color = ((x % size) < size / 2 ^ (y % size) < size / 2) ? WHITE : LIGHT_GREY;
                data[x + y * size] = color;
            }
        }
        WritablePixelFormat<IntBuffer> format = PixelFormat.getIntArgbInstance();
        WritableImage checkerboard = new WritableImage(size, size);
        checkerboard.getPixelWriter().setPixels(0, 0, size, size, format, data, 0, size);
        root.setBackground(new Background(new BackgroundImage(
                checkerboard,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        output.setEditable(false);
        output.setFont(Font.font("monospaced"));
        input.setFont(Font.font("monospaced"));
        input.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.isControlDown()) {
                if (KeyCode.ENTER.equals(e.getCode())) {
                    execute();
                    e.consume();
                } else if (KeyCode.S.equals(e.getCode())) {
                    save(JS_PATH);
                    e.consume();
                } else if (KeyCode.L.equals(e.getCode())) {
                    load(JS_PATH);
                    e.consume();
                } else if (KeyCode.P.equals(e.getCode())) {
                    print(IMG_PATH);
                    e.consume();
                }
            }
        });

        load(JS_PATH);
        execute();

        stage.setOnCloseRequest(c -> System.exit(0));
        stage.show();
    }

    private void execute() {
        StringBuilder console = new StringBuilder();
        try (Context context = Context.newBuilder("js").build()) {
            Source source = Source.newBuilder("js", input.getText(), "user-input").build();
            try {
                Value value = context.eval(source);
                Value jsBindings = context.getBindings("js");

                int width = jsBindings.getMember("width").asInt();
                int height = jsBindings.getMember("height").asInt();
                Value dataValue = jsBindings.getMember("data");
                int[] data = new int[(int) dataValue.getArraySize()];
                for (int i = 0; i < dataValue.getArraySize(); i++) {
                    data[i] = (int) dataValue.getArrayElement(i).asDouble();
                }

                WritablePixelFormat<IntBuffer> format = PixelFormat.getIntArgbInstance();
                WritableImage img = new WritableImage(width, height);
                img.getPixelWriter().setPixels(0, 0, width, height, format, data, 0, width);
                imageView.setImage(img);
                output.setBorder(GREEN_BORDER);

                console.append("-- Output --").append(System.lineSeparator());
                console.append(value.as(Object.class)).append(System.lineSeparator());
            } finally {
                Value jsBindings = context.getBindings("js");
                console.append(System.lineSeparator()).append("-- Bindings --").append(System.lineSeparator());
                for (String k : jsBindings.getMemberKeys()) {
                    Object v = jsBindings.getMember(k).as(Object.class);
                    console.append(k).append(": ").append(v).append(System.lineSeparator());
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            console.append(System.lineSeparator()).append("-- Error --");
            console.append(System.lineSeparator()).append(sw);
            output.setBorder(RED_BORDER);
        } finally {
            output.setText(console.toString());
        }
    }

    private void save(String fileName) {
        try {
            Path path = Path.of(fileName);
            String content = input.getText();
            Files.write(path, content.getBytes());
            output.setText("Saved File: " + fileName);
            output.setBorder(GREEN_BORDER);
        } catch (IOException e) {
            e.printStackTrace(System.err);
            output.setText("Save Failed: " + fileName);
            output.setBorder(RED_BORDER);
        }
    }

    private void load(String fileName) {
        try {
            Path path = Path.of(fileName);
            String content = Files.readString(path, StandardCharsets.UTF_8);
            input.setText(content);
            output.setText("Loaded File: " + fileName);
            output.setBorder(GREEN_BORDER);
        } catch (IOException e) {
            e.printStackTrace(System.err);
            output.setText("Load Failed: " + fileName);
            output.setBorder(RED_BORDER);
        }
    }

    private void print(String fileName) {
        try {
            Image img = imageView.getImage();
            PngEncoderFX encoder = new PngEncoderFX(img, true);
            byte[] bytes = encoder.pngEncode();
            Path path = Path.of(fileName);
            Files.write(path, bytes);
            output.setText("Printed Image: " + fileName);
            output.setBorder(GREEN_BORDER);
            Desktop.getDesktop().open(path.toFile());
        } catch (IOException e) {
            e.printStackTrace(System.err);
            output.setText("Print Failed: " + fileName);
            output.setBorder(RED_BORDER);
        }
    }
}
