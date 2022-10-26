package components;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class TextComponent extends BaseComponent {

    private static final Font DEFAULT_FONT = Font.font("Beleren Bold", 16);

    protected final TextArea textArea = new TextArea();

    public TextComponent() {
        super();
        getChildren().add(textArea);
        setTranslateZ(1);

        textArea.setFont(DEFAULT_FONT);
        textArea.setPrefHeight(0);
        textArea.textProperty().bindBidirectional(valueProperty());
        textArea.promptTextProperty().bindBidirectional(idProperty());
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


}
