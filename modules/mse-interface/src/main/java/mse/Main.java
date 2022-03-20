package mse;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String... args) throws IOException {
        Path sourceSet = Path.of("mse_sets/champions.mse-set");

        String setName = "TestImport";
        SetManager setManager = SetManager.importMseSet(setName, sourceSet);

        ObjectNode set = setManager.getSet();
        ArrayNode cards = (ArrayNode) set.get("cards");
        ObjectNode card = new ObjectMapper().createObjectNode();
        card.put("has_styling", "false");
        card.put("name", "Palitacita, Best Pal");
        card.put("casting_cost", "GWU");
        card.put("card_color", "green, white, blue, horizontal");
        card.put("super_type", "Legendary Creature");
        card.put("sub_type", "Human Cleric");
        card.put("rarity", "mythic rare");
        card.put("rule_text", "Palitacita is the most loved.");
        card.put("power", "3");
        card.put("toughness", "3");
        card.put("notes", "Loved the most.");
        cards.add(card);

        setManager.save();
        setManager.exportSet();
        setManager.generateImages();
    }

}
