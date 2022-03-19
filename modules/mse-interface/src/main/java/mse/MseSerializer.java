package mse;

import mse.data.MseCard;
import mse.data.MseKeyword;
import mse.data.MseNode;
import mse.data.MseSet;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MseSerializer {

    public String writeSet(MseSet set) {
        Stream<String> fields = set.fields.entrySet().stream()
                .map(e -> writeField(0, e.getKey(), e.getValue()));

        Stream<String> cards = set.cards.stream()
                .map(this::writeCard);

        Stream<String> keywords = set.keywords.stream()
                .map(this::writeKeyword);

        return Stream.of(fields, cards, keywords)
                .flatMap(i -> i)
                .collect(Collectors.joining("\n"));

    }

    private String writeCard(MseCard card) {
        return "card:\n" + writeFields(1, card.fields);
    }

    private String writeKeyword(MseKeyword keyword) {
        return "keyword:\n" + writeFields(1, keyword.fields);
    }

    private String writeFields(int depth, Map<String, String> fields) {
        return fields.entrySet().stream()
                .map(e -> writeField(depth, e.getKey(), e.getValue()))
                .collect(Collectors.joining("\n"));
    }

    private String writeField(int depth, String key, Object value) {
        String keyDepth = "\t".repeat(Math.max(0, depth));
        if (value instanceof MseNode node) {
            return "%s%s:\n%s".formatted(keyDepth, key,
                    writeFields(depth + 1, node.fields));
        } else if (value instanceof String && ((String) value).contains("\n")) {
            //BigString field takes up multiple lines.
            String valueDepth = "\t".repeat(Math.max(0, depth + 1));
            return "%s%s:\n%s%s".formatted(keyDepth, key, valueDepth, value);
        } else {
            return "%s%s: %s".formatted(keyDepth, key, value);
        }
    }

}
