package component;

import app.tool.EditorToolPane;
import app.tool.EditorToolPopOver;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import utils.AnchorProperty;
import utils.Bindingz;
import utils.DragListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.stream.Stream;

import static utils.AnchorProperty.Anchor.*;

public class Component extends AnchorPane implements FXMLLoadable {

    protected EditorToolPane editor;

    @FXML
    protected void initialize() {
        editor = new EditorToolPane(this);
        Bindingz.bindBidirectional(translateZProperty(), viewOrderProperty(), -1d);

        addEventFilter(MouseEvent.ANY, new DragListener(this));
        addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                EditorToolPopOver.request(editor, e.getScreenX(), e.getScreenY());
            }
        });
    }

    //region Properties

    protected DoubleProperty top = new AnchorProperty(this, TOP, null);
    protected DoubleProperty right = new AnchorProperty(this, RIGHT, null);
    protected DoubleProperty bottom = new AnchorProperty(this, BOTTOM, null);
    protected DoubleProperty left = new AnchorProperty(this, LEFT, null);

    public DoubleProperty topProperty() {
        return top;
    }

    public DoubleProperty rightProperty() {
        return right;
    }

    public DoubleProperty bottomProperty() {
        return bottom;
    }

    public DoubleProperty leftProperty() {
        return left;
    }

    public Double getTop() {
        return topProperty().get();
    }

    public void setTop(Double top) {
        topProperty().set(top);
    }

    public Double getRight() {
        return rightProperty().get();
    }

    public void setRight(Double right) {
        rightProperty().set(right);
    }

    public Double getBottom() {
        return bottomProperty().get();
    }

    public void setBottom(Double bottom) {
        bottomProperty().set(bottom);
    }

    public Double getLeft() {
        return leftProperty().get();
    }

    public void setLeft(Double left) {
        leftProperty().set(left);
    }


    //endregion

    //region Font Loading
    private static final List<Font> FONTS = loadFonts(Path.of("fonts"));

    private static List<Font> loadFonts(Path basePath) {
        PathMatcher fontExtensionMatcher = FileSystems.getDefault().getPathMatcher("glob:**.ttf");
        try (Stream<Path> walk = Files.walk(basePath)) {
            return walk.filter(fontExtensionMatcher::matches)
                    .map(Component::loadFont)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Font loadFont(Path path) {
        try {
            return Font.loadFont(path.toUri().toURL().toString(), 12);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    //endregion
}
