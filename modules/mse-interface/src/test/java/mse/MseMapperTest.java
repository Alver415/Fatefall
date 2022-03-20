package mse;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MseMapperTest {

    private static final MseMapper MAPPER = new MseMapper();

    private ObjectNode json = JsonNodeFactory.instance.objectNode();

    @Test
    public void fromJson_basic() {
        ArrayNode cards = JsonNodeFactory.instance.arrayNode();
        ObjectNode card = JsonNodeFactory.instance.objectNode();
        card.put("name", "Name of the Card");
        card.put("cost", "$5");
        cards.add(card);
        json.set("cards", cards);
        String string = MAPPER.fromJson(json);
        String expected = """
                card:
                \tname: Name of the Card
                \tcost: $5""";
        assertEquals(expected, string);
    }

    @Test
    public void toJson_real() throws IOException, URISyntaxException {
        URI uri = getClass().getResource("set").toURI();
        String exampleString = Files.readString(Path.of(uri));
        ObjectNode set = MAPPER.toJson(exampleString);
        System.out.println(set);
    }

    @Test
    public void toAndFrom() throws IOException, URISyntaxException {
        URI uri = getClass().getResource("set").toURI();
        String exampleString = Files.readString(Path.of(uri));
        ObjectNode set = MAPPER.toJson(exampleString);
        String result = MAPPER.fromJson(set);
        System.out.println(result);
    }

}
