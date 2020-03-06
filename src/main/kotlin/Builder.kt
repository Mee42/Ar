package dev.mee42

import dev.mee42.parse.fullParseType
import dev.mee42.VariableSet

@DslMarker
annotation class Builder

@Builder
class VariableSetBuilder {
    private val list = mutableListOf<NamedVariable>()
    fun func(block: InternalFunctionBuilder.() -> Unit) {
        list.add(InternalFunctionBuilder().apply(block).build())
    }
    fun func(name: String, block: InternalFunctionBuilder.() -> Unit) {
        list.add(InternalFunctionBuilder().apply(block).apply { name(name) }.build())
    }

    companion object {
        fun internal(block: VariableSetBuilder.() -> Unit): VariableSet {
            return VariableSetBuilder().apply(block).list.toSet()
        }
    }
}

@Builder
class InternalFunctionBuilder {
    private var name: String? = null
    fun name(name: String) { this.name = name }
    private var type: Type? = null
    fun type(type: Type) { this.type = type}
    fun type(type: String) { this.type = fullParseType(type)
    }
    private var executor: InternalExecutor? = null
    fun executor(executor: InternalExecutor){ this.executor = executor }
    fun build(): NamedVariable {
        return InternalVariable(
            name =     name     ?: error("need to specify a type"),
            type =     type     ?: error("need to specify a type"),
            executor = executor ?: error("need to specify a type")
        )
    }
}