package dev.mee42


sealed class Value {
    abstract fun evaluate(variableSet: VariableSet): Value
    abstract fun type(): Type
    abstract fun bind(genericType: String, type: Type):Value
    abstract fun unbound(): List<String>
    abstract fun toString(indent: String, valueName: String): String
    companion object {
        val VOID = InstantValue(Type.VOID, VoidValue)
    }
}

private object VoidValue


// this value is *never* generic
data class InstantValue(val type: Type, val value: Any): Value() {
    override fun evaluate(variableSet: VariableSet): Value = this
    override fun type(): Type = type
    override fun bind(genericType: String, type: Type):Value = this
    override fun unbound(): List<String> = emptyList()
    override fun toString(indent: String, valueName: String): String {
        return "${indent}_$valueName:$value => ${type.toShowString()}"
    }
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
                realVal.evaluate(arguments, variableSet)
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
    override fun unbound() = type.unbound()
    override fun toString(indent: String, valueName: String): String {
        return "${indent}_$valueName: CallableFunction($functionName) => " + type.toShowString()
    }

    override fun bind(genericType: String, type: Type):Value {
        return CallableFunction(this.type.bind(genericType, type), functionName)
    }
}


data class ApplyFunction (private val function: FunctionalValue, private val appliedValue: Value): FunctionalValue() {
    init {
        if(function.type() !is FunctionType) {
            error("value (type: ${function.type().toShowString()}) is not a function, and can not be applied any values. Function: $function")
        }
        val type = (function.type() as FunctionType)
        if(type.types.first() != appliedValue.type())
         error("can't apply value of type ${appliedValue.type().toShowString()} to function of type ${function.type().toShowString()} - mismatched types")
    }
    companion object {
        fun createAndBind(function: Value, applied: Value):ApplyFunction {
            if(function !is FunctionalValue) error("can't apply to something that is not of type FunctionalValue")
            if(function.type() !is FunctionType) {
                error("value (type: ${function.type().toShowString()}) is not a function, and can not be applied any values. Value: $applied")
            }
            val functionType = (function.type() as FunctionType)
            val applyTo = functionType.types.first()
            val applyFrom = applied.type()
            // okay, this is reasonable
            // just.. match the two up
            // ez pz
            // fuck
            // uhhhhhh
            if(applyTo.unbound().isEmpty() && applyFrom.unbound().isEmpty()) {
                // ez case - no generics
                if(applyTo != applyFrom) {
                    error("can't apply value of type ${applied.type().toShowString()} to function of type ${function.type().toShowString()} - mismatched types")
                } else {
                    return ApplyFunction(function, applied)
                }
            }
            // if applyTo is a generic type, the binding should be easy
            if(applyTo is GenericType) {
                // just bind it? lol
                return ApplyFunction(function.bind(applyTo.identifier, applyFrom) as FunctionalValue,applied)
            }
            // loop through each generic type and see what works? :thonk:
            // for(testGeneric in unbound()) {
            //     val newType = function.bind(testGeneric, applyFrom)
            // }
            TODO("can't handle generic function application (of this complexity) right now")

        }
    } 

    override fun evaluate(arguments: List<Value>, variableSet: VariableSet): Value {
        return function.evaluate(listOf(appliedValue) + arguments,variableSet)
    }

    override fun evaluate(variableSet: VariableSet): Value {
        return when {
            type() is RawType -> {
                // we can evaluate the internal command with our argument
                function.evaluate(listOf(appliedValue),variableSet)
            }
            type() is FunctionType -> {
                this // you can't evaluate a function without a value
            }
            else -> error("unknown state: ${type()}")
        }
    }
    override fun type(): Type {
        val leftOverTypes = (function.type() as FunctionType).types.sublist(1)
        return FunctionType(leftOverTypes).restructure()
    }
    override fun unbound() = type().unbound()
    override fun toString(indent: String, valueName: String): String {
        return "${indent}_$valueName: ApplyFunction\n" + function.toString(indent + "__","function") + "\n" +
                                                       appliedValue.toString(indent + "__", "appliedValue")
    }

    override fun bind(genericType: String, type: Type):Value {
        return ApplyFunction(function.bind(genericType,type) as FunctionalValue, appliedValue.bind(genericType,type))
    }
}

data class LambdaFunction(private val namedArguments: List<TypedVariable>, private val type: Type, private val value: Value): FunctionalValue(){

    override fun evaluate(variableSet: VariableSet) = this
    override fun type(): Type = type.restructure()

    override fun evaluate(arguments: List<Value>, variableSet: VariableSet): Value {
        // alright, time to shine
        val newVariables = variableSet.variableList.toMutableSet()
        if(arguments.size < namedArguments.size) {
            println("this should never execute, but maybe it'll work anyway?")
            return this
//            error("assertion failed\narguments:$arguments\n" +
//                    "\tnamedArguments: $namedArguments,\n" +
//                    "\ttype of lambda: $type\n" +
//                    "\tvalue: $value")
        } else if(arguments.size > namedArguments.size) {
            // apply just what we need to, and then apply the result to the result
            newVariables += namedArguments.mapIndexed { index, typedVariable ->
                ActualVariable(name = typedVariable.name, value = arguments[index])
            }
            val stepOne =  value.evaluate(VariableSet(newVariables))
            stepOne as? FunctionalValue ?: error("can't apply more arguments to $stepOne")
            return stepOne.evaluate(arguments.subList(namedArguments.size, arguments.size), variableSet)
        }
        newVariables += arguments.mapIndexed { index, value ->
            //if(namedArguments[index].type != value.type()) error("assertion failed #2")
            ActualVariable(name = namedArguments[index].name, value = value)
        }
        return value.evaluate(VariableSet(newVariables))
    }
    override fun unbound() = type().unbound()
    override fun toString(indent: String, valueName: String): String {
        return "${indent}_$valueName: LambdaFunction (" +
                namedArguments.joinToString(",", "[", "]") { it.name + " => " + it.type } +
                " => ${type.toShowString()}\n" + value.toString(indent + "__", "value")
    }

    override fun bind(genericType: String, type: Type):Value {
        return LambdaFunction(namedArguments = namedArguments.map { (name, type) -> TypedVariable(name, type.bind(genericType,type)) },
                              type = type.bind(genericType, type),
                              value = value.bind(genericType, type))
    }
}