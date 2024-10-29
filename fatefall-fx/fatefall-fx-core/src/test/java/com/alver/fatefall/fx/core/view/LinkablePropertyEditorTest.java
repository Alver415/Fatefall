package com.alver.fatefall.fx.core.view;

import com.alver.fatefall.fx.core.view.editor.Editor;
import com.alver.fatefall.fx.core.view.editor.EditorControl;
import com.alver.fatefall.fx.core.view.editor.IntrospectingBeanEditor;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LinkablePropertyEditorTest extends Application {

	public static class Launcher {
		public static void main(String... args) {
			Application.launch(LinkablePropertyEditorTest.class);
		}
	}

	@Override
	public void start(Stage stage) throws InterruptedException {
		Example child = new Example();
		child.setName("Child");
		child.setDescription("Example");

		Example example = new Example();
		example.setName("Alex");
		example.setDescription("Software Developer");
		example.setChild(child);

		Editor<Example, EditorControl<Example>> editor = new Editor<>(example.nameProperty(),
				new IntrospectingBeanEditor<>(new SimpleObjectProperty<>(example)));

		Scene scene = new Scene(editor);
		stage.setScene(scene);
		stage.setWidth(1600);
		stage.setHeight(900);
		stage.centerOnScreen();
		stage.show();
	}

	public static class SimpleExample {
		private final StringProperty example = new SimpleStringProperty(this, "example");
		public StringProperty exampleProperty(){
		    return this.example;
		}
		public String getExample(){
		    return this.exampleProperty().get();
		}
		public void setExample(String value){
		    this.exampleProperty().set(value);
		}
	}
}
