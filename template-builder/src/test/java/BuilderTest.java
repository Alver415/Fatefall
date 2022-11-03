import com.alver.fatefall.templatebuilder.components.block.Card;
import com.alver.fatefall.templatebuilder.components.builders.CardBuilder;
import com.alver.fatefall.templatebuilder.components.builders.CardFaceBuilder;
import com.alver.fatefall.templatebuilder.components.builders.TextBlockBuilder;
import javafx.application.Platform;
import org.junit.jupiter.api.Test;

public class BuilderTest {
    @Test
    public void test() {
        Platform.startup(() -> {
            Card card = CardBuilder.builder()
                    .front(CardFaceBuilder.builder()
                            .addBlock(TextBlockBuilder.builder()
                                    .text("test")
                                    .build())
                            .build())
                    .build();
        });
    }
}
