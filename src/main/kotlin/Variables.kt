package dev.mee42

data class TypedVariable(val name: String, val type: Type) {
    override fun toString(): String {
        return "TypedVariable($name => ${type.toShowString()})"
    }
}
typealias InternalExecutor = (List<Value>, VariableSet) -> Value

sealed class NamedVariable(val name: String, val type: Type){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NamedVariable) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

class ActualVariable(name: String,val value: Value): NamedVariable(name, value.type())
class InternalVariable(name: String, type: Type, val executor: InternalExecutor): NamedVariable(name, type)

data class VariableSet(val variableList: Set<NamedVariable>) {
    operator fun plus(other: VariableSet) = VariableSet(other.variableList + variableList)
    operator fun plus(other: NamedVariable) : VariableSet {
        val newSet = variableList.toMutableSet()
        if(!newSet.add(other)){
            newSet.remove(other)
            newSet.add(other)
        }
        return VariableSet(newSet)
    }
    val typedVariables = variableList.map { TypedVariable(it.name, it.type) }
}
