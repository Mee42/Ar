package dev.mee42

sealed class Type {
    abstract fun restructure(): Type
    companion object {
        val INT = RawType("Int")
        val VOID = RawType("Void")
    }
    abstract fun toShowString(needsParentheses: Boolean = false): String
}
data class RawType(val name: String): Type() {
    override fun restructure(): Type = this
    override fun toShowString(needsParentheses: Boolean): String = name
}
data class FunctionType(val types: List<Type>): Type() {
    init {
        if(types.isEmpty()) error("types list can not be empty")
    }

    override fun restructure(): Type {
        if(types.size == 1) return types[0]
        return this
    }

    override fun toShowString(needsParentheses: Boolean): String {
        val str = StringBuilder()
        for(type in types) {
            str.append(type.toShowString(true))
            str.append(" -> ")
        }
        val string = str.substring(0, str.length - 4)
        return if(needsParentheses) "($string)" else string
    }
}
