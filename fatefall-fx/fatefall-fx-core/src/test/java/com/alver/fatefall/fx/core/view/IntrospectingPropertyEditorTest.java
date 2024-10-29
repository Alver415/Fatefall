package com.alver.fatefall.fx.core.view;

import com.alver.fatefall.fx.core.view.editor.Editor;
import com.alver.fatefall.fx.core.view.editor.EditorControl;
import com.alver.fatefall.fx.core.view.editor.IntrospectingBeanEditor;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IntrospectingPropertyEditorTest extends Application {

	public static class Launcher {
		public static void main(String... args) {
			Application.launch(IntrospectingPropertyEditorTest.class);
		}
	}

	@Override
	public void start(Stage stage) {
		Example child = new Example();
		child.setName("Child");
		child.setDescription("Example");

		Example example = new Example();
		example.setName("Alex");
		example.setDescription("Software Developer");
		example.setChild(child);

		child.descriptionProperty().bindBidirectional(example.descriptionProperty());

		Editor<Example, EditorControl<Example>> beanEditor = new Editor<>("Example", new IntrospectingBeanEditor<>(new SimpleObjectProperty<>(example)));

		Scene scene = new Scene(new VBox(beanEditor));
		stage.setScene(scene);

		stage.centerOnScreen();
		stage.show();
	}
}
