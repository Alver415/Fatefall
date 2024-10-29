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

		StaticPropertyEditor propertyEditor = new StaticPropertyEditor("Test", new SimpleObjectProperty<>(example));

		Scene scene = new Scene(new VBox(propertyEditor));
		stage.setScene(scene);
;

		stage.centerOnScreen();
		stage.show();

	}

	public static class StaticPropertyEditor extends PropertyEditor<Example> {

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

		public StaticPropertyEditor(String name, Property<Example> exampleProperty) {
			super(name, exampleProperty);
			Example example = exampleProperty.getValue();
			ObservableList<EditorControl<?>> editors = FXCollections.observableArrayList();
			editors.add(new TextEditor("Name", example.nameProperty()));
			editors.add(new TextEditor("Description", example.descriptionProperty()));
			editors.add(new DoubleEditor("Age", example.ageProperty()));
			editors.add(new ColorSelectionEditor("Color", example.colorProperty()));
			editors.add(new SelectionEditor<>("Direction", example.directionProperty(),
					FXCollections.observableArrayList(Example.Direction.values())));

			if (example.getChild() != null) {
				editors.add(new StaticPropertyEditor("Child", example.childProperty()));
			}
			setEditors(editors);
		}
	}
}
