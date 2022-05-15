package com.alver.fatefall.api;

import com.alver.fatefall.api.models.CardImage;

import java.io.IOException;

public interface CardImageApi {

    byte[] downloadImage(Long id) throws IOException;

}
