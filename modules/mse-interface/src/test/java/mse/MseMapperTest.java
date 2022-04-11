package mse;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MseMapperTest {

    private static final MseMapper MAPPER = new MseMapper();
    private static final String NEWLINE = System.lineSeparator();

    private ObjectNode json = JsonNodeFactory.instance.objectNode();

    @Test
    public void fromJson_basic() {
        ArrayNode cards = JsonNodeFactory.instance.arrayNode();
        ObjectNode card = JsonNodeFactory.instance.objectNode();
        card.put("name", "Name of the Card");
        card.put("cost", "$5");
        card.put("long_string", "This is a long string." + NEWLINE + "It takes up more than one line.");
        card.put("short_string", "This is a single length string.");
        cards.add(card);
        json.set("card", cards);
        String string = MAPPER.fromJson(json);
        String expected = """
                card:\r
                \tname: Name of the Card\r
                \tcost: $5\r
                \tlong_string:\r
                \t\tThis is a long string.\r
                \t\tIt takes up more than one line.\r
                \tshort_string: This is a single length string.""";
        assertEquals(expected, string);
    }

    @Test
    public void toJson_real() throws IOException, URISyntaxException {
        URI uri = getClass().getResource("set").toURI();
        String exampleString = Files.readString(Path.of(uri));
        ObjectNode set = MAPPER.toJson(exampleString);
        System.out.println(set);

        ArrayNode cards = (ArrayNode) set.get("card");
        assertNotNull(cards);
        assertEquals(175, cards.size());
        ArrayNode keywords = (ArrayNode) set.get("keyword");
        assertNotNull(keywords);
        assertEquals(2, keywords.size());
    }

    @Test
    public void toAndFrom() throws IOException, URISyntaxException {
        URI uri = getClass().getResource("set").toURI();
        String exampleString = Files.readString(Path.of(uri));
        ObjectNode set = MAPPER.toJson(exampleString);
        String result = MAPPER.fromJson(set);
        System.out.println(result);
        assertEquals(exampleString, result);
    }

}
