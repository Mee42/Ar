package dev.mee42

sealed class Either<out A, out B> {
    class Left<A>(val value: A): Either<A, Nothing>()
    class Right<B>(val value: B): Either<Nothing, B>()
}

fun <A,R> Either<A,A>.use(func: (A) -> R):R {
	return when(this){
		is Either.Left -> func(value)
		is Either.Right -> func(value)
		// else -> error("bruh")
	}
}