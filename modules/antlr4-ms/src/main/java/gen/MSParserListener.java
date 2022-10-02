// Generated from C:/Users/Alver/Development/projects/Fatefall/modules/antlr4-ms/src/main/antlr4\MSParser.g4 by ANTLR 4.10.1
package gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MSParser}.
 */
public interface MSParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MSParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(MSParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(MSParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(MSParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(MSParser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSParser#object}.
	 * @param ctx the parse tree
	 */
	void enterObject(MSParser.ObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSParser#object}.
	 * @param ctx the parse tree
	 */
	void exitObject(MSParser.ObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(MSParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(MSParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(MSParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(MSParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSParser#value_scalar}.
	 * @param ctx the parse tree
	 */
	void enterValue_scalar(MSParser.Value_scalarContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSParser#value_scalar}.
	 * @param ctx the parse tree
	 */
	void exitValue_scalar(MSParser.Value_scalarContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSParser#multiline_string}.
	 * @param ctx the parse tree
	 */
	void enterMultiline_string(MSParser.Multiline_stringContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSParser#multiline_string}.
	 * @param ctx the parse tree
	 */
	void exitMultiline_string(MSParser.Multiline_stringContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSParser#string_literal}.
	 * @param ctx the parse tree
	 */
	void enterString_literal(MSParser.String_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSParser#string_literal}.
	 * @param ctx the parse tree
	 */
	void exitString_literal(MSParser.String_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSParser#string_folded}.
	 * @param ctx the parse tree
	 */
	void enterString_folded(MSParser.String_foldedContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSParser#string_folded}.
	 * @param ctx the parse tree
	 */
	void exitString_folded(MSParser.String_foldedContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSParser#string_double_quoted}.
	 * @param ctx the parse tree
	 */
	void enterString_double_quoted(MSParser.String_double_quotedContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSParser#string_double_quoted}.
	 * @param ctx the parse tree
	 */
	void exitString_double_quoted(MSParser.String_double_quotedContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSParser#mapping}.
	 * @param ctx the parse tree
	 */
	void enterMapping(MSParser.MappingContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSParser#mapping}.
	 * @param ctx the parse tree
	 */
	void exitMapping(MSParser.MappingContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(MSParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(MSParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSParser#number_integer}.
	 * @param ctx the parse tree
	 */
	void enterNumber_integer(MSParser.Number_integerContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSParser#number_integer}.
	 * @param ctx the parse tree
	 */
	void exitNumber_integer(MSParser.Number_integerContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSParser#number_float}.
	 * @param ctx the parse tree
	 */
	void enterNumber_float(MSParser.Number_floatContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSParser#number_float}.
	 * @param ctx the parse tree
	 */
	void exitNumber_float(MSParser.Number_floatContext ctx);
}