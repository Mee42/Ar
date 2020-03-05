package dev.mee42

data class TypedVariable(val name: String, val type: Type) {
    override fun toString(): String {
        return "TypedVariable($name => ${type.toShowString()})"
    }
}
typealias InternalExecutor = (List<Value>, VariableCollection) -> Value

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

data class VariableCollection(val localVariables: Set<NamedVariable>, val globalVariables: Set<NamedVariable>) {
    fun bindLocalVariable(namedVariable: NamedVariable): VariableCollection {
        return VariableCollection(localVariables + namedVariable, globalVariables)
    }
    fun bindGlobalVariable(globalVariable: NamedVariable): VariableCollection {
        return VariableCollection(localVariables, globalVariables)
    }
    val types = globalVariables.map { TypedVariable(it.name, it.type) } + localVariables.map { TypedVariable(it.name, it.type) }
    fun findForName(name: String): NamedVariable {
        return localVariables.firstOrNull { it.name == name }
            ?: globalVariables.firstOrNull { it.name == name }
            ?: error("can't find function $name (that was promised at runtime)")
    }
}