package components;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextComponent extends BaseComponent {

    private static final Font DEFAULT_FONT = Font.font("Beleren Bold", 16);

    protected final TextArea textArea = new TextArea();

    public TextComponent() {
        super();
        getChildren().add(new BorderPane(textArea));
        setTranslateZ(1);

        textArea.setWrapText(true);
        textArea.setFont(DEFAULT_FONT);
        textArea.textProperty().bindBidirectional(valueProperty());
        textArea.promptTextProperty().bindBidirectional(idProperty());
        textArea.prefWidthProperty().bind(widthProperty());
        textArea.prefHeightProperty().bind(heightProperty());
    }

    public ObjectProperty<Font> fontProperty() {
        return textArea.fontProperty();
    }

    public final void setFont(Font value) {
        fontProperty().setValue(value);
    }

    public final Font getFont() {
        return fontProperty() == null ? Font.getDefault() : fontProperty().getValue();
    }

    public final void setFontName(String name) {
        setFont(Font.font(name, getFontSize()));
    }

    public final String getFontName() {
        return getFont().getName();
    }

    public final void setFontSize(double size) {
        setFont(Font.font(getFontName(), size));
    }

    public final double getFontSize() {
        return getFont().getSize();
    }

    public final void setFontColor(Color color) {
        textArea.setStyle(" -fx-text-fill: #" + color.toString().substring(2));
    }

    public final Color getFontColor() {
        return Color.BLACK;
    }


}
