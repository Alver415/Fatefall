package mse;

import com.alver.fatefall.api.models.scryfall.Card;
import mse.data.MseCard;
import mse.data.MseSet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MseMapper {

    private final MseSerializer serializer;
    private final MseDeserializer deserializer;

    public MseMapper() {
        this(new MseSerializer(), new MseDeserializer());

    }
    public MseMapper(MseSerializer serializer, MseDeserializer deserializer) {
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    public String toString(MseSet set) {
        return serializer.writeSet(set);
    }
    public MseSet fromLines(List<String> lines) {
        return deserializer.readSet(lines);
    }
    public MseCard fromCard(Card card){
        return Converter.fromCard(card);
    }
    public Card toCard(MseCard mseCard){
        return Converter.toCard(mseCard);
    }
}
