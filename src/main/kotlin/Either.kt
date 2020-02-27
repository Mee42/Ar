package dev.mee42

sealed class Either<out A, out B> {
    class Left<A>(val value: A): Either<A, Nothing>() {
    	override fun toString() = value.toString()
    }
    class Right<B>(val value: B): Either<Nothing, B>() {
    	override fun toString() = value.toString()
    }
}

fun <A,R> Either<A,A>.use(func: (A) -> R):R {
	return when(this){
		is Either.Left -> func(value)
		is Either.Right -> func(value)
		// else -> error("bruh")
	}
}
fun <A,B,R> Either<A,B>.yes(funcA: (A) -> R, funcB: (B) -> R):R {
	return when(this) {
		is Either.Left -> funcA(value)
		is Either.Right -> funcB(value)
	}
}