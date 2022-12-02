import com.alver.fatefall.api.Card;
import com.alver.scryfall.api.CardApi;
import com.alver.scryfall.api.ScryfallApiClient;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScryfallApiClientTest {

    @Test
    public void test(){
        ScryfallApiClient scryfallApiClient = new ScryfallApiClient();
        CardApi cardApi = scryfallApiClient.getCardApi();
        List<Card> cards = cardApi.search("Nicol Bolas");
        assertEquals(6, cards.size());
    }
}
