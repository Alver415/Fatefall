package com.alver.fatefall.fx.core.utils;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.util.Duration;

import java.util.function.Function;

public class JFXSmoothScroll {

	public static void smoothScrolling(Control control, double speed) {
		smoothVerticalScrolling(control, speed);
		smoothHorizontalScrolling(control, speed);
	}
	public static void smoothVerticalScrolling(Control control, double speed) {
		smoothScrolling(control, speed, Orientation.VERTICAL, Bounds::getHeight);
	}

	public static void smoothHorizontalScrolling(Control control, double speed) {
		smoothScrolling(control, speed, Orientation.HORIZONTAL, Bounds::getHeight);
	}

	private static void smoothScrolling(Control control, double speed, Orientation orientation, Function<Bounds, Double> sizeFunc) {
		ScrollBar scrollBar = getScrollbarComponent(control, orientation);
		if (scrollBar == null) {
			return;
		}
		scrollBar.setUnitIncrement(5);
		final double[] frictions = {0.99, 0.1, 0.05, 0.04, 0.03, 0.02, 0.01, 0.04, 0.01, 0.008, 0.008, 0.008, 0.008, 0.0006, 0.0005, 0.00003, 0.00001};
		final double[] pushes = {speed};
		final double[] derivatives = new double[frictions.length];
		final double[] lastVPos = {0};
		Timeline timeline = new Timeline();
		final EventHandler<MouseEvent> dragHandler = event -> timeline.stop();
		final EventHandler<ScrollEvent> scrollHandler = event -> {
			scrollBar.valueProperty().set(lastVPos[0]);
			if (event.getEventType() == ScrollEvent.SCROLL) {
				double direction = event.getDeltaY() > 0 ? -1 : 1;
				for (int i = 0; i < pushes.length; i++) {
					derivatives[i] += direction * pushes[i];
				}
				if (timeline.getStatus() == Animation.Status.STOPPED) {
					timeline.play();
				}

			}
			event.consume();
		};
		if (scrollBar.getParent() != null) {
			scrollBar.getParent().addEventHandler(MouseEvent.DRAG_DETECTED, dragHandler);
			scrollBar.getParent().addEventHandler(ScrollEvent.ANY, scrollHandler);
		}
		scrollBar.parentProperty().addListener((o, oldVal, newVal) -> {
			if (oldVal != null) {
				oldVal.removeEventHandler(MouseEvent.DRAG_DETECTED, dragHandler);
				oldVal.removeEventHandler(ScrollEvent.ANY, scrollHandler);
			}
			if (newVal != null) {
				newVal.addEventHandler(MouseEvent.DRAG_DETECTED, dragHandler);
				newVal.addEventHandler(ScrollEvent.ANY, scrollHandler);
			}
		});

		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(3), (event) -> {
			for (int i = 0; i < derivatives.length; i++) {
				derivatives[i] *= frictions[i];
			}
			for (int i = 1; i < derivatives.length; i++) {
				derivatives[i] += derivatives[i - 1];
			}
			double dy = derivatives[derivatives.length - 1];
			double size = sizeFunc.apply(scrollBar.getLayoutBounds());
			scrollBar.valueProperty().set(Math.min(Math.max(scrollBar.getValue() + dy / size, 0), 1));
			lastVPos[0] = scrollBar.getValue();
			if (Math.abs(dy) < 1) {
				if (Math.abs(dy) < 0.001) {
					timeline.stop();
				}
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
	}

	private static <T extends Control> ScrollBar getScrollbarComponent(T control, Orientation orientation) {
		if (control.lookup(".scroll-bar") instanceof ScrollBar scrollBar) {
			if (scrollBar.getOrientation().equals(orientation)) {
				return scrollBar;
			}
		}

		return null;
	}

}