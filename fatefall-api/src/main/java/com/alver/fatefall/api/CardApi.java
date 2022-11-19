package com.alver.fatefall.api;

import com.alver.fatefall.api.models.Card;
import javafx.scene.image.Image;

import java.io.IOException;

public interface CardApi {

    Card findById(Long id);

    Card save(Card card);

    void delete(Long pk);

    Image getImage(String location) throws IOException;
    byte[] getImageBytes(String location) throws IOException;

    Card generateImage(Card card) throws IOException;

}
