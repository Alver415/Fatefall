package component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanProperty;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TextComponent extends Component {
    private static final URL FXML = FXMLLoadable.findFXML(TextComponent.class);

    @FXML
    protected TextArea textArea;

    public TextComponent() {
        load(TextComponent.class, FXML);
        setViewOrder(-1); //Bump back so it is drawn on top.

        textArea.promptTextProperty().bindBidirectional(idProperty());
        textArea.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
    }

    public StringProperty textProperty() {
        return textArea.textProperty();
    }

    public String getText() {
        return textProperty().get();
    }

    public void setText(String text) {
        textProperty().set(text);
    }

    public ObjectProperty<Font> fontProperty() {
        return textArea.fontProperty();
    }

    public Font getFont() {
        return fontProperty().get();
    }

    public void setFont(Font font) {
        fontProperty().set(font);
    }

    public void setFontName(String name) {
        setFont(Font.font(name, getFontSize()));
    }

    public String getFontName() {
        return getFont().getName();
    }

    public void setFontSize(double size) {
        setFont(Font.font(getFontName(), size));
    }

    public double getFontSize() {
        return getFont().getSize();
    }

    public void setFontColor(Color color) {
        textArea.setStyle("-fx-text-fill: #" + color.toString().substring(2));
    }

    public Color getFontColor() {
        return Color.BLACK;
    }

}
