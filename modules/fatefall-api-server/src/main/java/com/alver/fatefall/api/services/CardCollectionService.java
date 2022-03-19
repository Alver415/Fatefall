package com.alver.fatefall.api.services;

import com.alver.fatefall.api.models.scryfall.Card;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.api.models.scryfall.ImageUri;
import com.alver.fatefall.api.models.scryfall.Layouts;
import com.alver.fatefall.api.repositories.CardRepository;
import mse.SetManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CardCollectionService {

    @Autowired
    protected CardRepository cardRepository;

    public CardCollection importFromMse(String name, File file) throws IOException {
        SetManager setManager = SetManager.importMseSet(name, file.toPath());
        List<Card> convertedCards = setManager.getSet().cards.stream().map(c -> {
                    String cardName = c.fields.get("name");
                    String fileName = cardName
                            .replace("\"", "")
                            .replace(",", "")
                            .replace(" ", "_");
                    String image = setManager.getImagesPath()
                            .resolve(fileName + ".png")
                            .toFile().toURI().toString();
                    return new Card()
                            .withLayout(Layouts.NORMAL)
                            .withName(cardName)
                            .withManaCost(c.fields.get("casting_cost"))
                            .withImageUris(new ImageUri()
                                    .withNormal(image)
                                    .withPng(image)
                            );
                })
                .toList();

        CardCollection collection = new CardCollection();
        collection.setName(name);
        collection.getCards().addAll(convertedCards);
        return collection;
    }

}
