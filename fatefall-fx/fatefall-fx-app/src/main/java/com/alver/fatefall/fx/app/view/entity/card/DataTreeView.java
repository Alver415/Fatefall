package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.Source;
import com.alver.fatefall.fx.core.utils.TreeProperty;
import com.alver.fatefall.fx.core.utils.TreePropertyBuilder;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.PseudoClass;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DataTreeView extends TreeTableView<DataTreeView.TreePropertyItem> {

	private TreeItem<TreePropertyItem> buildTreePropertyItem(String field, TreeProperty<Object> property) {
		TreeItem<TreePropertyItem> item = new TreeItem<>(new TreePropertyItem(field, property));
		for (Map.Entry<String, TreeProperty<Object>> entry : property.entrySet()) {
			String childField = entry.getKey();
			TreeProperty<Object> childProperty = entry.getValue();
			item.getChildren().add(buildTreePropertyItem(childField, childProperty));

		}
		item.setExpanded(true);
		return item;
	}
	public void setCardData(CardFX card) {
		TreeItem<TreePropertyItem> root = buildTreePropertyItem("root", new TreeProperty<>());
		setRoot(root);

		root.getChildren().add(buildTreePropertyItem("front",
				TreePropertyBuilder.buildAndBind(Map.of(
						Source.CARD, card.dataProperty(),
						Source.CARD_FACE, card.getFront().dataProperty(),
						Source.TEMPLATE, card.getFront().getTemplate().dataProperty()))));
		root.getChildren().add(buildTreePropertyItem("back",
				TreePropertyBuilder.buildAndBind(Map.of(
						Source.CARD, card.dataProperty(),
						Source.CARD_FACE, card.getBack().dataProperty(),
						Source.TEMPLATE, card.getBack().getTemplate().dataProperty()))));
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

	public static class RowFactory implements Callback<TreeTableView<DataTreeView.TreePropertyItem>, TreeTableRow<DataTreeView.TreePropertyItem>> {

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
