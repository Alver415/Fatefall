package com.alver.fatefall.api.server.services;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.server.repositories.CardRepository;
import com.fasterxml.jackson.databind.node.ArrayNode;
import mse.SetManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CardService {

    @Autowired
    protected CardRepository cardRepository;

    public Card generateImage(Card card) throws IOException {
        SetManager setManager = SetManager.get("champions");
        ArrayNode cards = (ArrayNode) setManager.getSet().get("cards");
        cards.removeAll();

        String name = card.getData().path("name").textValue();
        String imageUrl = card.getData().path("image_uris").path("art_crop").textValue();
        if (imageUrl != null) {
            String artCropFilename = "artcrop_" + name + ".png";
            artCropFilename = toValidFileName(artCropFilename);

            Path artCropPath = setManager.getSetPath().resolveSibling(artCropFilename);
            Files.deleteIfExists(artCropPath);
            try (InputStream in = new URL(imageUrl).openStream()) {
                Files.copy(in, artCropPath);
            }
        }
        String fileName = toValidFileName(name);

        cards.add(card.getData());
        setManager.save();
        setManager.generateImages();

        String image = setManager.getImagesPath()
                .resolve(fileName + ".png")
                .toFile().toURI().toString();

        Card result = new Card();
        result.setData(card.getData());
        result.setFrontFaceUrl(image);
        result.setBackFaceUrl(image);
        return result;
    }
    private String toValidFileName(String string) {
        return string
                .replace("\"", "")
                .replace(",", "")
                .replace(" ", "_");
    }
}
