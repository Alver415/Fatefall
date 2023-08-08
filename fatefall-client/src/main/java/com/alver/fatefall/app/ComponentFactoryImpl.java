package com.alver.fatefall.app;

import com.alver.fatefall.app.fx.editor.block.TextBlock;
import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import com.alver.fatefall.app.fx.view.entity.card.CardView;
import com.alver.fatefall.app.fx.view.entity.card.CardViewImpl;
import com.alver.fatefall.app.fx.view.entity.workspace.WorkspaceView;
import com.alver.fatefall.app.fx.view.entity.workspace.WorkspaceViewImpl;
import com.alver.fxmlsaver.FXMLSaver;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ComponentFactoryImpl implements ComponentFactory {

	@Autowired
	protected ObservableList<WorkspaceFX> workspaceList;
	@Autowired
	protected BeanFactory beanFactory;

	public MenuItem buildAddToCollectionMenuItem(CardView<?> cardView) {
		Menu menu = new Menu("Add to...");
		workspaceList.forEach(c -> {
			MenuItem item = new MenuItem(c.getName());
			item.setOnAction(a -> c.addCards(cardView.getCard()));
			menu.getItems().add(item);
		});
		return menu;
	}

	public MenuItem buildDeleteCardMenuItem(CardView<?> cardView) {
		MenuItem item = new MenuItem("Delete");
		item.setOnAction(a -> workspaceList
				.forEach(w -> w.removeCards(cardView.getCard())));
		return item;
	}

	public MenuItem buildAddBlockMenuItem(CardView<?> cardView) {
		Menu menu = new Menu("Edit...");
		MenuItem item = new MenuItem("Create Text Block");
		item.setOnAction(a -> {
			if (cardView.getFxViewNode() instanceof CardViewImpl impl) {
				impl.getFront().getChildren().add(new TextBlock("Text Block"));
			}
		});
		menu.getItems().add(item);

		MenuItem confirm = new MenuItem("Confirm Changes");
		confirm.setOnAction(a -> {
			if (cardView.getFxViewNode() instanceof CardViewImpl impl) {
				if (!impl.getFront().getChildren().isEmpty()) {
					cardView.getCard().getFront().setFxmlTemplate(FXMLSaver.serialize(impl.getFront().getChildren().get(0)));
				}
				if (!impl.getBack().getChildren().isEmpty()) {
					cardView.getCard().getBack().setFxmlTemplate(FXMLSaver.serialize(impl.getBack().getChildren().get(0)));
				}
			}

		});
		menu.getItems().add(confirm);

		MenuItem clear = new MenuItem("Clear Changes");
		clear.setOnAction(a -> {
			if (cardView.getFxViewNode() instanceof CardViewImpl impl) {
				impl.getFront().getChildren().clear();
				impl.getBack().getChildren().clear();
				cardView.getCard().getFront().setFxmlTemplate(null);
				cardView.getCard().getBack().setFxmlTemplate(null);
			}
		});
		menu.getItems().add(clear);
		return menu;
	}

	@Prototype
	public List<MenuItem> buildCardViewContextMenuItems(CardView<?> cardView) {
		return List.of(
				buildAddToCollectionMenuItem(cardView),
				buildDeleteCardMenuItem(cardView),
				buildAddBlockMenuItem(cardView));
	}

	public WorkspaceView<?> buildWorkspaceView() {
		return beanFactory.getBean(WorkspaceViewImpl.class);
	}

}
