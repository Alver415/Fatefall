package components;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class TextComponent extends BaseComponent {

    protected final TextArea textArea = new TextArea();

    public TextComponent() {
        super();
        getChildren().add(textArea);
        textArea.setFont(Font.font("Beleren Bold", 16));
        AnchorPane.setTopAnchor(textArea, 0d);
        AnchorPane.setRightAnchor(textArea, 0d);
        AnchorPane.setBottomAnchor(textArea, 0d);
        AnchorPane.setLeftAnchor(textArea, 0d);
        setPrefHeight(30);
        setPadding(new Insets(5));

        textArea.textProperty().bindBidirectional(valueProperty());
        textArea.promptTextProperty().bindBidirectional(idProperty());
    }

    public void setText(String text){
        textArea.setText(text);
    }
    public String getText(){
        return textArea.getText();
    }
}
