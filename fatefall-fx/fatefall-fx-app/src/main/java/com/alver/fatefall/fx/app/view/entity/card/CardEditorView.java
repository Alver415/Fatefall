package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.app.editor.file.XMLEditor;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.utils.SelectionBinding;
import com.alver.fxmlsaver.FXMLSaver;
import com.alver.springfx.annotations.FXMLPrototype;
import com.sun.javafx.binding.BidirectionalBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.List;
import java.util.UUID;

@FXMLPrototype
public class CardEditorView {

	//region FXML
	@FXML
	private Viewport viewport;
	@FXML
	private CardView cardView;
	@FXML
	private DataTreeView dataTreeView;

	@FXML
	private XMLEditor fxmlEditor;
	@FXML
	private NodeTreeView nodeTreeView;
	@FXML
	private CachedItemsPropertySheet selectedNodePropertySheet;

	@FXML
	private void initialize() {
		scope.set(String.valueOf(UUID.randomUUID())); // Prevents moving tabs between different editors.

		BidirectionalBinding.bind(cardProperty(), cardView.cardProperty());
		BidirectionalBinding.bind(cardProperty(), dataTreeView.cardProperty());

		BidirectionalBinding.bind(selectedProperty(), selectedNodePropertySheet.selectedProperty());

		buildNodeTreeView();

//
//		ChangeListener<Node> focusSelectedListener = (_, _, newValue) -> run(() -> {
//			if (isAncestorOf(newValue, cardView)) runFX(() -> setSelected(newValue));
//		});
//		viewport.sceneProperty().map(Scene::focusOwnerProperty).subscribe((oldValue, newValue) -> {
//			if (oldValue != null) oldValue.removeListener(focusSelectedListener);
//			if (newValue != null) newValue.addListener(focusSelectedListener);
//		});
//		viewport.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
//			if (event.getTarget() instanceof Node node) {
//				run(() -> {
//					if (isAncestorOf(node, cardView)) setSelected(node);
//				});
//			}
//		});
	}

	private void buildNodeTreeView() {
		TreeItem<Node> frontRoot = new TreeItem<>();
		frontRoot.valueProperty().bind(
				cardView.getFront().controller().contentProperty().flatMap(Node::parentProperty));
		frontRoot.valueProperty().subscribe(value -> {
			TreeItem<Node> nodeTreeItem = nodeTreeView.buildTreeNode(value);
			frontRoot.getChildren().setAll(nodeTreeItem.getChildren());
		});
		TreeItem<Node> backRoot = new TreeItem<>();
		backRoot.valueProperty().bind(cardView.getBack().controller().contentProperty().flatMap(Node::parentProperty));
		backRoot.valueProperty().subscribe(value -> {
			TreeItem<Node> nodeTreeItem = nodeTreeView.buildTreeNode(value);
			backRoot.getChildren().setAll(nodeTreeItem.getChildren());
		});

		nodeTreeView.getRoot().getChildren().add(frontRoot);
		nodeTreeView.getRoot().getChildren().add(backRoot);


		SelectionBinding.bindBidirectional(
				nodeTreeView.selectionModelProperty()
						.flatMap(SelectionModel::selectedItemProperty)
						.flatMap(TreeItem::valueProperty),
				v -> nodeTreeView.getSelectionModel().select(nodeTreeView.nodeToItemMap.get(v)),
				selectedProperty(),
				this::setSelected);
	}

	//endregion FXML

	//region Properties

	private final StringProperty scope = new SimpleStringProperty(this, "scope");

	public StringProperty scopeProperty() {
		return scope;
	}

	public String getScope() {
		return scopeProperty().get();
	}

	public void setScope(String scope) {
		scopeProperty().set(scope);
	}

	private final ObjectProperty<Node> selected = new SimpleObjectProperty<>(this, "selected");

	public ObjectProperty<Node> selectedProperty() {
		return selected;
	}

	public Node getSelected() {
		return selectedProperty().get();
	}

	public void setSelected(Node selected) {
		selectedProperty().set(selected);
	}


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

	@FXML
	public void createNode(ActionEvent action) {
		Dialog<Class<? extends Node>> dialog = new ChoiceDialog<>(
				StackPane.class,
				List.of(StackPane.class, TextArea.class, ImageView.class));
		dialog.show();
		dialog.resultProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				TreeItem<Node> selectedItem = nodeTreeView.getSelectionModel().getSelectedItem();
				Node node = selectedItem.getValue();
				if (node instanceof Pane pane) {
					try {
						pane.getChildren().add(newValue.getConstructor().newInstance());
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		});
	}

	@FXML
	public void reset() {
		viewport.reset();
	}

	@FXML
	public void undoAction(ActionEvent action) {
		//TODO: Implement transactional undo/redo using UndoFX
	}

	@FXML
	public void redoAction(ActionEvent action) {
		//TODO: Implement transactional undo/redo using UndoFX
	}

	@FXML
	public void sceneToFxml(ActionEvent action) {
		Node root = cardView.getFront().controller().getContent();
		String serialize = FXMLSaver.serialize(root);
		fxmlEditor.getContent().replaceText(serialize);
	}

	@FXML
	public void fxmlToScene(KeyEvent keyEvent) {
		if (!keyEvent.isControlDown() || !keyEvent.getCode().equals(KeyCode.ENTER)) return;

		String fxml = fxmlEditor.getContent().getText();
		cardView.getFront().controller().loadFxml(fxml);
	}
}

