package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.core.utils.CollectionBindings;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;

public class FXMLTreeView extends TreeTableView<Node> {

	private static final PseudoClass SELECTED = PseudoClass.getPseudoClass("selected");

	private final Map<Node, TreeItem<Node>> nodeToItemMap = new HashMap<>();
	public FXMLTreeView() {
		getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super TreeItem<Node>>) change -> {
			while (change.next()) {
				change.getRemoved().forEach(removed -> removed.getValue().pseudoClassStateChanged(SELECTED, false));
				change.getAddedSubList().forEach(added -> added.getValue().pseudoClassStateChanged(SELECTED, true));
			}
		});
	}

	public void setCardView(CardView cardView) {
		TreeItem<Node> root = new TreeItem<>();
		root.getChildren().add(buildTreeNode(cardView.getFront().controller().getRoot()));
		root.getChildren().add(buildTreeNode(cardView.getBack().controller().getRoot()));
		setRoot(root);
	}

	private TreeItem<Node> buildTreeNode(Node node) {
		TreeItem<Node> item = new TreeItem<>(node);
		nodeToItemMap.put(node, item);
		if (node instanceof Parent parent) {
			CollectionBindings.bind(parent.getChildrenUnmodifiable(), item.getChildren(), this::buildTreeNode);
		}
		item.setExpanded(true);
		return item;
	}

	public void selectNode(Node node) {
		getSelectionModel().clearSelection();
		getSelectionModel().select(nodeToItemMap.get(node));
		scrollTo(getSelectionModel().getSelectedIndex());
	}

	public static class CellValueFactory implements Callback<TreeTableColumn.CellDataFeatures<Node, Node>, ObservableValue<Node>> {
		@Override
		public ObservableValue<Node> call(TreeTableColumn.CellDataFeatures<Node, Node> param) {
			return param.getValue().valueProperty();
		}
	}

	public static class CellFactory implements Callback<TreeTableColumn<Node, Node>, TreeTableCell<Node, Node>> {
		@Override
		public TreeTableCell<Node, Node> call(TreeTableColumn<Node, Node> column) {
			TreeTableCell<Node, Node> cell = new TreeTableCell<>() {
				@Override
				public void updateItem(Node item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setGraphic(null);
						textProperty().unbind();
						textProperty().set(null);
					} else {
						Class<? extends Node> clazz = item.getClass();
						Tooltip.install(this, new Tooltip(clazz.getTypeName()));
						textProperty().bind(Bindings.createStringBinding(() -> {
							String text = !clazz.getSimpleName().isEmpty() ?
									clazz.getSimpleName() :
									clazz.getTypeName();
							text += item.getId() == null ? "" :
									" [" + item.getId() + "]";
							return text;
						}));
					}
				}
			};
			ContextMenu contextMenu = new ContextMenu();
			MenuItem delete = new MenuItem("Delete");
			delete.setOnAction(a -> {
				if (cell.getItem().getParent() instanceof Pane pane)
					pane.getChildren().remove(cell.getItem());
			});

			contextMenu.getItems().setAll(delete);
			cell.setContextMenu(contextMenu);
			return cell;
		}
	}
}
