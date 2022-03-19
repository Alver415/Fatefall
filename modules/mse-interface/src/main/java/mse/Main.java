package mse;

import mse.data.MseCard;
import mse.data.MseSet;

import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String... args) throws IOException {
        Path sourceSet = Path.of("mse_sets/champions.mse-set");

        String setName = "TestImport";
        SetManager setManager = SetManager.importMseSet(setName, sourceSet);

        MseSet set = setManager.getSet();
        set.cards.clear();
        MseCard card = new MseCard();
        card.fields.put("has_styling", "false");
        card.fields.put("name", "Palitacita, Best Pal");
        card.fields.put("casting_cost", "GWU");
        card.fields.put("card_color", "green, white, blue, horizontal");
        card.fields.put("super_type", "Legendary Creature");
        card.fields.put("sub_type", "Human Cleric");
        card.fields.put("rarity", "mythic rare");
        card.fields.put("rule_text", "Palitacita is the most loved.");
        card.fields.put("power", "3");
        card.fields.put("toughness", "3");
        card.fields.put("notes", "Loved the most.");
        set.cards.add(card);

        setManager.save();
        setManager.exportSet();
        setManager.generateImages();

    }

}
