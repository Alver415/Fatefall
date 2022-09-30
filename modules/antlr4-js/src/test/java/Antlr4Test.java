import gen.JavaScriptLexer;
import gen.JavaScriptParser;
import gen.JavaScriptParserBaseVisitor;
import gen.JavaScriptParserVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

public class Antlr4Test {

    @Test
    public void test() {
        String jsString = "var name = 'Alex'; var hello = 'Hello ' + name; console.out(hello);";
        CodePointCharStream cs = CharStreams.fromString(jsString);
        JavaScriptLexer lexer = new JavaScriptLexer(cs);
        CommonTokenStream cts = new CommonTokenStream(lexer);
        JavaScriptParser parser = new JavaScriptParser(cts);
        JavaScriptParserVisitor<Void> visitor = new JavaScriptParserBaseVisitor<Void>() {
            @Override
            public Void visitProgram(JavaScriptParser.ProgramContext ctx) {
                System.out.println(ctx.getText());
                return super.visitProgram(ctx);
            }
            @Override
            public Void visitVariableDeclaration(JavaScriptParser.VariableDeclarationContext ctx) {
                System.out.println(ctx.getText());
                return super.visitVariableDeclaration(ctx);
            }
            @Override
            public Void visitStatement(JavaScriptParser.StatementContext ctx) {
                System.out.println(ctx.getText());
                return super.visitStatement(ctx);
            }
        };
        visitor.visit(parser.program());

    }
}
