package com.alver.fatefall.fx.app.component.mainstage;

import com.alver.fatefall.fx.app.menu.CardContextMenuFactory;
import com.alver.fatefall.fx.app.menu.WorkspaceContextMenuFactory;
import com.alver.fatefall.fx.app.view.entity.card.CardEditorView;
import com.alver.fatefall.fx.app.view.entity.workspace.WorkspaceView;
import com.alver.fatefall.fx.core.interfaces.AppController;
import com.alver.fatefall.fx.core.interfaces.AppView;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.EntityFX;
import com.alver.fatefall.fx.core.model.WorkspaceFX;
import com.alver.fatefall.fx.core.utils.CollectionBindings;
import com.alver.springfx.SpringFX;
import com.alver.springfx.annotations.FXMLComponent;
import com.alver.springfx.model.FXMLControllerAndView;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import org.springframework.beans.factory.BeanFactory;

@FXMLComponent
public class EntityTreeView extends TreeView<EntityFX> {

	private final AppController appController;
	private final BeanFactory beanFactory;
	private final SpringFX springFX;
	private final WorkspaceContextMenuFactory workspaceContextMenuFactory;
	private final CardContextMenuFactory cardContextMenuFactory;

	public EntityTreeView(
			AppController appController,
			BeanFactory beanFactory,
			ObservableList<WorkspaceFX> workspaces,
			SpringFX springFX, WorkspaceContextMenuFactory workspaceContextMenuFactory,
			CardContextMenuFactory cardContextMenuFactory) {
		this.appController = appController;
		this.beanFactory = beanFactory;
		this.springFX = springFX;
		this.workspaceContextMenuFactory = workspaceContextMenuFactory;
		this.cardContextMenuFactory = cardContextMenuFactory;

		this.setCellFactory(new EntityCellFactory());
		EntityTreeItem root = new EntityTreeItem(null);
		CollectionBindings.bind(workspaces, root.getChildren(), EntityTreeItem::new);
		this.setRoot(root);
	}

	private static class EntityTreeItem extends TreeItem<EntityFX> {
		EntityTreeItem(EntityFX entity) {
			this.setValue(entity);
			if (entity instanceof WorkspaceFX workspace) {
				CollectionBindings.bind(workspace.cardsProperty(), getChildren(), EntityTreeItem::new);
			}
		}
	}

	private class EntityCellFactory implements Callback<TreeView<EntityFX>, TreeCell<EntityFX>> {
		@Override
		public TreeCell<EntityFX> call(TreeView<EntityFX> param) {
			return new EntityTreeCell<>();
		}

		private class EntityTreeCell<T extends EntityFX> extends TreeCell<T> {
			@Override
			protected void updateItem(T item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty) {
					textProperty().unbind();
					setText(null);
				} else {
					textProperty().bind(item.nameProperty());
					if (item instanceof WorkspaceFX workspaceFX) {
						this.setContextMenu(workspaceContextMenuFactory.buildContextMenu(workspaceFX));
					} else if (item instanceof CardFX cardFX) {
						this.setContextMenu(cardContextMenuFactory.buildContextMenu(cardFX));
					}
					MenuItem open = new MenuItem("Open");
					getContextMenu().getItems().add(open);
					open.setOnAction(a -> {
						if (item instanceof WorkspaceFX workspaceFX) {
							WorkspaceView workspaceView = beanFactory.getBean(WorkspaceView.class);
							workspaceView.setWorkspace(workspaceFX);
							appController.registerView(AppView.of(item.nameProperty(), workspaceView));
						} else if (item instanceof CardFX cardFX) {
							FXMLControllerAndView<CardEditorView, BorderPane> cnv =
									springFX.load(CardEditorView.class);
							CardEditorView cardEditorView = cnv.controller();
							cardEditorView.setCard(cardFX);
							appController.registerView(AppView.of(item.nameProperty(), cnv.view()));
						}
					});
					setOnMouseClicked(e -> open.fire());
				}
			}
		}
	}

}
