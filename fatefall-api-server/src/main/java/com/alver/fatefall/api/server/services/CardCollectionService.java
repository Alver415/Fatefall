package com.alver.fatefall.api.server.services;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.api.server.repositories.CardRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import mse.SetManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class CardCollectionService {

    @Autowired
    protected CardRepository cardRepository;

    public CardCollection importFromMse(String name, File file) {
        try {
            SetManager setManager = SetManager.importMseSet(name, file.toPath());
            ArrayNode cards = (ArrayNode) setManager.getSet().get("card");

            CardCollection collection = new CardCollection();
            collection.setName(name);
            List<Card> converted = StreamSupport.stream(cards.spliterator(), true).map(node -> {
                String cardName = node.get("name").textValue();
                String fileName = cardName
                        .replace("\"", "")
                        .replace(",", "")
                        .replace(" ", "_");
                String image = setManager.getImagesPath()
                        .resolve(fileName + ".png")
                        .toFile().toURI().toString();
                Card card = new Card();
                card.setData(node);
                card.setFrontFaceUrl(image);
                card.setBackFaceUrl(SetManager.DEFAULT_CARD_BACK_FACE);
                return card;
            }).toList();
            collection.getCards().addAll(converted);
            return collection;
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
    }

}
