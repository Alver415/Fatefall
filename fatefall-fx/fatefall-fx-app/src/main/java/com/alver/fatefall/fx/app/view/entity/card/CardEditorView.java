package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fxmlsaver.FXMLSaver;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.util.List;
import java.util.UUID;

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
	private CodeArea fxmlEditor;
	@FXML
	private SceneTreeView sceneTreeView;
	@FXML
	private CachedItemsPropertySheet selectedNodePropertySheet;

	@FXML
	private void initialize(){
		fxmlEditor.setParagraphGraphicFactory(LineNumberFactory.get(fxmlEditor));
		scope.set(String.valueOf(UUID.randomUUID())); // Prevents moving tabs between different editors.
	}

	private final StringProperty scope = new SimpleStringProperty(this, "scope");
	public StringProperty scopeProperty(){
		return scope;
	}
	public String getScope(){
		return scopeProperty().get();
	}
	public void setScope(String scope){
		scopeProperty().set(scope);
	}

	public void setCard(CardFX card) {
		cardView.setCard(card);
		sceneTreeView.setCardView(cardView);
		dataTreeView.setCardData(cardView.getCard());

		ChangeListener<Node> focusSelectedListener = (observable, oldValue, newValue) -> {
			if (isAncestorOf(newValue, cardView)) {
				sceneTreeView.selectNode(newValue);
			}
		};
		viewport.sceneProperty().map(Scene::focusOwnerProperty).addListener((observable, oldValue, newValue) -> {
			if (oldValue != null) oldValue.removeListener(focusSelectedListener);
			if (newValue != null) newValue.addListener(focusSelectedListener);
		});
		viewport.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			Object target = event.getTarget();
			if (target instanceof Node node){
				if (isAncestorOf(node, cardView)) {
					sceneTreeView.selectNode(node);
				}
			}
		});
		sceneTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null){
				selectedNodePropertySheet.selectNode(newValue.getValue());
			}
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
				TreeItem<Node> selectedItem = sceneTreeView.getSelectionModel().getSelectedItem();
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
		fxmlEditor.replaceText(serialize);
	}

	@FXML
	public void fxmlToScene(KeyEvent keyEvent) {
		if (!keyEvent.isControlDown() || !keyEvent.getCode().equals(KeyCode.ENTER)) return;

		String fxml = fxmlEditor.getText();
		cardView.getFront().controller().loadFxml(fxml);
	}
}
