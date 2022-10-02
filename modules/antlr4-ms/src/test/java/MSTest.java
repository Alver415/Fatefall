import engine.MSVisitor;
import engine.types.MSValue;
import gen.MSLexer;
import gen.MSParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MSTest {

    private static String TEST_JS;

    @BeforeAll
    public static void setup() throws IOException {
        TEST_JS = Files.readString(Path.of("src/test/resources/test.ms"));
    }

    @Test
    public void test() throws Exception {
        CodePointCharStream cs = CharStreams.fromString(TEST_JS);
        MSLexer lexer = new MSLexer(cs);
        CommonTokenStream cts = new CommonTokenStream(lexer);
        MSParser parser = new MSParser(cts);
        MSVisitor visitor = new MSVisitor();
        MSValue value = visitor.visit(parser.file());

        System.err.println(value);
    }
}
