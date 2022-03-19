package com.alver.fatefall.api.services;

import com.alver.fatefall.api.models.scryfall.Card;
import com.alver.fatefall.api.models.scryfall.Colors;
import com.alver.fatefall.api.models.scryfall.ImageUri;
import com.alver.fatefall.api.models.scryfall.Layouts;
import com.alver.fatefall.api.repositories.CardRepository;
import mse.SetManager;
import mse.data.MseCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    @Autowired
    protected CardRepository cardRepository;

    public Card generateImage(Card card) throws IOException {
        SetManager setManager = SetManager.get("champions");
        setManager.getSet().cards.clear();

        String name = card.name();
        String artCropFilename = "artcrop_" + name + ".png";
        artCropFilename = artCropFilename
                .replace("\"", "")
                .replace(",", "")
                .replace(" ", "_");

        Path artCropPath = setManager.getSetPath().resolveSibling(artCropFilename);
        Files.deleteIfExists(artCropPath);
        try(InputStream in = new URL(card.imageUris().artCrop()).openStream()){
            Files.copy(in, artCropPath);
        }
        String fileName = name
                .replace("\"", "")
                .replace(",", "")
                .replace(" ", "_");

        MseCard mseCard = new MseCard();
        mseCard.fields.put("has_styling", "false");
        mseCard.fields.put("name", name);
        mseCard.fields.put("casting_cost", card.manaCost());
        mseCard.fields.put("card_color", card.colors().stream()
                .map(this::colorToString)
                .collect(Collectors.joining(",")));
        mseCard.fields.put("super_type", card.typeLine());
        mseCard.fields.put("rarity", card.rarity().name().toLowerCase());
//        mseCard.fields.put("rule_text", card.oracleText());
        mseCard.fields.put("power", card.power());
        mseCard.fields.put("toughness", card.toughness());
        mseCard.fields.put("image", artCropFilename);
        setManager.getSet().cards.add(mseCard);
        setManager.save();
        setManager.generateImages();

        List<Card> convertedCards = setManager.getSet().cards.stream().map(c -> {
                    String image = setManager.getImagesPath()
                            .resolve(fileName + ".png")
                            .toFile().toURI().toString();
                    return new Card()
                            .withLayout(Layouts.NORMAL)
                            .withName(c.fields.get("name"))
                            .withManaCost(c.fields.get("casting_cost"))
                            .withImageUris(new ImageUri()
                                    .withNormal(image)
                                    .withPng(image)
                            );
                })
                .toList();
        return convertedCards.get(0);
    }
    private String colorToString(Colors color) {
        switch (color) {
            case W -> {
                return "white";
            }
            case U -> {
                return "blue";
            }
            case B -> {
                return "black";
            }
            case R -> {
                return "red";
            }
            case G -> {
                return "green";
            }
            default -> throw new IllegalStateException("Invalid color: " + color);
        }
    }
}
