package mse;

import com.alver.fatefall.api.models.scryfall.Card;
import com.alver.fatefall.api.models.scryfall.Colors;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import mse.data.MseCard;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Converter {

    public final Card card;
    public final MseCard mseCard;

    private Converter(Card card, MseCard mseCard) {
        this.card = card;
        this.mseCard = mseCard;
    }

    public static MseCard fromCard(Card card) {
        Converter converter = new Converter(card, new MseCard());
        return converter.toMseCard();
    }

    public static Card toCard(MseCard mseCard) {
        Converter converter = new Converter(new Card(), mseCard);
        return converter.toCard();
    }

    private Card toCard(){
        return card;
    }

    private MseCard toMseCard(){
        Map<String, String> fields = mseCard.fields;
        map("has_styling", (c) -> "false");
        map("name", Card::name);
        map("casting_cost", Card::manaCost);
        map("card_color", this::mapColors);
        map("super_type", Card::typeLine);
        map("rarity", this::mapRarity);
        map("rule_text", Card::oracleText);
        map("power", Card::power);
        map("toughness", Card::toughness);
        map("image", this::prepareImage);
        return mseCard;
    }

    private void map(String field, Function<Card, String> function) {
        try {
            mseCard.fields.put(field, function.apply(card));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String mapRarity(Card card) {
        return card.rarity().name().toLowerCase();
    }

    private String mapColors(Card card) {
        return card.colors().stream()
                .map(COLORS_MAP::get)
                .collect(Collectors.joining(","));
    }

    private String prepareImage(Card card) {
        return card.name();
    }

    private static final BiMap<Colors, String> COLORS_MAP = HashBiMap.create();
    static {
        COLORS_MAP.put(Colors.W, "white");
        COLORS_MAP.put(Colors.U, "blue");
        COLORS_MAP.put(Colors.B, "black");
        COLORS_MAP.put(Colors.R, "red");
        COLORS_MAP.put(Colors.G, "green");
    }
}
