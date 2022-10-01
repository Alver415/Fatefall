import engine.Value;
import engine.ScriptVisitor;
import gen.JavaScriptLexer;
import gen.JavaScriptParser;
import gen.JavaScriptParserVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Antlr4Test {

    private static String TEST_JS;

    @BeforeAll
    public static void setup() throws IOException {
        TEST_JS = Files.readString(Path.of("src/test/resources/test.js"));
    }

    @Test
    public void test() {
        CodePointCharStream cs = CharStreams.fromString(TEST_JS);
        JavaScriptLexer lexer = new JavaScriptLexer(cs);
        CommonTokenStream cts = new CommonTokenStream(lexer);
        JavaScriptParser parser = new JavaScriptParser(cts);
        JavaScriptParserVisitor<Value> visitor = new ScriptVisitor();
        Value value = visitor.visit(parser.program());

        System.err.println(value);
    }
}
