package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.core.utils.CollectionBindings;
import com.sun.javafx.binding.BidirectionalBinding;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NodeTreeView extends TreeTableView<Node> {
	private static final Logger log = LoggerFactory.getLogger(NodeTreeView.class);

	private static final PseudoClass SELECTED = PseudoClass.getPseudoClass("selected");

	public final Map<Node, TreeItem<Node>> nodeToItemMap = new HashMap<>();

	public NodeTreeView() {
		setRoot(new TreeItem<>());
		getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super TreeItem<Node>>) change -> {
			while (change.next()) {
				change.getRemoved().forEach(removed -> removed.getValue().pseudoClassStateChanged(SELECTED, false));
				change.getAddedSubList().forEach(added -> added.getValue().pseudoClassStateChanged(SELECTED, true));
			}
		});
	}

	public void addSubRoot(Node node) {
		TreeItem<Node> root = getRoot();
		root.getChildren().add(buildTreeNode(node));
	}

	public TreeItem<Node> buildTreeNode(Node node) {
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

	public static class CellValueFactory implements Callback<TreeTableColumn.CellDataFeatures<Node, Node>,
			ObservableValue<Node>> {
		@Override
		public ObservableValue<Node> call(TreeTableColumn.CellDataFeatures<Node, Node> param) {
			return param.getValue().valueProperty();
		}
	}

	public static class CellFactory implements Callback<TreeTableColumn<Node, Node>, TreeTableCell<Node, Node>> {
		@Override
		public TreeTableCell<Node, Node> call(TreeTableColumn<Node, Node> column) {
			return new TreeTableCell<>() {
				{
					setOnContextMenuRequested(event -> setContextMenu(buildContextMenu(getItem())));
				}

				@Override
				public void updateItem(Node item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setGraphic(null);
						textProperty().unbind();
						textProperty().set(null);
					} else {
						CheckBox checkBox = new CheckBox();
						checkBox.disableProperty().bind(
								Bindings.or(
										item.disabledProperty(),
										Bindings.createBooleanBinding(
												() -> item.visibleProperty().isBound(),
												item.visibleProperty())));
						BidirectionalBinding.bind(checkBox.selectedProperty(), item.visibleProperty());
						setGraphic(checkBox);
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

				private ContextMenu buildContextMenu(Node node) {
					ContextMenu contextMenu = new ContextMenu();
					if (node != null) {
						buildAddItem(node, contextMenu);
						buildRemoveItem(node, contextMenu);
						buildToFrontItem(node, contextMenu);
						buildToBackItem(node, contextMenu);
					}
					return contextMenu;
				}

				private void buildToFrontItem(Node node, ContextMenu contextMenu) {
					MenuItem toFront = new MenuItem("To Front");
					toFront.setOnAction(a -> node.toFront());
					ObservableList<Node> siblings = node.getParent().getChildrenUnmodifiable();
					boolean onlyChild = siblings.size() <= 1;
					int index = siblings.indexOf(node);
					boolean isFront = index == siblings.size() - 1;
					toFront.setDisable(onlyChild || isFront);
					contextMenu.getItems().add(toFront);
				}

				private void buildToBackItem(Node node, ContextMenu contextMenu) {
					MenuItem toBack = new MenuItem("To Back");
					toBack.setOnAction(a -> node.toBack());
					ObservableList<Node> siblings = node.getParent().getChildrenUnmodifiable();
					boolean onlyChild = siblings.size() <= 1;
					int index = siblings.indexOf(node);
					boolean isBack = index == 0;
					toBack.setDisable(onlyChild || isBack);
					contextMenu.getItems().add(toBack);
				}

				private void buildRemoveItem(Node node, ContextMenu contextMenu) {
					MenuItem remove = new MenuItem("Remove");
					Optional<ObservableList<Node>> children = getChildrenIfPublic(node.getParent());
					if (children.isEmpty()) {
						remove.setDisable(true);
					} else {
						remove.setOnAction(a -> children.get().remove(node));
					}
					contextMenu.getItems().add(remove);
				}

				private void buildAddItem(Node node, ContextMenu contextMenu) {
					Menu add = new Menu("Add Child");
					Optional<ObservableList<Node>> children = getChildrenIfPublic(node);
					if (children.isEmpty()) {
						add.setDisable(true);
					} else {
						for (Class<? extends Node> clazz : SelectNodeClassDialog.getNodeClasses()) {
							MenuItem item = new MenuItem(clazz.getSimpleName());
							item.setOnAction(a -> {
								try {
									Node instance = clazz.getConstructor().newInstance();
									children.get().add(instance);
								} catch (InstantiationException |
										 IllegalAccessException |
										 InvocationTargetException |
										 NoSuchMethodException e) {
									throw new RuntimeException(e);
								}
							});
							add.getItems().add(item);
						}
//						add.setOnAction(a -> {
//							SelectNodeClassDialog dialog = new SelectNodeClassDialog();
//							dialog.show();
//							dialog.resultProperty().addListener((observable, oldValue, newValue) -> {
//								try {
//									Node instance = newValue.getConstructor().newInstance();
//									children.get().add(instance);
//								} catch (InstantiationException |
//										 IllegalAccessException |
//										 InvocationTargetException |
//										 NoSuchMethodException e) {
//									throw new RuntimeException(e);
//								}
//							});
//						});
					}
					contextMenu.getItems().add(add);
				}

				private Optional<ObservableList<Node>> getChildrenIfPublic(Node node) {
					try {
						Method getChildren = node.getClass().getMethod("getChildren");
						if (Modifier.isPublic(getChildren.getModifiers())) {
							//noinspection unchecked
							return Optional.of((ObservableList<Node>) getChildren.invoke(node));
						}
					} catch (NoSuchMethodException |
							 InvocationTargetException |
							 IllegalAccessException e) {
						return Optional.empty();
					}
					return Optional.empty();
				}
			};
		}

	}
}
