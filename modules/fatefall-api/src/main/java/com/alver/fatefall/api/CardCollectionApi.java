package com.alver.fatefall.api;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardCollection;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public interface CardCollectionApi {

    List<CardCollection> findAll() ;

    CardCollection findById(Long id) ;
    CardCollection save(CardCollection cardCollection) ;

    CardCollection importFromMse(String name, File mseSet);
}
