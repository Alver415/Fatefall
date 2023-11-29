package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fxmlsaver.FXMLSaver;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.List;

import static com.alver.fatefall.fx.core.utils.FXUtils.isAncestorOf;

@FXMLPrototype
public class CardEditorView {

	@FXML
	private Viewport viewport;
	@FXML
	private CardView cardView;
	@FXML
	private DataTreeView dataTreeView;

	@FXML
	private TextArea fxmlEditor;
	@FXML
	private FXMLTreeView fxmlTreeView;
	@FXML
	private CachedItemsPropertySheet fxmlPropertySheet;

	public void setCard(CardFX card) {
		cardView.setCard(card);
		fxmlTreeView.setCardView(cardView);
		dataTreeView.setCardData(cardView.getCard());

		ChangeListener<Node> focusSelectedListener = (observable, oldValue, newValue) -> {
			if (isAncestorOf(newValue, cardView)) fxmlTreeView.selectNode(newValue);
		};
		viewport.sceneProperty().map(Scene::focusOwnerProperty).addListener((observable, oldValue, newValue) -> {
			if (oldValue != null) oldValue.removeListener(focusSelectedListener);
			if (newValue != null) newValue.addListener(focusSelectedListener);
		});
		viewport.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			Object target = event.getTarget();
			if (target instanceof Node node){
				if (isAncestorOf(node, cardView)) fxmlTreeView.selectNode(node);
			}
		});
		fxmlTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) fxmlPropertySheet.selectNode(newValue.getValue());
		});
	}

	@FXML
	public void createNode(ActionEvent action) {
		Dialog<Class<? extends Node>> dialog = new ChoiceDialog<>(
				StackPane.class,
				List.of(StackPane.class, TextArea.class, ImageView.class));
		dialog.show();
		dialog.resultProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				TreeItem<Node> selectedItem = fxmlTreeView.getSelectionModel().getSelectedItem();
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
		fxmlEditor.setText(serialize);
	}
	@FXML
	public void fxmlToScene(KeyEvent keyEvent) {
		if (!keyEvent.isControlDown() || !keyEvent.getCode().equals(KeyCode.ENTER)) return;

		String fxml = fxmlEditor.getText();
		cardView.getFront().controller().loadFxml(fxml);
	}
}
