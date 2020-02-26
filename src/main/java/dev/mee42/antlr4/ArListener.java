// Generated from src/main/antlr4/Ar.g4 by ANTLR 4.8
package dev.mee42.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ArParser}.
 */
public interface ArListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ArParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(ArParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(ArParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(ArParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(ArParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArParser#typeDef}.
	 * @param ctx the parse tree
	 */
	void enterTypeDef(ArParser.TypeDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArParser#typeDef}.
	 * @param ctx the parse tree
	 */
	void exitTypeDef(ArParser.TypeDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionalType}
	 * labeled alternative in {@link ArParser#type}.
	 * @param ctx the parse tree
	 */
	void enterFunctionalType(ArParser.FunctionalTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionalType}
	 * labeled alternative in {@link ArParser#type}.
	 * @param ctx the parse tree
	 */
	void exitFunctionalType(ArParser.FunctionalTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code complexType}
	 * labeled alternative in {@link ArParser#type}.
	 * @param ctx the parse tree
	 */
	void enterComplexType(ArParser.ComplexTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code complexType}
	 * labeled alternative in {@link ArParser#type}.
	 * @param ctx the parse tree
	 */
	void exitComplexType(ArParser.ComplexTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code genericType}
	 * labeled alternative in {@link ArParser#type}.
	 * @param ctx the parse tree
	 */
	void enterGenericType(ArParser.GenericTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code genericType}
	 * labeled alternative in {@link ArParser#type}.
	 * @param ctx the parse tree
	 */
	void exitGenericType(ArParser.GenericTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesesType}
	 * labeled alternative in {@link ArParser#type}.
	 * @param ctx the parse tree
	 */
	void enterParenthesesType(ArParser.ParenthesesTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesesType}
	 * labeled alternative in {@link ArParser#type}.
	 * @param ctx the parse tree
	 */
	void exitParenthesesType(ArParser.ParenthesesTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code baseType}
	 * labeled alternative in {@link ArParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBaseType(ArParser.BaseTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code baseType}
	 * labeled alternative in {@link ArParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBaseType(ArParser.BaseTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(ArParser.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(ArParser.IntegerValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableCallValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 */
	void enterVariableCallValue(ArParser.VariableCallValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableCallValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 */
	void exitVariableCallValue(ArParser.VariableCallValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionApplicationValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 */
	void enterFunctionApplicationValue(ArParser.FunctionApplicationValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionApplicationValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 */
	void exitFunctionApplicationValue(ArParser.FunctionApplicationValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lambdaValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 */
	void enterLambdaValue(ArParser.LambdaValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lambdaValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 */
	void exitLambdaValue(ArParser.LambdaValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesesValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 */
	void enterParenthesesValue(ArParser.ParenthesesValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesesValue}
	 * labeled alternative in {@link ArParser#value}.
	 * @param ctx the parse tree
	 */
	void exitParenthesesValue(ArParser.ParenthesesValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArParser#lambda}.
	 * @param ctx the parse tree
	 */
	void enterLambda(ArParser.LambdaContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArParser#lambda}.
	 * @param ctx the parse tree
	 */
	void exitLambda(ArParser.LambdaContext ctx);
}