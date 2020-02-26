package dev.mee42


sealed class Value {
    abstract fun evaluate(variableSet: VariableSet): Value
    abstract fun type(): Type
    // abstract fun bind(genericType: String, type: Type):Type
    // abstract fun unbound(): List<String>
    companion object {
        val VOID = InstantValue(Type.VOID, VoidValue)
    }
}

private object VoidValue

class BindedGenericValue(val value: Value, val bindings: Map<String,Type>) {
    // typing
}

class GenericValue(val value: Value,
                   val unboundGenerics: List<String>,
                   val boundGenerics: Map<String,Type>)


// this value is *never* generic
data class InstantValue(val type: Type, val value: Any): Value() {
    override fun evaluate(variableSet: VariableSet): Value = this
    override fun type(): Type = type
    // override fun bind(generic: String, type: type) = error("Can't find a generic type to an InstantValue")
    // override fun unbound() = emptyList()
}

sealed class FunctionalValue: Value() {
    abstract fun evaluate(arguments: List<Value>, variableSet: VariableSet): Value
}


data class CallableFunction(private val type: Type, private val functionName: String): FunctionalValue() {
    override fun type(): Type = type.restructure()

    override fun evaluate(arguments: List<Value>, variableSet: VariableSet): Value {
        val func = variableSet.variableList.firstOrNull { it.name == functionName }
            ?: error("can't find function $functionName (that was promised at runtime)")
        return when(func){
            is ActualVariable -> {
                val realVal =  func.value
                realVal as? FunctionalValue ?: error("trying to apply arguments to value that is not a real type")
                return realVal.evaluate(arguments, variableSet)
            }
            is InternalVariable -> func.executor(arguments, variableSet)
        }
    }

    override fun evaluate(variableSet: VariableSet): Value {
        val func = variableSet.variableList.firstOrNull { it.name == functionName }
            ?: error("can't find function $functionName (that was promised at runtime)")
        if(func is ActualVariable) return func.value.evaluate(variableSet)
        func as InternalVariable
        if(func.type.restructure() is RawType) {
            return func.executor(emptyList(), variableSet)
        }
        return this
    }
}


data class ApplyFunction(private val function: FunctionalValue, private val appliedValue: Value): FunctionalValue() {
    init {
        if(function.type() !is FunctionType) {
            error("value (type: ${function.type().toShowString()}) is not a function, and can not be applied any values. Value: $function")
        }
        val type = (function.type() as FunctionType)
        val canApply = type.types.first() == appliedValue.type()
        if(!canApply) error("can't apply value of type ${appliedValue.type().toShowString()} to function of type ${function.type().toShowString()} - mismatched types")
    }

    override fun evaluate(arguments: List<Value>, variableSet: VariableSet): Value {
        return function.evaluate(arguments + listOf(appliedValue),variableSet)
    }

    override fun evaluate(variableSet: VariableSet): Value {
        if(type() is RawType) {
            // we can evaluate the internal command with our argument
            return function.evaluate(listOf(appliedValue),variableSet)
        } else if(type() is FunctionType){
            return this // you can't evaluate a function without a value
        }
        error("unknown state")
    }
    override fun type(): Type {
        val leftOverTypes = (function.type() as FunctionType).types.sublist(1)
        return FunctionType(leftOverTypes).restructure()
    }
}

data class LambdaFunction(private val namedArguments: List<TypedVariable>, private val type: Type, private val value: Value): FunctionalValue(){
    override fun evaluate(variableSet: VariableSet) = this
    override fun type(): Type = type.restructure()

    override fun evaluate(arguments: List<Value>, variableSet: VariableSet): Value {
        // alright, time to shine
        val newVariables = variableSet.variableList.toMutableSet()
        if(arguments.size != namedArguments.size) error("assertion failed")
        newVariables += arguments.mapIndexed { index, value ->
            if(namedArguments[index].type != value.type()) error("assertion failed #2")
            ActualVariable(name = namedArguments[index].name, value = value)
        }
        return value.evaluate(VariableSet(newVariables))
    }
}