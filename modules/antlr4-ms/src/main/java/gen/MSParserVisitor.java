// Generated from C:/Users/Alver/Development/projects/Fatefall/modules/antlr4-ms/src/main/antlr4\MSParser.g4 by ANTLR 4.10.1
package gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MSParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MSParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(MSParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link MSParser#document}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDocument(MSParser.DocumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MSParser#object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject(MSParser.ObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link MSParser#key}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKey(MSParser.KeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MSParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(MSParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MSParser#value_scalar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue_scalar(MSParser.Value_scalarContext ctx);
	/**
	 * Visit a parse tree produced by {@link MSParser#multiline_string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiline_string(MSParser.Multiline_stringContext ctx);
	/**
	 * Visit a parse tree produced by {@link MSParser#string_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_literal(MSParser.String_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link MSParser#string_folded}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_folded(MSParser.String_foldedContext ctx);
	/**
	 * Visit a parse tree produced by {@link MSParser#string_double_quoted}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_double_quoted(MSParser.String_double_quotedContext ctx);
	/**
	 * Visit a parse tree produced by {@link MSParser#mapping}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapping(MSParser.MappingContext ctx);
	/**
	 * Visit a parse tree produced by {@link MSParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(MSParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link MSParser#number_integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber_integer(MSParser.Number_integerContext ctx);
	/**
	 * Visit a parse tree produced by {@link MSParser#number_float}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber_float(MSParser.Number_floatContext ctx);
}