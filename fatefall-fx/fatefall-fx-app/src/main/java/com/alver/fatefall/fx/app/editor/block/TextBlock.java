package com.alver.fatefall.fx.app.editor.block;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class TextBlock extends Block<String> {

	protected TextArea textArea = new TextArea();
	
	public TextBlock() {
		super();
		getChildren().add(textArea);
		setViewOrder(-1); //Bump back so it is drawn on top.

		textArea.textProperty().bindBidirectional(valueProperty());
		textArea.promptTextProperty().bindBidirectional(idProperty());
		textArea.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
	}

	public TextBlock(String text){
		this();
		setText(text);
	}

	public ObservableList<Node> getChildrenUnmodifiable(){
		return super.getChildrenUnmodifiable();
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
