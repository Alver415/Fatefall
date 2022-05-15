package com.alver.fatefall.api.server.services;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.api.models.CardImage;
import com.alver.fatefall.api.server.repositories.CardCollectionRepository;
import com.alver.fatefall.api.server.repositories.CardImageRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import mse.SetManager;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CardCollectionService {

    @Autowired
    protected CardCollectionRepository cardCollectionRepository;

    @Autowired
    protected FileLocationService fileLocationService;

    @Autowired
    protected CardImageRepository cardImageRepository;

    public CardCollection importFromMse(String name, File file) {
        try {
            SetManager setManager = SetManager.importMseSet(name, file.toPath());
            ArrayNode cards = (ArrayNode) setManager.getSet().get("card");

            CardImage backFace = createDefaultBackFace();

            List<Card> convertedCards = new ArrayList<>();
            for (JsonNode node : cards) {
                ObjectNode data = (ObjectNode) node;
                String cardName = data.get("name").textValue();
                String fileName = cardName
                        .replace("\"", "")
                        .replace(",", "")
                        .replace(" ", "_");
                Path image = setManager.getImagesPath()
                        .resolve(fileName + ".png");

                byte[] content = Files.readAllBytes(image);
                Long id = fileLocationService.save(content, fileName);
                CardImage frontFace = cardImageRepository.getById(id);

                Card card = new Card();
                card.setData(data);
                card.setFrontFace(frontFace);
                card.setBackFace(backFace);

                convertedCards.add(card);
            }
            CardCollection collection = new CardCollection();
            collection.setName(name);
            collection.getCards().addAll(convertedCards);

            return cardCollectionRepository.save(collection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CardImage createDefaultBackFace() throws IOException {
        byte[] content = Objects.requireNonNull(
                SetManager.class.getResourceAsStream("magic_card_back.png"))
                .readAllBytes();
        Long id = fileLocationService.save(content, "magic_card_back");
        CardImage backFace = cardImageRepository.getById(id);
        return backFace;
    }

}
