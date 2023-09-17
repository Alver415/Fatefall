package com.alver.fatefall.json;

import com.alver.fatefall.property.TreeProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class TreePropertyDeserializer extends JsonDeserializer<TreeProperty> {

    @Override
    public TreeProperty deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext)
            throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        return deserialize(node);
    }

    private TreeProperty deserialize(JsonNode jsonNode) {
        TreeProperty treeProperty = new TreeProperty();
        if (jsonNode instanceof ObjectNode objectNode) {
            for (Iterator<Map.Entry<String, JsonNode>> it = objectNode.fields(); it.hasNext(); ) {
                Map.Entry<String, JsonNode> entry = it.next();
                String key = entry.getKey();
                treeProperty.getChildrenMap().put(key, deserialize(entry.getValue()));
            }
        } else if (jsonNode instanceof TextNode textNode) {
            treeProperty.setValue(textNode.textValue());
        } else if (jsonNode instanceof BooleanNode booleanNode) {
            treeProperty.setValue(booleanNode.booleanValue());
        } else if (jsonNode instanceof DoubleNode doubleNode) {
            treeProperty.setValue(doubleNode.doubleValue());
        } else if (jsonNode instanceof IntNode intNode) {
            treeProperty.setValue(intNode.intValue());
        } else if (jsonNode instanceof NullNode || jsonNode instanceof MissingNode) {
            // Do nothing
        } else {
            throw new UnsupportedOperationException(jsonNode.getNodeType().name());
        }
        return treeProperty;
    }
}
