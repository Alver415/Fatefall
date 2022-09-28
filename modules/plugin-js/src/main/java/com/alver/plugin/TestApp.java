package com.alver.plugin;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.nio.IntBuffer;
import java.util.OptionalDouble;
import java.util.stream.LongStream;

public class TestApp extends Application {

    ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
    Context context = Context.create("js");
    LongStream.Builder deltas = LongStream.builder();

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        TextArea input = new TextArea("var hw = 'Hello World'; \r\nhw;");
        root.setCenter(input);
        TextArea output = new TextArea("Output...");
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("https://upload.wikimedia.org/wikipedia/commons/e/ea/Test.gif"));
        BorderPane right = new BorderPane();
        right.setTop(imageView);
        right.setBottom(output);
        root.setRight(right);

        input.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (KeyCode.ENTER.equals(e.getCode()) && e.isControlDown()) {
                execute(input, output, imageView);
                e.consume();
            }
        });
        new Thread(() -> {
            while (true) {
                try {
                    long start = System.currentTimeMillis();
                    execute(input, output, imageView);
                    long end = System.currentTimeMillis();
                    getDeltas().add(end - start);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    synchronized (getDeltas()) {
                        OptionalDouble average = getDeltas().build().average();
                        Platform.runLater(() -> primaryStage.setTitle(String.valueOf((int)average.getAsDouble())));

                        //reset
                        deltas = LongStream.builder();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(c -> System.exit(0));
        primaryStage.show();
    }

    private LongStream.Builder getDeltas() {
        return deltas;
    }

    private void execute(TextArea input, TextArea output, ImageView imageView) {
        try {
            Source source = Source.newBuilder("js", input.getText(), "user-input").build();
            Value value = context.eval(source);

            int[] data = new int[(int) value.getArraySize()];
            for (int i = 0; i < value.getArraySize(); i++) {
                data[i] = (int) value.getArrayElement(i).asDouble();
            }

            int width = 300;
            int height = 416;
            imageView.setImage(generateImage(width, height, data));

            Object response = value.as(Object.class);
            output.setText(response.toString());
        } catch (Exception e) {
            output.setText(e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    private Image generateImage(int width, int height, int[] data) {
        WritablePixelFormat<IntBuffer> format = PixelFormat.getIntArgbInstance();
        WritableImage img = new WritableImage(width, height);
        img.getPixelWriter().setPixels(0, 0, width, height, format, data, 0, width);
        return img;
    }
}
