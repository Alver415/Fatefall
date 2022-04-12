package mse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;

import java.util.*;

public class MseMapper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    //TODO: Determine if we need to support whitespace in variable names. (\\s)
    private static final String VARIABLE_NAME_REGEX = "\uFEFF?[a-zA-Z_$][\\sa-zA-Z0-9_$-]*";
    private static final String NEWLINE = "\r\n";
    private static final String FIELD_DELIMITER = ":";
    private static final String FIELD_FORMAT = NEWLINE + "%s%s" + FIELD_DELIMITER;
    private static final String MULTILINE_STRING_FORMAT = NEWLINE + "%s%s";

    public ObjectNode toJson(String string) {
        return toJson(List.of(string.split(NEWLINE)));
    }

    public ObjectNode toJson(List<String> lines) {
        return (ObjectNode) readNode(lines.listIterator(), 0);
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
            int index = line.indexOf(FIELD_DELIMITER);
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
                String string = readMultilineString(iterator, depth);
                return new TextNode(string);
            }
        }
        return parent;
    }

    private String readMultilineString(ListIterator<String> iterator, int depth) {
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

    private void setNode(ObjectNode parent, String key, JsonNode node) {
        //If this is the second time the key has been used.
        if (parent.has(key)) {
            JsonNode prev = parent.get(key);
            if (prev instanceof ArrayNode array) {
                //If it's already an array, just add to it.
                array.add(node);
            } else {
                //Otherwise, start an array and start adding to that.
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
        if (node instanceof ArrayNode arrayNode) {
            arrayNode.forEach(element -> writeNode(builder, field, element, depth));
        } else {
            builder.append(FIELD_FORMAT.formatted(getDepth(depth), field));
            if (node instanceof ObjectNode) {
                writeObject(builder, node, depth + 1);
            } else {
                writeString(builder, node.textValue(), depth + 1);
            }
        }
    }

    private void writeObject(StringBuilder builder, JsonNode node, int depth) {
        for (Iterator<String> it = node.fieldNames(); it.hasNext(); ) {
            String field = it.next();
            writeNode(builder, field, node.path(field), depth);
        }
    }

    private void writeString(StringBuilder builder, String value, int depth) {
        value = value == null ? "" : value;
        if (value.contains(NEWLINE)) {
            for (String line : value.split(NEWLINE)) {
                builder.append(MULTILINE_STRING_FORMAT.formatted(getDepth(depth), line));
            }
        } else {
            builder.append(" ").append(value);
        }
    }

    /* Probably unnecessary performance optimization. Avoids repeatedly concatenating the depth string.*/
    private static final Map<Integer, String> DEPTH_STRING_MAP = new HashMap<>();

    private static String getDepth(int depth) {
        if (!DEPTH_STRING_MAP.containsKey(depth)) {
            DEPTH_STRING_MAP.put(depth, "\t".repeat(depth));
        }
        return DEPTH_STRING_MAP.get(depth);
    }
}
