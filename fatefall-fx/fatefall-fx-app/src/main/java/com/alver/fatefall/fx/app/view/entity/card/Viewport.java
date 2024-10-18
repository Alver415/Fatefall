package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.core.utils.BackgroundUtil;
import javafx.beans.DefaultProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

@DefaultProperty("content")
public class Viewport extends SubScene {

	private final ObjectProperty<Node> contentProperty = new SimpleObjectProperty<>(this, "content", null);
	private final DoubleProperty scaleProperty = new SimpleDoubleProperty(this, "scale", 1.0);

	private final StackPane contentWrapper;

	public Viewport() {
		this(new StackPane());
	}
	public Viewport(StackPane root) {
		super(root, 400, 400);
		widthProperty().bind(parentProperty().flatMap(p -> ((Region)p).widthProperty()));
		heightProperty().bind(parentProperty().flatMap(p -> ((Region)p).heightProperty()));
		setManaged(false);

		contentWrapper = root;

		Gestures gestures = new Gestures();
		addEventFilter(MouseEvent.MOUSE_PRESSED, gestures.onMousePressedEventHandler);
		addEventFilter(MouseEvent.MOUSE_DRAGGED, gestures.onMouseDraggedEventHandler);
		addEventFilter(ScrollEvent.ANY, gestures.onScrollEventHandler);
		contentWrapper.scaleXProperty().bind(scaleProperty);
		contentWrapper.scaleYProperty().bind(scaleProperty);
		contentWrapper.scaleZProperty().bind(scaleProperty);
		contentWrapper.setBackground(BackgroundUtil.checkeredBackground());
		contentProperty.addListener((_, _, newValue) -> contentWrapper.getChildren().setAll(newValue));
		setFill(Color.TRANSPARENT);
		userAgentStylesheetProperty().bind(FatefallProperties.getInstance().getSubSceneStylesheetSelection()
				.map(s -> FatefallProperties.getStylesheetByNameMap().get(s)));
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
		contentWrapper.setTranslateX(0);
		contentWrapper.setTranslateY(0);
	}

	private static class DragContext {
		double mouseAnchorX;
		double mouseAnchorY;
		double translateAnchorX;
		double translateAnchorY;
	}

	private class Gestures {

		private static final double MAX_SCALE = 5.0d;
		private static final double MIN_SCALE = 0.2d;

		private final DragContext dragContext = new DragContext();

		private static boolean notCtrlClick(MouseEvent event) {
			return !event.isPrimaryButtonDown() || !event.isAltDown();
		}

		private final EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
			if (notCtrlClick(event)) return;
			dragContext.mouseAnchorX = event.getX();
			dragContext.mouseAnchorY = event.getY();
			dragContext.translateAnchorX = contentWrapper.getTranslateX();
			dragContext.translateAnchorY = contentWrapper.getTranslateY();

		};
		private final EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {
			if (notCtrlClick(event)) return;
			contentWrapper.setTranslateX(dragContext.translateAnchorX - dragContext.mouseAnchorX + event.getX());
			contentWrapper.setTranslateY(dragContext.translateAnchorY - dragContext.mouseAnchorY + event.getY());
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
			double dx = (event.getX() - (contentWrapper.getBoundsInParent()
					.getWidth() / 2 + contentWrapper.getBoundsInParent().getMinX()));
			double dy = (event.getY() - (contentWrapper.getBoundsInParent()
					.getHeight() / 2 + contentWrapper.getBoundsInParent().getMinY()));

			setScale(scale);
			contentWrapper.setTranslateX(contentWrapper.getTranslateX() - (f * dx));
			contentWrapper.setTranslateY(contentWrapper.getTranslateY() - (f * dy));

			event.consume();
		};
	}
}
