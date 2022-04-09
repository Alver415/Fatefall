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
import java.util.List;

@Service
public class CardCollectionService {

    @Autowired
    protected CardRepository cardRepository;

    public CardCollection importFromMse(String name, File file) {
        try {
            SetManager setManager = SetManager.importMseSet(name, file.toPath());
            ArrayNode cards = (ArrayNode) setManager.getSet().get("cards");

            List<Card> convertedCards = new ArrayList<>();
            for (JsonNode node : cards) {
                ObjectNode data = (ObjectNode) node;
                String cardName = data.get("name").textValue();
                String fileName = cardName
                        .replace("\"", "")
                        .replace(",", "")
                        .replace(" ", "_");
                String image = setManager.getImagesPath()
                        .resolve(fileName + ".png")
                        .toFile().toURI().toString();
                Card card = new Card();
                card.setData(data);
                card.setFrontFaceUrl(image);
                card.setBackFaceUrl(SetManager.DEFAULT_CARD_BACK_FACE);
                convertedCards.add(card);
            }

            CardCollection collection = new CardCollection();
            collection.setName(name);
            collection.getCards().addAll(convertedCards);
            return collection;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
