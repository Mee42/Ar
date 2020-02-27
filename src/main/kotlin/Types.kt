package dev.mee42

sealed class Type {
    abstract fun restructure(): Type
    companion object {
        val INT = BaseType("Int")
        val VOID = BaseType("Void")
    }
    abstract fun toShowString(needsParentheses: Boolean = false): String
    abstract fun bind(genericName: String, type: Type): Type
    abstract fun unbound(): List<String>
}

abstract class RawType(): Type()

data class BaseType(val name: String): RawType() {
    override fun restructure(): Type = this
    override fun toShowString(needsParentheses: Boolean): String = name
    
    override fun bind(genericName: String, type: Type) = this
    override fun unbound() = emptyList<String>()
}

data class ComplexType(val name: Either<BaseType,GenericType>, val subType: Type) :RawType() {
    override fun restructure(): Type = ComplexType(name, subType.restructure())
    override fun toShowString(needsParentheses: Boolean): String {
        return name.use { it.toShowString() } + "<" + subType.toShowString() + ">"
    }

    override fun bind(genericName: String, type: Type): Type {
        
        val newName = when(name){
            is Either.Left -> name
            is Either.Right -> when(val v = name.value.bind(genericName, type)) {
                is GenericType -> Either.Right(v)
                is BaseType -> Either.Left(v)
                else -> error("Can't genericize complex type with $genericName == ${type.toShowString()}")
            }
        }
        return ComplexType(newName, subType.bind(genericName, type))
    }
    override fun unbound(): List<String> {
        return (name.use { it.unbound() } + subType.unbound()).distinct()
    }
}

data class GenericType(val identifier: String): Type() {
    override fun restructure(): Type = this
    override fun toShowString(needsParentheses: Boolean): String {
        return identifier
    }
    override fun bind(genericName: String, type: Type): Type {
        if(identifier == genericName) return type
        else return this
    }
    override fun unbound() = listOf(identifier)
}


data class FunctionType(val types: List<Type>): Type() {
    init {
        if(types.isEmpty()) error("types list can not be empty")
    }

    override fun restructure(): Type {
        if(types.size == 1) return types[0]
        if(types.last() is FunctionType) {
            val newTypes = types.dropLast(1) + (types.last() as FunctionType).types
            return FunctionType(newTypes.map(Type::restructure)).restructure()
        } else {
            return FunctionType(types.map(Type::restructure))
        }
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
    override fun bind(genericName: String, type: Type): Type {
        return FunctionType(types.map { it.bind(genericName, type) }).restructure()
    }
    override fun unbound() = TODO()
}
