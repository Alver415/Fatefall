package com.alver.fatefall.app.fx.component;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public interface Borders {

	Border BLACK = new Border(new BorderStroke(Color.BLACK,
			BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
	Border ORANGE = new Border(new BorderStroke(Color.ORANGE,
			BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
	Border RED = new Border(new BorderStroke(Color.RED,
			BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
	Border GREEN = new Border(new BorderStroke(Color.GREEN,
			BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
}
