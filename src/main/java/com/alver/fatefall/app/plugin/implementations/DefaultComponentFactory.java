package com.alver.fatefall.app.plugin.implementations;

import com.alver.fatefall.api.interfaces.CardCollectionView;
import com.alver.fatefall.api.interfaces.CardView;
import com.alver.fatefall.api.interfaces.ComponentFactory;
import com.alver.fatefall.app.plugin.implementations.cardcollectionview.DefaultCardCollectionView;
import com.alver.fatefall.app.plugin.implementations.cardview.DefaultCardView;
import com.alver.fatefall.app.services.CardCollectionRepository;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultComponentFactory implements ComponentFactory {

    @Autowired
    protected BeanFactory beanFactory;
    @Autowired
    protected CardCollectionRepository cardCollectionRepository;


    public CardView buildCardView() {
        DefaultCardView bean = beanFactory.getBean(DefaultCardView.class);

        bean.setOnMouseClicked(e -> {
            if (e.getButton() != MouseButton.SECONDARY){
                return;
            }
            ContextMenu contextMenu = new ContextMenu();
            cardCollectionRepository.findAll().forEach(c -> {
                MenuItem item = new MenuItem(c.getName());
                item.setOnAction(a -> c.getCards().add(bean.getCard()));
                contextMenu.getItems().add(item);
            });
        });

        return bean;
    }

    public CardCollectionView buildCardCollectionView() {
        return beanFactory.getBean(DefaultCardCollectionView.class);
    }
}
