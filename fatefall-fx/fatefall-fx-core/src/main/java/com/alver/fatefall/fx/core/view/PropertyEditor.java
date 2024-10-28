package com.alver.fatefall.fx.core.view;

import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PropertyEditor<T> extends EditorControl<T> {

	private static final Logger log = LoggerFactory.getLogger(PropertyEditor.class);
	private static final String CSS = Objects.requireNonNull(PropertyEditor.class.getResource("PropertyEditor.css"))
			.toExternalForm();

	private static final ObjectProperty<LinkableProperty<?>> selected = new SimpleObjectProperty<>();
	private static final ObservableValue<Boolean> isLinkStateActive = selected.map(Objects::nonNull).orElse(false);

	public PropertyEditor(String name, Property<T> property) {
		super(name, property);
		getStylesheets().add(CSS);
	}

	private final ListProperty<EditorControl<?>> editors = new SimpleListProperty<>(
			this, "editors", FXCollections.observableArrayList());

	public ListProperty<EditorControl<?>> editorsProperty() {
		return this.editors;
	}

	public ObservableList<EditorControl<?>> getEditors() {
		return this.editorsProperty().get();
	}

	public void setEditors(ObservableList<EditorControl<?>> value) {
		this.editorsProperty().set(value);
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new TopLabeledSkin();
	}

	private class TopLabeledSkin extends SkinBase<PropertyEditor<?>> {

		private Subscription subscription;

		private TopLabeledSkin() {
			super(PropertyEditor.this);
			subscription = editorsProperty().subscribe(this::rebuild);
		}

		enum State {
			UNBOUND,
			BOUND,
			BI_BOUND,
		}

		private void rebuild(List<EditorControl<?>> editors) {
			Insets insets = new Insets(2);
			VBox vbox = new VBox();
			for (EditorControl<?> editor : editors) {
				buildEditor(editor, insets, vbox);
			}

			ScrollPane scrollPane = new ScrollPane(vbox);
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);

			getChildren().setAll(scrollPane);
		}

		private static <T> void buildEditor(EditorControl<T> editor, Insets insets, VBox vbox) {
			Label name = new Label();
			name.textProperty().bind(editor.nameProperty());

			LinkableProperty<T> linkable = LinkableProperty.wrap(editor.getProperty());

			ReadOnlyObjectProperty<LinkableProperty<T>> linkedTo = linkable.linkedToProperty();
			ReadOnlyListProperty<LinkableProperty<T>> linkedBy = linkable.linkedByProperty();

			Button linkButton = new Button();
			ObjectBinding<Color> color = Bindings.createObjectBinding(() -> {
				boolean isLinking = isLinkStateActive.getValue();
				if (isLinking) {
					if (linkable == selected.get()) {
						return Color.LIGHTGRAY;
					} else {
						return Color.GREEN;
					}
				} else {
					if (linkable.isLinkedTo()) {
						return Color.BLUE;
					} else {
						return Color.LIGHTGREEN;
					}
				}
			}, linkable.isLinkedToProperty(), isLinkStateActive);
			ObservableValue<Background> background = color.map(Color::desaturate).map(Background::fill);
			linkButton.backgroundProperty().bind(background);

			editor.disableProperty().bind(linkable.isLinkedToProperty());

			BooleanBinding disabled = Bindings.createBooleanBinding(() -> linkable == selected.get(), selected);
			linkButton.disableProperty().bind(disabled);


			ObservableValue<String> linkedToText = linkedTo
					.flatMap(LinkableProperty::lastLinkedToProperty)
					.map(LinkableProperty::getName)
					.map("Linked to: %s"::formatted);

			ObservableValue<String> linkedDependenciesText = linkable.dependenciesProperty()
					.map(l -> l.stream().map(LinkableProperty::getName).collect(Collectors.joining("\n - ", "\n - ", "")))
					.map("Linked via path: %s"::formatted);
			linkedDependenciesText.subscribe(a -> System.out.println(a));

			ObservableValue<String> linkedByText = linkedBy
					.map(l -> l.stream().map(LinkableProperty::getName).collect(Collectors.joining(",")))
					.map("Linked by: [%s]"::formatted);

			StringExpression reminder = Bindings.createStringBinding(() -> Stream.of(linkedToText, linkedByText)
							.map(ObservableValue::getValue)
							.filter(Objects::nonNull)
							.collect(Collectors.joining(" | ")),
					linkedToText, linkedByText);

			Text text = new Text();
			text.textProperty().bind(reminder);

			Tooltip tooltip = new Tooltip();
			tooltip.textProperty().bind(linkedDependenciesText);
			linkButton.setTooltip(tooltip);

			linkButton.setOnAction(a -> {
				LinkableProperty source = PropertyEditor.selected.get();
				LinkableProperty target = linkable;
				Boolean isLinking = isLinkStateActive.getValue();
				if (!isLinking) {
					if (target.isLinkedTo()) {
						target.unlink();
					} else {
						selected.set(target);
					}
				} else {
					source.link(target);
					selected.set(null);
				}
			});
			DoubleBinding size = name.heightProperty().subtract(2);
			linkButton.maxWidthProperty().bind(size);
			linkButton.maxHeightProperty().bind(size);
			linkButton.minWidthProperty().bind(size);
			linkButton.minHeightProperty().bind(size);
			HBox.setMargin(linkButton, insets);

			Button biBind = new Button();
			biBind.setOnAction(a -> selected.set(null));
			biBind.maxWidthProperty().bind(size);
			biBind.maxHeightProperty().bind(size);
			biBind.minWidthProperty().bind(size);
			biBind.minHeightProperty().bind(size);
			HBox.setMargin(biBind, insets);
			HBox hBox = new HBox(text, linkButton, biBind);

			BorderPane borderPane = new BorderPane();
			borderPane.setLeft(name);
			borderPane.setRight(hBox);

			VBox control = new VBox(borderPane, editor);
			control.getStyleClass().add("editor-control");
			vbox.getChildren().add(control);
		}

		private static <T> void doBind(Property<T> second, Property<T> first) {
			first.bind(second);
		}

		@Override
		public void dispose() {
			super.dispose();
			subscription.unsubscribe();
		}
	}

	private class LeftLabeledSkin extends SkinBase<PropertyEditor<?>> {

		private LeftLabeledSkin() {
			super(PropertyEditor.this);

			Insets insets = new Insets(5, 10, 5, 10);
			VBox vbox = new VBox();
			for (EditorControl<?> editor : getEditors()) {
				Label name = new Label();
				name.textProperty().bind(editor.nameProperty());
				HBox hbox = new HBox(name, editor);
				HBox.setMargin(name, insets);
				HBox.setHgrow(name, Priority.NEVER);
				HBox.setHgrow(editor, Priority.ALWAYS);
				VBox.setMargin(hbox, insets);
				vbox.getChildren().add(hbox);

			}

			ScrollPane scrollPane = new ScrollPane(vbox);
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);

			getChildren().setAll(scrollPane);
		}

	}

	private class GridLabeledSkin extends SkinBase<PropertyEditor<?>> {

		private GridLabeledSkin() {
			super(PropertyEditor.this);

			GridPane gridPane = new GridPane(10, 10);
			int row = 0;
			for (EditorControl<?> editor : getEditors()) {
				Label label = new Label();
				label.textProperty().bind(editor.nameProperty());
				gridPane.add(label, 0, row, 1, 1);
				gridPane.add(editor, 1, row, 3, 1);
				row++;
			}

			AnchorPane anchorPane = new AnchorPane(gridPane);
			AnchorPane.setLeftAnchor(gridPane, 0d);
			AnchorPane.setRightAnchor(gridPane, 0d);
			AnchorPane.setTopAnchor(gridPane, 0d);
			AnchorPane.setBottomAnchor(gridPane, 0d);


			ScrollPane scrollPane = new ScrollPane(anchorPane);
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);

			getChildren().setAll(scrollPane);
		}

	}


}
