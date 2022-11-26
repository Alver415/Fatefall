package com.alver.fatefall.app.fx.components.cardview;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CardEditorView extends CardView implements EventHandler<MouseEvent> {

	@FXML
	public void initialize() {
		super.initialize();
		addEventFilter(MouseEvent.ANY, this);
	}

	private enum Mode {
		TRANSLATE, SCALE, ROTATE;
	}

	private volatile Block<?> block;

	private volatile Mode mode = Mode.TRANSLATE;

	private volatile double startX = 0;
	private volatile double startY = 0;

	private volatile double nodeX = 0;
	private volatile double nodeY = 0;

	private volatile double nodeTop = 0;
	private volatile double nodeBottom = 0;
	private volatile double nodeLeft = 0;
	private volatile double nodeRight = 0;

	private volatile double nodeRotate = 0;

	@Override
	public void handle(MouseEvent e) {
		if (e.getSource() instanceof Block<?> source) {
			this.block = source;
			if (!e.getButton().equals(MouseButton.PRIMARY)) {
				return;
			}
			if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
				begin(e);
			} else if (e.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
				Mode currMode = getMode(e);
				if (currMode != mode) {
					mode = currMode;
					begin(e);
				} else {
					update(e);
				}
			} else if (e.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
				end(e);
			}
		}
	}

	private static Mode getMode(MouseEvent e) {
		if (e.isControlDown()) {
			return Mode.SCALE;
		} else if (e.isAltDown()) {
			return Mode.ROTATE;
		} else {
			return Mode.TRANSLATE;
		}
	}

	private void begin(MouseEvent e) {
		startX = e.getSceneX();
		startY = e.getSceneY();

		nodeX = block.getTranslateX();
		nodeY = block.getTranslateY();
		nodeTop = block.getTop();
		nodeBottom = block.getBottom();
		nodeLeft = block.getLeft();
		nodeRight = block.getRight();

		nodeRotate = block.getRotate();
	}

	private void update(MouseEvent e) {
		double deltaX = e.getSceneX() - startX;
		double deltaY = e.getSceneY() - startY;

		switch (mode) {
			case TRANSLATE -> {
				block.setTranslateX(nodeX + deltaX);
				block.setTranslateY(nodeY + deltaY);
			}
			case SCALE -> {
				block.setTop(nodeTop + deltaY);
				block.setBottom(nodeBottom + deltaY);
				block.setLeft(nodeLeft + deltaX);
				block.setRight(nodeRight + deltaX);
			}
			case ROTATE -> {
				block.setRotate(nodeRotate + deltaX);
			}
		}
	}

	private void end(MouseEvent e) {
		// Don't have to do anything here.
	}

}
