package com.alver.fatefall.app.plugin.implementations;

import com.alver.fatefall.app.Prototype;
import com.alver.fatefall.app.editor.components.WorkspaceView;
import com.alver.fatefall.app.plugin.implementations.cardcollectionview.WorkspaceViewImpl;
import com.alver.fatefall.app.services.ComponentFactory;
import com.alver.fatefall.data.entity.Card;
import com.alver.fatefall.data.entity.Workspace;
import com.alver.fatefall.data.repository.CardRepository;
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
    protected ObservableList<Workspace> workspaceList;
    @Autowired
    protected CardRepository cardRepository;
    @Autowired
    protected BeanFactory beanFactory;

    public MenuItem buildAddToCollectionMenuItem(Card card) {
        Menu menu = new Menu("Add to...");
        workspaceList.forEach(c -> {
            MenuItem item = new MenuItem(c.getName());
            item.setOnAction(a -> c.addCards(card));
            menu.getItems().add(item);
        });
        return menu;
    }

    public MenuItem buildDeleteCardMenuItem(Card card) {
        MenuItem item = new MenuItem("Delete");
        item.setOnAction(a -> workspaceList
                .forEach(w -> w.removeCards(card)));
        return item;
    }

    @Prototype
    public List<MenuItem> buildCardViewContextMenuItems(Card card) {
        return List.of(
                buildAddToCollectionMenuItem(card),
                buildDeleteCardMenuItem(card));
    }

    public WorkspaceView<?> buildWorkspaceView() {
        return beanFactory.getBean(WorkspaceViewImpl.class);
    }

}
