package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.Source;
import com.alver.fatefall.fx.core.utils.TreeProperty;
import com.alver.fatefall.fx.core.utils.TreePropertyBuilder;
import javafx.beans.property.*;
import javafx.css.PseudoClass;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.alver.jfxtra.util.JFXUtils.run;
import static com.alver.jfxtra.util.JFXUtils.runFX;

public class DataTreeView extends TreeTableView<DataTreeView.TreePropertyItem> {

	//region Properties
	private final ObjectProperty<CardFX> card = new SimpleObjectProperty<>(this, "card");

	public ObjectProperty<CardFX> cardProperty() {
		return card;
	}

	public CardFX getCard() {
		return cardProperty().get();
	}

	public void setCard(CardFX card) {
		cardProperty().set(card);
	}
	//endregion

	public DataTreeView() {
		setRowFactory(new RowFactory());
		setRoot(buildTreePropertyItem("root", new TreeProperty<>()));
		cardProperty().subscribe(this::rebuild);
	}

	public void expandAll() {
		recursive(getRoot(), item -> item.setExpanded(true));
	}

	public void collapseAll() {
		recursive(getRoot(), item -> item.setExpanded(false));
	}

	private static <T> void recursive(TreeItem<T> item, Consumer<TreeItem<T>> consumer) {
		consumer.accept(item);
		item.getChildren().forEach(child -> recursive(child, consumer));
	}

	/**
	 * !!!IMPORTANT!!!
	 * Need class level references to these properties, otherwise the WeakReferences used by javafx bindings
	 * will allow them to be garbage collected and break the binding randomly.
	 */
	private TreeProperty<Object> frontTreeProperty;
	private TreeProperty<Object> backTreeProperty;

	private void rebuild(CardFX cardFX) {
		if (cardFX == null) {
			runFX(() -> getRoot().getChildren().clear());
			return;
		}
		run(() -> {
			frontTreeProperty = TreePropertyBuilder.buildAndBind(Map.of(
					Source.CARD, cardFX.dataProperty(),
					Source.CARD_FACE, cardFX.getFront().dataProperty(),
					Source.TEMPLATE, cardFX.getFront().getTemplate().dataProperty()));
			TreeItem<TreePropertyItem> frontTreeItem = buildTreePropertyItem("front", frontTreeProperty);

			backTreeProperty = TreePropertyBuilder.buildAndBind(Map.of(
					Source.CARD, cardFX.dataProperty(),
					Source.CARD_FACE, cardFX.getBack().dataProperty(),
					Source.TEMPLATE, cardFX.getBack().getTemplate().dataProperty()));
			TreeItem<TreePropertyItem> backTreeItem = buildTreePropertyItem("back", backTreeProperty);
			runFX(() -> {
				TreeItem<TreePropertyItem> root = getRoot();
				root.getChildren().clear();
				root.getChildren().add(frontTreeItem);
				root.getChildren().add(backTreeItem);
				expandAll();
			});
		});
	}

	private TreeItem<TreePropertyItem> buildTreePropertyItem(String field, TreeProperty<Object> property) {
		TreeItem<TreePropertyItem> item = new TreeItem<>(new TreePropertyItem(field, property));
		for (Map.Entry<String, TreeProperty<Object>> entry : property.entrySet()) {
			String childField = entry.getKey();
			TreeProperty<Object> childProperty = entry.getValue();
			item.getChildren().add(buildTreePropertyItem(childField, childProperty));
		}
		return item;
	}

	public static final class TreePropertyItem {
		private final StringProperty nameProperty;
		private final TreeProperty<Object> valueProperty;

		private TreePropertyItem(String name, TreeProperty<Object> valueProperty) {
			this.nameProperty = new SimpleStringProperty(name);
			this.valueProperty = valueProperty;
		}

		public StringProperty nameProperty() {
			return nameProperty;
		}

		public void setName(String name) {
			nameProperty().set(name);
		}

		public String getName() {
			return nameProperty().get();
		}

		public TreeProperty<Object> valueProperty() {
			return valueProperty;
		}

		public void setValue(Object value) {
			valueProperty().set(value);
		}

		public Object getValue() {
			return valueProperty().get();
		}

		public ReadOnlyObjectProperty<Source> sourceProperty() {
			return valueProperty.sourceProperty();
		}

		public void setSource(Object value) {
			throw new UnsupportedOperationException("Cannot set source");
		}

		public Source getSource() {
			return sourceProperty().get();
		}
	}

	public static class RowFactory implements Callback<TreeTableView<DataTreeView.TreePropertyItem>,
			TreeTableRow<DataTreeView.TreePropertyItem>> {

		private static final Map<Source, PseudoClass> PSEUDO_CLASSES = Arrays.stream(Source.values())
				.collect(Collectors.toMap(k -> k, v -> PseudoClass.getPseudoClass(v.name().toLowerCase())));

		@Override
		public TreeTableRow<DataTreeView.TreePropertyItem> call(TreeTableView<DataTreeView.TreePropertyItem> param) {
			return new TreeTableRow<>() {
				@Override
				public void updateItem(DataTreeView.TreePropertyItem item, boolean empty) {
					super.updateItem(item, empty);
					PSEUDO_CLASSES.values().forEach(pseudoClass -> pseudoClassStateChanged(pseudoClass, false));
					if (item != null && item.getSource() != null) {
						PseudoClass pseudoClass = PSEUDO_CLASSES.get(item.getSource());
						pseudoClassStateChanged(pseudoClass, true);
						Tooltip.install(this, new Tooltip(pseudoClass.getPseudoClassName()));
					}
				}
			};
		}
	}
}