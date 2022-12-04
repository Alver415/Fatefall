package com.alver.fatefall.plugin.implementations.cardview;

import com.alver.fatefall.app.fx.components.FxComponent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.DefaultProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

@DefaultProperty("front")
public class CardPane extends AnchorPane implements FxComponent {

	public enum Side {FRONT, BACK}

	public enum Spin {UP, RIGHT, DOWN, LEFT}

	public CardPane() {
		super();
		initFxml();
		sideProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == oldValue) return;
			getChildren().clear();
			getChildren().add(newValue == Side.FRONT ? getFront() : getBack());
			setScaleX(Math.abs(getScaleX()));
		});
		frontProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == oldValue || getSide() != Side.FRONT) return;
			getChildren().clear();
			getChildren().add(newValue);
		});
		backProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == oldValue || getSide() != Side.BACK) return;
			getChildren().clear();
			getChildren().add(newValue);
		});
		minWidthProperty().bind(cardWidthProperty());
		minHeightProperty().bind(cardHeightProperty());
		prefWidthProperty().bind(cardWidthProperty());
		prefHeightProperty().bind(cardHeightProperty());
		maxWidthProperty().bind(cardWidthProperty());
		maxHeightProperty().bind(cardHeightProperty());
	}

	/**
	 * === Card Spin Property ==
	 */
	protected ObjectProperty<Spin> spinProperty = new SimpleObjectProperty<>(Spin.UP);
	public final void setSpin(Spin value) {
		spinProperty().set(value);
	}
	public final Spin getSpin() {
		return spinProperty.get();
	}
	public final ObjectProperty<Spin> spinProperty() {
		return spinProperty;
	}

	/**
	 * === Card Side Property ==
	 */
	protected ObjectProperty<Side> sideProperty = new SimpleObjectProperty<>(Side.FRONT);
	public final void setSide(Side value) {
		sideProperty.set(value);
	}
	public final Side getSide() {
		return sideProperty.get();
	}
	public final ObjectProperty<Side> sideProperty() {
		return sideProperty;
	}

	/**
	 * Front Face Image Property
	 */
	protected CardFaceProperty frontProperty = new CardFaceProperty("front");
	public final void setFront(CardFace value) {
		frontProperty.set(value);
	}
	public final CardFace getFront() {
		return frontProperty.get();
	}
	public final ObjectProperty<CardFace> frontProperty() {
		return frontProperty;
	}

	/**
	 * Back Face Image Property
	 */
	protected CardFaceProperty backProperty = new CardFaceProperty("back");
	public final void setBack(CardFace value) {
		backProperty.set(value);
	}
	public final CardFace getBack() {
		return backProperty.get();
	}
	public final ObjectProperty<CardFace> backProperty() {
		return backProperty;
	}


	/**
	 * Card Width Property
	 */
	protected DoubleProperty cardWidthProperty;
	public final DoubleProperty cardWidthProperty() {
		if (cardWidthProperty == null){
			cardWidthProperty  = new SimpleDoubleProperty(this, "cardWidth", 400);
		}
		return cardWidthProperty;
	}
	public final void setCardWidth(Double value) {
		cardWidthProperty().set(value);
	}
	public final Double getCardWidth() {
		return cardWidthProperty().get();
	}

	/**
	 * Card Height Property
	 */
	protected DoubleProperty cardHeightProperty;
	public final DoubleProperty cardHeightProperty() {
		if (cardHeightProperty == null){
			cardHeightProperty = new SimpleDoubleProperty(this, "cardHeight", 600);
		}
		return cardHeightProperty;
	}
	public final void setCardHeight(Double value) {
		cardHeightProperty().set(value);
	}
	public final Double getCardHeight() {
		return cardHeightProperty().get();
	}

	private final class CardFaceProperty extends SimpleObjectProperty<CardFace> {
		private final String propertyName;

		CardFaceProperty(String propertyName) {
			this.propertyName = propertyName;
		}

		@Override
		public Object getBean() {
			return CardPane.this;
		}

		@Override
		public String getName() {
			return propertyName;
		}
	}

	/**
	 * Card Flip/Spin Animation
	 */
	private static final Duration end = Duration.seconds(0.2);
	private static final Duration half = end.divide(2);
	private static final Duration start = Duration.ZERO;

	private final Timeline flipTimeline = new Timeline();
	private final Timeline spinTimeline = new Timeline();

	public void spinLeft() {
		Spin[] spins = Spin.values();
		Spin nextSpin = spins[Math.floorMod(getSpin().ordinal() - 1, spins.length)];
		spin(-90);
		setSpin(nextSpin);
	}

	public void spinRight() {
		Spin[] spins = Spin.values();
		Spin nextSpin = spins[Math.floorMod(getSpin().ordinal() + 1, spins.length)];
		spin(90);
		setSpin(nextSpin);
	}

	public void spin(double amount) {
		double rotationStart = getSpin().ordinal() * 90;
		double rotationEnd = rotationStart + amount;

		ObservableList<KeyFrame> keyFrames = spinTimeline.getKeyFrames();
		keyFrames.clear();

		setRotationAxis(Rotate.Z_AXIS);
		keyFrames.add(new KeyFrame(start, new KeyValue(rotateProperty(), rotationStart)));
		keyFrames.add(new KeyFrame(end, new KeyValue(rotateProperty(), rotationEnd)));
		spinTimeline.setOnFinished((event) -> {
			setRotate(getSpin().ordinal() * 90);
		});
		spinTimeline.playFromStart();
	}

	public void flip() {
		flipTo(getSide() == Side.FRONT ? Side.BACK : Side.FRONT);
	}

	public void flipTo(Side side) {
		//First reset spin.
		setSpin(Spin.UP);
		setRotationAxis(Rotate.Z_AXIS);
		setRotate(0);

		//Then handle flip.
		ObservableList<KeyFrame> keyFrames = flipTimeline.getKeyFrames();
		keyFrames.clear();

		setRotationAxis(Rotate.Y_AXIS);
		keyFrames.addAll(
				new KeyFrame(start,
						new KeyValue(rotationAxisProperty(), Rotate.Y_AXIS),
						new KeyValue(rotateProperty(), 0)),
				new KeyFrame(half,
						new KeyValue(rotateProperty(), 90),
						new KeyValue(sideProperty(), side)),
				new KeyFrame(end,
						new KeyValue(rotateProperty(), 0))
		);
		flipTimeline.playFromStart();
	}

}
