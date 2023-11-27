package com.alver.fatefall.fx.app.view.entity.card;

import javafx.beans.DefaultProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

@DefaultProperty("content")
public class Viewport extends StackPane {

	private final ObjectProperty<Node> contentProperty;
	private final DoubleProperty scaleProperty;

	private final StackPane canvas;

	public Viewport() {
		Rectangle clip = new Rectangle();
		clip.widthProperty().bind(widthProperty());
		clip.heightProperty().bind(heightProperty());
		setClip(clip);

		contentProperty = new SimpleObjectProperty<>(this, "content", null);
		scaleProperty = new SimpleDoubleProperty(this, "scale", 1.0);
		canvas = new StackPane();

		Gestures gestures = new Gestures();
		addEventFilter(MouseEvent.MOUSE_PRESSED, gestures.onMousePressedEventHandler);
		addEventFilter(MouseEvent.MOUSE_DRAGGED, gestures.onMouseDraggedEventHandler);
		addEventFilter(ScrollEvent.ANY, gestures.onScrollEventHandler);
		canvas.scaleXProperty().bind(scaleProperty);
		canvas.scaleYProperty().bind(scaleProperty);
		canvas.scaleZProperty().bind(scaleProperty);

		contentProperty.addListener((observable, oldValue, newValue) -> {
			canvas.getChildren().setAll(newValue);
		});

		getChildren().setAll(canvas);
	}

	public ObjectProperty<Node> contentProperty() {
		return contentProperty;
	}
	public void setContent(Node node) {
		contentProperty().set(node);
	}
	public Node getContent() {
		return contentProperty().get();
	}

	public DoubleProperty scaleProperty() {
		return scaleProperty;
	}

	public double getScale() {
		return scaleProperty.get();
	}

	public void setScale(double scale) {
		scaleProperty.set(scale);
	}
	public void reset() {
		setScale(1);
		canvas.setTranslateX(0);
		canvas.setTranslateY(0);
	}

	private static class DragContext {
		double mouseAnchorX;
		double mouseAnchorY;
		double translateAnchorX;
		double translateAnchorY;
	}

	private class Gestures {

		private static final double MAX_SCALE = 100.0d;
		private static final double MIN_SCALE = 0.1d;

		private final DragContext dragContext = new DragContext();

		private static boolean notCtrlClick(MouseEvent event) {
			return !event.isPrimaryButtonDown() || !event.isControlDown();
		}

		private final EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
			if (notCtrlClick(event)) return;
			dragContext.mouseAnchorX = event.getX();
			dragContext.mouseAnchorY = event.getY();
			dragContext.translateAnchorX = canvas.getTranslateX();
			dragContext.translateAnchorY = canvas.getTranslateY();

		};
		private final EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {
			if (notCtrlClick(event)) return;
			canvas.setTranslateX(dragContext.translateAnchorX - dragContext.mouseAnchorX + event.getX());
			canvas.setTranslateY(dragContext.translateAnchorY - dragContext.mouseAnchorY + event.getY());
			event.consume();
		};
		private final EventHandler<ScrollEvent> onScrollEventHandler = event -> {
			double delta = 1.2;
			double scale = getScale(); // currently we only use Y, same value is used for X
			double oldScale = scale;

			scale = event.getDeltaY() < 0 ?
					scale / delta :
					scale * delta;
			scale = Math.min(Math.max(scale, MIN_SCALE), MAX_SCALE);

			double f = (scale / oldScale) - 1;
			double dx = (event.getX() - (canvas.getBoundsInParent().getWidth() / 2 + canvas.getBoundsInParent().getMinX()));
			double dy = (event.getY() - (canvas.getBoundsInParent().getHeight() / 2 + canvas.getBoundsInParent().getMinY()));

			setScale(scale);
			canvas.setTranslateX(canvas.getTranslateX() - (f * dx));
			canvas.setTranslateY(canvas.getTranslateY() - (f * dy));

			event.consume();
		};
	}
}
