package com.alver.fatefall.fx.app.component.mainstage;

import com.alver.fatefall.fx.app.menu.CardContextMenuFactory;
import com.alver.fatefall.fx.app.menu.WorkspaceContextMenuFactory;
import com.alver.fatefall.fx.app.view.entity.card.CardView;
import com.alver.fatefall.fx.app.view.entity.workspace.WorkspaceView;
import com.alver.fatefall.fx.core.interfaces.AppController;
import com.alver.fatefall.fx.core.interfaces.AppView;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.EntityFX;
import com.alver.fatefall.fx.core.model.WorkspaceFX;
import com.alver.fatefall.fx.core.utils.CollectionBindings;
import com.alver.springfx.annotations.FXMLComponent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;
import org.springframework.beans.factory.BeanFactory;

@FXMLComponent
public class EntityTreeView extends TreeView<EntityFX> {

	private final AppController appController;
	private final BeanFactory beanFactory;
	private final WorkspaceContextMenuFactory workspaceContextMenuFactory;
	private final CardContextMenuFactory cardContextMenuFactory;

	public EntityTreeView(
			AppController appController,
			BeanFactory beanFactory,
			ObservableList<WorkspaceFX> workspaces,
			WorkspaceContextMenuFactory workspaceContextMenuFactory,
			CardContextMenuFactory cardContextMenuFactory) {
		this.appController = appController;
		this.beanFactory = beanFactory;
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
					MenuItem open = new MenuItem("Open");
					Node view;
					if (item instanceof WorkspaceFX workspaceFX) {
						this.setContextMenu(workspaceContextMenuFactory.buildContextMenu(workspaceFX));
						WorkspaceView workspaceView = beanFactory.getBean(WorkspaceView.class);
						workspaceView.setWorkspace(workspaceFX);
						view = workspaceView;
					} else if (item instanceof CardFX cardFX) {
						this.setContextMenu(cardContextMenuFactory.buildContextMenu(cardFX));
						CardView cardView = beanFactory.getBean(CardView.class);
						cardView.setCard(cardFX);
						view = cardView;
					} else {
						throw new RuntimeException("Not implemented for:" + item.getClass());
					}
					EventHandler<ActionEvent> openAction = a -> appController.addView(AppView.of(item.nameProperty(), view));
					open.setOnAction(openAction);

					if (getContextMenu() == null) {
						setContextMenu(new ContextMenu());
					}
					getContextMenu().getItems().add(open);
					setOnMouseClicked(e -> {
						boolean doubleClick = e.getClickCount() == 2;
						boolean primary = e.getButton().equals(MouseButton.PRIMARY);
						if (primary && doubleClick) {
							appController.addView(AppView.of(item.nameProperty(), view));
						}
					});
				}
			}
		}
	}

}
