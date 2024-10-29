package com.alver.fatefall.fx.core.view;

import com.alver.fatefall.fx.core.view.editor.*;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StaticPropertyEditorTest extends Application {

	public static class Launcher {
		public static void main(String... args) {
			Application.launch(StaticPropertyEditorTest.class);
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

		StaticBeanEditor propertyEditor = new StaticBeanEditor(new SimpleObjectProperty<>(example));

		Scene scene = new Scene(new VBox(propertyEditor));
		stage.setScene(scene);
;

		stage.centerOnScreen();
		stage.show();

	}

	public static class StaticBeanEditor extends BeanEditor<Example> {

		private final ObjectProperty<Example> example = new SimpleObjectProperty<>(this, "example");

		public ObjectProperty<Example> exampleProperty() {
			return this.example;
		}

		public Example getExample() {
			return this.exampleProperty().get();
		}

		public void setExample(Example value) {
			this.exampleProperty().set(value);
		}

		public StaticBeanEditor(Property<Example> exampleProperty) {
			super(exampleProperty);
			Example example = exampleProperty.getValue();
			ObservableList<Editor<?,?>> editors = FXCollections.observableArrayList();
			editors.add(new Editor<>("Name", new TextEditor(example.nameProperty())));

			if (example.getChild() != null) {
				editors.add(new Editor<>("Child", new StaticBeanEditor(example.childProperty())));
			}
			setEditors(editors);
		}
	}
}
