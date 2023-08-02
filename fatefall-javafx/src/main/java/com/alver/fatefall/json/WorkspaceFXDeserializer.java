package com.alver.fatefall.json;

import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WorkspaceFXDeserializer extends StdDeserializer<WorkspaceFX> {

    @Autowired
    protected ObjectWriter writer;

    public WorkspaceFXDeserializer() {
        this(WorkspaceFX.class);
    }

    public WorkspaceFXDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public WorkspaceFX deserialize(JsonParser parser, DeserializationContext context)
            throws IOException {
        JsonNode json = parser.getCodec().readTree(parser);
        return buildWorkspace(json);
    }

    public WorkspaceFX buildWorkspace(JsonNode json) {
        WorkspaceFX workspace = new WorkspaceFX();
        workspace.setData(writeValueAsString(json));
        if (json.has("name")){
            workspace.setName(json.get("name").asText());
        } else {
            workspace.setName("[Card]");
        }

        return workspace;
    }

    private String writeValueAsString(JsonNode json) {
        try {
            return writer.writeValueAsString(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}