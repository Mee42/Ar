package dev.mee42.parse

import dev.mee42.*
import dev.mee42.antlr4.ArLexer
import dev.mee42.antlr4.ArParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

fun fullParse(fileContent: String, existingVariables: List<TypedVariable>): VariableSet {
    val tokens = CommonTokenStream(ArLexer(CharStreams.fromString(fileContent)))
    val parser = ArParser(tokens)
    val main = parser.main()
    val typedVariables = main.typeDef().map(::parseTypeDef) + existingVariables
    val variables = main.definition().map { def ->
        val identifier = def.IDENTIFIER().symbol.text
        val typeDef = typedVariables.firstOrNull { it.name == identifier }
            ?: error("can't find type definition for variable $identifier")
        val value = parseValue(def.value(), typedVariables)
        if(value.type() != typeDef.type){
            error("value \"${def.text}\" of type ${value.type()} does not match type definition - expected ${typeDef.type}")
        }
        ActualVariable(identifier, value)
    }
    return VariableSet(variables.toSet())
}
public fun fullParseType(type: String): Type {
    val tokens = CommonTokenStream(ArLexer(CharStreams.fromString(type)))
    val parser = ArParser(tokens)
    val typeDef = parser.type()
    return parseType(typeDef)
}

fun fullParseDefinition(input: String, variables: List<TypedVariable>): ActualVariable {
    val tokens = CommonTokenStream(ArLexer(CharStreams.fromString(input)))
    val parser = ArParser(tokens)
    val typeDef = parser.definition();
    val identifier = typeDef.IDENTIFIER().symbol.text
    val value = parseValue(typeDef.value(), variables)
    return ActualVariable(identifier, value)
}
fun fullParseValue(string: String, variableSet: VariableSet): Value {
    val tokens = CommonTokenStream(ArLexer(CharStreams.fromString(string)))
    val parser = ArParser(tokens)
    return parseValue(parser.value(), variableSet.typedVariables)
}



private fun parseTypeDef(typeDef : ArParser.TypeDefContext): TypedVariable {
    val name = typeDef.IDENTIFIER().symbol.text
    val type = parseType(typeDef.type())
    return TypedVariable(name, type)
}
private fun parseType(typeDef: ArParser.TypeContext): Type {
    return when(typeDef){
        is ArParser.BaseTypeContext -> BaseType(typeDef.TYPE_IDENTIFIER().text)
        is ArParser.ParenthesesTypeContext -> parseType(typeDef.type())
        is ArParser.FunctionalTypeContext -> { // yikes, a loop
            val list = mutableListOf<Type>()
            // add the first element
            var tempType = typeDef
            while(tempType is ArParser.FunctionalTypeContext) {
                list.add(parseType(tempType.type()[1]))
                tempType = tempType.type()[0]
                // while(tempType is ArParser.ParenthesesTypeContext) {
                //     tempType = tempType.type()
                // }
            }
            list.add(parseType(tempType))
            list.reverse()
            FunctionType(list)
        }
        is ArParser.ComplexTypeContext -> {
            val base = typeDef.TYPE_IDENTIFIER().text
            val subType = parseType(typeDef.type())
            ComplexType(base, subType)
        }
        is ArParser.GenericTypeContext -> {
            GenericType(typeDef.IDENTIFIER().text)
        }
        else -> error("unknown type")
    }
}

private fun parseValue(value: ArParser.ValueContext, variables: List<TypedVariable>): Value {
    return when(value){
        is ArParser.IntegerValueContext -> {
            val i = value.INTEGER().symbol.text.toIntOrNull()
                ?: error("integer \"" +  value.INTEGER().symbol.text + "\" can not be parsed to an integer")
            InstantValue(Type.INT, i)
        }
        is ArParser.VariableCallValueContext -> {
            val identifier = value.IDENTIFIER().symbol.text
            // get the variable
            val variable = variables.firstOrNull { it.name == identifier } ?: error("Can't find variable called \"$identifier\"")
            CallableFunction(variable.type, variable.name)
        }
        is ArParser.FunctionApplicationValueContext -> {
            ApplyFunction(
                function = parseValue(
                    value.value()[0],
                    variables
                ) as? FunctionalValue ?: error("Can't apply value to \"${value.text}\""),
                appliedValue = parseValue(value.value()[1], variables)
            )
        }
        is ArParser.ParenthesesValueContext -> {
            parseValue(value.value(), variables)
        }
        is ArParser.LambdaValueContext -> {
            parseLambda(value.lambda(), variables)
        }
        else -> error("unsupported value type " + value.javaClass.name)
    }
}
private fun parseLambda(lambda: ArParser.LambdaContext, variableSet: List<TypedVariable>): Value {
    val type = parseType(lambda.type())
    val arguments = lambda.IDENTIFIER()// size >= 1
    type as? FunctionType ?: error("lambda must be of a function type - ie, not constant: ${type.toShowString()}")
    if(type.types.size != arguments.size + 1) {
        error("argument count and type size don't match: types size: ${type.types.size}, arguments size: ${arguments.size} (should be ${type.types.size - 1})")
    }
    val typedVariables = arguments
        .map { it.symbol.text }
        .mapIndexed { i, name -> TypedVariable(name, type.types[i]) }
    variableSet.firstOrNull { typedVariables.any { typed -> typed.name == it.name } }?.let {
        error("Can't use ${it.name} as an identifier as it is already used")
    }
    val value = parseValue(lambda.value(), variableSet + typedVariables)
    return LambdaFunction(namedArguments = typedVariables, type = type,value = value)
}
