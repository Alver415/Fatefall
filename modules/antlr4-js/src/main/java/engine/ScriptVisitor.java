package engine;

import gen.JavaScriptParser.*;
import gen.JavaScriptParserBaseVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class ScriptVisitor extends JavaScriptParserBaseVisitor<Value> {

    private final Scope global = new Scope(null);
    private final Scope scope = global;

    @Override
    public Value visit(ParseTree parseTree){
        System.out.println(parseTree.getText());
        return super.visit(parseTree);
    }

    @Override
    public Value visitProgram(ProgramContext ctx) {
        return visitSourceElements(ctx.sourceElements());
    }

    @Override
    public Value visitReturnStatement(ReturnStatementContext ctx) {
        return visitExpressionSequence(ctx.expressionSequence());
    }

    @Override
    public Value visitVariableDeclaration(VariableDeclarationContext ctx) {
        String identifier = visitAssignable(ctx.assignable()).asString();
        scope.declare(identifier);
        Value value = visit(ctx.singleExpression());
        scope.assign(identifier, value);
        return value;
    }

    @Override
    public Value visitAssignmentExpression(AssignmentExpressionContext ctx) {
        if (ctx.singleExpression(0) instanceof IdentifierExpressionContext ictx){
            String identifier = ictx.identifier().Identifier().getText();
            Value right = visit(ctx.singleExpression(1));
            scope.assign(identifier, right);
            return right;
        }
        throw new RuntimeException("Fuck this");
    }

    @Override
    public Value visitIdentifier(IdentifierContext ctx) {
        return new Value(ctx.Identifier().getText());
    }
    @Override
    public Value visitIdentifierExpression(IdentifierExpressionContext ctx) {
        Value identifier = visitIdentifier(ctx.identifier());
        return scope.resolve(identifier.asString());
    }

    @Override
    public Value visitLiteral(LiteralContext ctx) {
        String text = ctx.StringLiteral().getText();
        return new Value(text.substring(1, text.length() - 1));
    }
    @Override
    public Value visitAdditiveExpression(AdditiveExpressionContext ctx) {
        Value left = visit(ctx.singleExpression(0));
        Value right = visit(ctx.singleExpression(1));
        return new Value(left.asString() + right.asString());
    }
}
