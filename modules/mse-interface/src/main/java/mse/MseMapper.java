package mse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MseMapper {

    private static final String NEWLINE = "\n";
    private static final char TAB = '\t';

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public ObjectNode toJson(String string) {
        return toJson(List.of(string.split("\n")));
    }

    public ObjectNode toJson(List<String> lines) {
        ListIterator<String> iterator = lines.listIterator();
        return readSet(iterator, 0);
    }

    private ObjectNode readSet(ListIterator<String> iterator, int depth) {
        ObjectNode set = MAPPER.createObjectNode();
        ArrayNode cards = MAPPER.createArrayNode();
        set.set("cards", cards);

        ArrayNode keywords = MAPPER.createArrayNode();
        set.set("keywords", keywords);

        while (iterator.hasNext()) {
            String line = iterator.next();
            if (!line.contains(":")) {
                //This is only true at the top level
                throw new IllegalStateException(
                        "Line must contain colon ':' deliminator to split key and value. '%s'".formatted(line));
            }
            int index = line.indexOf(":");
            String key = line.substring(0, index).trim();

            if ("card".equals(key)) {
                JsonNode card = readCard(iterator, depth);
                cards.add(card);
            } else if ("keyword".equals(key)) {
                JsonNode value = readKeyword(iterator, depth);
                keywords.add(value);
            } else if (line.length() > index + 1) {
                //Simple field. Value is on the same line as the key.
                String value = line.substring(index + 1).trim();
                set.put(key, value);
            } else {
                //Object field.
                ObjectNode value = readObject(iterator, depth);
                set.set(key, value);
            }
        }

        return set;
    }

    private ObjectNode readObject(ListIterator<String> iterator, int parentDepth) {
        ObjectNode object = MAPPER.createObjectNode();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (getDepth(line) <= parentDepth) {
                iterator.previous();
                return object;
            }
            int index = line.indexOf(":");
            String key = line.substring(0, index).trim();
            if (line.length() > index + 1) {
                //Simple field. Value is on the same line as the key.
                String value = line.substring(index + 1).trim();
                object.put(key, value);
            } else {
                //Object field. Recurse.
                ObjectNode value = readObject(iterator, parentDepth + 1);
                object.set(key, value);
            }
        }

        return object;
    }

    private ObjectNode readCard(ListIterator<String> iterator, int parentDepth) {
        ObjectNode card = MAPPER.createObjectNode();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (getDepth(line) <= parentDepth) {
                iterator.previous();
                break;
            }
            int index = line.indexOf(":");
            String key = line.substring(0, index).trim();
            if (line.length() > index + 1) {
                //Simple field. Value is on the same line as the key.
                String value = line.substring(index + 1);
                card.put(key, value.trim());
            } else {
                //Long form field. Value is split across multiple lines.
                String value = readMultiLineString(iterator, parentDepth + 1).trim();
                card.put(key, value);
            }
        }

        return card;
    }

    private ObjectNode readKeyword(ListIterator<String> iterator, int parentDepth) {
        ObjectNode keyword = MAPPER.createObjectNode();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (getDepth(line) <= parentDepth) {
                iterator.previous();
                break;
            }
            int index = line.indexOf(":");
            String key = line.substring(0, index).trim();
            if (line.length() > index + 1) {
                //Simple field. Value is on the same line as the key.
                String value = line.substring(index + 1);
                keyword.put(key, value.trim());
            } else {
                //Long form field. Value is split across multiple lines.
                String value = readMultiLineString(iterator, parentDepth + 1).trim();
                keyword.put(key, value);
            }
        }

        return keyword;
    }

    private String readMultiLineString(ListIterator<String> iterator, int parentDepth) {
        List<String> lines = new ArrayList<>();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (getDepth(line) <= parentDepth) {
                iterator.previous();
                break;
            }
            lines.add(line.trim());
        }

        return String.join(NEWLINE, lines);
    }

    private int getDepth(String string) {
        int i;
        for (i = 0; i < string.length(); i++) {
            if (string.charAt(i) != TAB) {
                return i;
            }
        }
        return i;
    }

    /*===== Serialization =====*/

    public String fromJson(JsonNode set) {
        StringBuilder builder = new StringBuilder();
        writeSet(builder, set, 0);
        return builder.toString().trim();
    }

    private void writeSet(StringBuilder builder, JsonNode set, int depth) {
        for (Iterator<String> it = set.fieldNames(); it.hasNext(); ) {
            String field = it.next();
            if (!List.of("cards", "keywords").contains(field)) {
                writeNode(builder, field, set.path(field), depth);
            }
        }
        writeCards(builder, set, depth);
        writeKeywords(builder, set, depth);
    }

    private void writeKeywords(StringBuilder builder, JsonNode set, int depth) {
        for (JsonNode keyword : set.path("keywords")) {
            writeKeyword(builder, (ObjectNode) keyword, depth);
        }
    }

    private void writeCards(StringBuilder builder, JsonNode set, int depth) {
        for (JsonNode card : set.path("cards")) {
            writeCard(builder, (ObjectNode) card, depth);
        }
    }

    private void writeChildren(StringBuilder builder, JsonNode node, int depth) {
        for (Iterator<String> it = node.fieldNames(); it.hasNext(); ) {
            String field = it.next();
            writeNode(builder, field, node.path(field), depth);
        }
    }

    private void writeNode(StringBuilder builder, String field, JsonNode node, int depth) {
        if (node instanceof ObjectNode) {
            writeObject(builder, field, (ObjectNode) node, depth);
        } else if (node instanceof ArrayNode) {
            writeArray(builder, field, (ArrayNode) node, depth);
        } else {
            writeText(builder, field, node.textValue(), depth);
        }
    }

    private void writeArray(StringBuilder builder, String field, ArrayNode array, int depth) {
        array.forEach(element -> {
            writeNode(builder, field, element, depth);
        });
    }

    private void writeObject(StringBuilder builder, String field, ObjectNode object, int depth) {
        builder.append("\n%s%s:".formatted(getKeyDepth(depth), field));
        writeChildren(builder, object, depth + 1);
    }

    private void writeCard(StringBuilder builder, ObjectNode card, int depth) {
        builder.append("\n%scard:".formatted(getKeyDepth(depth)));
        writeChildren(builder, card, depth + 1);
    }

    private void writeKeyword(StringBuilder builder, ObjectNode keyword, int depth) {
        builder.append("\n%skeyword:".formatted(getKeyDepth(depth)));
        writeChildren(builder, keyword, depth + 1);
    }

    private void writeText(StringBuilder builder, String field, String value, int depth) {
        value = value == null ? "" : value;
        if (!value.contains("\n")) {
            builder.append("\n%s%s: %s".formatted(getKeyDepth(depth), field, value));
        } else {
            builder.append("\n%s%s:".formatted(getKeyDepth(depth), field));
            for (String line : value.split("\n")) {
                builder.append("\n%s%s".formatted(getKeyDepth(depth + 1), line));
            }
        }
    }

    /* Probably unnecessary performance optimization. Avoids building the depth string for every single line. */
    private static final String _1 = "\t".repeat(1);
    private static final String _2 = "\t".repeat(2);
    private static final String _3 = "\t".repeat(3);
    private static final String _4 = "\t".repeat(4);
    private static final String _5 = "\t".repeat(5);

    private static final String getKeyDepth(int depth) {
        switch (depth) {
            case 1:
                return _1;
            case 2:
                return _2;
            case 3:
                return _3;
            case 4:
                return _4;
            case 5:
                return _5;
            default:
                return "\t".repeat(depth);

        }
    }
}
