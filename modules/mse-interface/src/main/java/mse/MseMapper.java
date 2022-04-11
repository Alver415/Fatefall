package mse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MseMapper {

    //TODO: Determine if we need to support whitespace in variable names. (\\s)
    private static final String VARIABLE_NAME_REGEX = "\uFEFF?[a-zA-Z_$-][\\sa-zA-Z0-9_$-]*";
    private static final String NEWLINE = System.lineSeparator();
    private static final String FIELD_STRING_FORMAT = NEWLINE + "%s%s:";
    private static final String LONG_TEXT_FORMAT = NEWLINE + "%s%s";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public ObjectNode toJson(String string) {
        return toJson(List.of(string.split(NEWLINE)));
    }

    public ObjectNode toJson(List<String> lines) {
        ListIterator<String> iterator = lines.listIterator();
        return (ObjectNode) readNode(iterator, 0);
    }

    private JsonNode readNode(ListIterator<String> iterator, int depth) {
        ObjectNode parent = MAPPER.createObjectNode();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (getDepth(line) < depth) {
                //Gone too far, step back and return the complete object.
                iterator.previous();
                break;
            }
            int index = line.indexOf(":");
            if (index > 0 && line.substring(0, index).trim().matches(VARIABLE_NAME_REGEX)) {
                //Valid variable name means it's a field.
                String key = line.substring(0, index).trim();
                if (nextIsDeeper(iterator, depth)) {
                    //Either an object or a long form text. Recurse
                    JsonNode node = readNode(iterator, depth + 1);
                    setNode(parent, key, node);
                } else {
                    //Simple field. Value is null or on the same line as the key.
                    JsonNode node = line.length() > index ?
                            new TextNode(line.substring(index + 1).trim()) :
                            MAPPER.nullNode();
                    setNode(parent, key, node);
                }
            } else {
                //Long form string.
                return new TextNode(readMultiLineString(iterator, depth));
            }
        }
        return parent;
    }

    private void setNode(ObjectNode parent, String key, JsonNode node) {
        //If this is the second time the key has been used.
        if (parent.has(key)) {
            JsonNode prev = parent.get(key);
            if (prev instanceof ArrayNode array) {
                //If it's already an array, add to it.
                array.add(node);
            } else {
                //Otherwise, start an array and add to that.
                ArrayNode array = MAPPER.createArrayNode();
                parent.set(key, array);
                array.add(prev);
                array.add(node);
            }
        } else {
            parent.set(key, node);
        }
    }

    private boolean nextIsDeeper(ListIterator<String> iterator, int depth) {
        if (iterator.hasNext()) {
            String next = iterator.next();
            iterator.previous();
            return getDepth(next) > depth;
        }
        return false;
    }

    private String readMultiLineString(ListIterator<String> iterator, int depth) {
        List<String> lines = new ArrayList<>();
        iterator.previous();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (getDepth(line) < depth) {
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
            if (string.charAt(i) != '\t') {
                return i;
            }
        }
        return i;
    }

    /*===== Serialization =====*/

    public String fromJson(JsonNode set) {
        StringBuilder builder = new StringBuilder();
        writeObject(builder, set, 0);
        return builder.toString().trim();
    }

    private void writeNode(StringBuilder builder, String field, JsonNode node, int depth) {

        if (node instanceof ObjectNode) {
            builder.append(FIELD_STRING_FORMAT.formatted(getKeyDepth(depth++), field));
            writeObject(builder, node, depth);
        } else if (node instanceof ArrayNode) {
            writeArray(builder, field, (ArrayNode) node, depth);
        } else {
            builder.append(FIELD_STRING_FORMAT.formatted(getKeyDepth(depth++), field));
            writeText(builder, node.textValue(), depth);
        }
    }


    private void writeObject(StringBuilder builder, JsonNode node, int depth) {
        for (Iterator<String> it = node.fieldNames(); it.hasNext(); ) {
            String field = it.next();
            writeNode(builder, field, node.path(field), depth);
        }
    }

    private void writeArray(StringBuilder builder, String field, ArrayNode array, int depth) {
        array.forEach(element -> writeNode(builder, field, element, depth));
    }

    private void writeText(StringBuilder builder, String value, int depth) {
        value = value == null ? "" : value;
        if (!value.contains(NEWLINE)) {
            builder.append(" ").append(value);
        } else {
            for (String line : value.split(NEWLINE)) {
                builder.append(LONG_TEXT_FORMAT.formatted(getKeyDepth(depth), line));
            }
        }
    }

    /* Probably unnecessary performance optimization. Avoids building the depth string for every single line. */
    private static final String _1 = "\t".repeat(1);
    private static final String _2 = "\t".repeat(2);
    private static final String _3 = "\t".repeat(3);
    private static final String _4 = "\t".repeat(4);
    private static final String _5 = "\t".repeat(5);

    private static String getKeyDepth(int depth) {
        return switch (depth) {
            case 1 -> _1;
            case 2 -> _2;
            case 3 -> _3;
            case 4 -> _4;
            case 5 -> _5;
            default -> "\t".repeat(depth);
        };
    }
}
