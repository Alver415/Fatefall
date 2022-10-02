package engine;

import engine.types.MSNull;
import engine.types.MSObject;
import engine.types.MSString;
import engine.types.MSValue;
import gen.MSParser;
import gen.MSParserBaseVisitor;

import java.util.Stack;

public class MSVisitor extends MSParserBaseVisitor<MSValue> {

    private boolean debug = true;


    private final Scope global;
    private final Stack<MSValue> stack;

    public MSVisitor(){
        this.global = new Scope(null);
        this.stack= new Stack<>();

        MSValue root = new MSValue();
        stack.push(root);
    }

    @Override
    public MSValue visitFile(MSParser.FileContext context) {
        return context.document().stream()
                .map(this::visitDocument)
                .reduce((a, b) -> b)
                .orElse(MSNull.NULL);
    }

    @Override
    public MSValue visitDocument(MSParser.DocumentContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MSValue visitObject(MSParser.ObjectContext ctx) {
        MSObject object = new MSObject();
        stack.push(object);
        MSValue visit = visitChildren(ctx);
        return stack.pop();
    }

    @Override
    public MSValue visitKey(MSParser.KeyContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MSValue visitValue(MSParser.ValueContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MSValue visitValue_scalar(MSParser.Value_scalarContext ctx) {
        return new MSString(ctx.getText());
    }

    @Override
    public MSValue visitMultiline_string(MSParser.Multiline_stringContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MSValue visitString_literal(MSParser.String_literalContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MSValue visitString_folded(MSParser.String_foldedContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MSValue visitString_double_quoted(MSParser.String_double_quotedContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MSValue visitMapping(MSParser.MappingContext ctx) {
        String key = ctx.key().getText();
        MSValue value = visit(ctx.value());
        MSValue current = stack.peek();
        if (current instanceof MSObject object) {
            object.put(key, value);
            if (debug) {
                String depth = "\t".repeat(stack.size() - 2);
                System.out.printf("%s%s: %s\n", depth, key, value);
            }
        } else {
            throw new RuntimeException("FUck");
        }
        return value;
    }

    @Override
    public MSValue visitNumber(MSParser.NumberContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MSValue visitNumber_integer(MSParser.Number_integerContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MSValue visitNumber_float(MSParser.Number_floatContext ctx) {
        return visitChildren(ctx);
    }
}
