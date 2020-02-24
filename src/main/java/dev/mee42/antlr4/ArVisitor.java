// Generated from /home/carson/java/workspace/ar/src/main/antlr4/Ar.g4 by ANTLR 4.8
package dev.mee42.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ArParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ArVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ArParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(ArParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(ArParser.DefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArParser#typeDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDef(ArParser.TypeDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionalType}
	 * labeled alternative in {@link ArParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionalType(ArParser.FunctionalTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rawType}
	 * labeled alternative in {@link ArParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRawType(ArParser.RawTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesesType}
	 * labeled alternative in {@link ArParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesesType(ArParser.ParenthesesTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerValue(ArParser.IntegerValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableCallValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableCallValue(ArParser.VariableCallValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionApplicationValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionApplicationValue(ArParser.FunctionApplicationValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lambdaValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaValue(ArParser.LambdaValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesesValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesesValue(ArParser.ParenthesesValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArParser#lambda}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambda(ArParser.LambdaContext ctx);
}