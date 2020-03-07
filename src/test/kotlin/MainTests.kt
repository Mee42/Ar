package dev.mee42

import dev.mee42.parse.CompileError
import dev.mee42.parse.ParseError
import dev.mee42.parse.fullParseType
import dev.mee42.parse.fullParseValue
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.*
import io.kotest.matchers.*
import kotlin.reflect.KClass


class MainTests : StringSpec({
    "integer literals should evaluate to an integer literal" {
        ar("1") shouldEvaluateTo lit(1)
    }
    "integer literals can not be negative" {
        shouldNotParse("-1")
    }
    "integer literals can include 0" {
        ar("0") shouldEvaluateTo lit(0)
    }
    "integer literals can not be above the integer limit" {
        shouldNotParse("10000000000000","integer \"10000000000000\" can not be parsed to an integer")
    }
    "4-function math function literals compile" {
        ar("add")   shouldEvaluateTo funcLiteral(type = "Int -> Int -> Int", classname = CallableFunction::class)
        ar("sub")   shouldEvaluateTo funcLiteral(type = "Int -> Int -> Int", classname = CallableFunction::class)
        ar("times") shouldEvaluateTo funcLiteral(type = "Int -> Int -> Int", classname = CallableFunction::class)
        ar("div")   shouldEvaluateTo funcLiteral(type = "Int -> Int -> Int", classname = CallableFunction::class)
    }
    "adding 2 and 2 makes 4" {
        ar("add 2 2") shouldEvaluateTo lit(4)
    }
    "negative numbers work" {
        ar("sub 0 10") shouldEvaluateTo lit(-10)
    }
    "subtraction works and order is relevant" {
        ar("sub 10 2") shouldEvaluateTo lit(8)
        ar("sub 2 10") shouldEvaluateTo lit(-8)
    }
    "division is normal integer division" {
        (0..8).flatMap { a -> (1gita..8).map { b -> a to b } }.map { (a,b) ->
            ar("div $a $b") shouldEvaluateTo lit(a / b)
        }
    }
})

private fun <T: Value> funcLiteral(type: String? = null, classname: KClass<T>): Literal {
    return FunctionLiteral(type?.run(::fullParseType), classname.java)
}

fun shouldNotParse(str: String, errStr: String? = null){
    val err = shouldThrow<ParseError> { ar(str) }
    errStr?.let { err.message shouldBe it }
}
fun shouldNotCompile(str: String, errStr: String? = null){
    val err = shouldThrow<CompileError> { ar(str) }
    errStr?.let { err.message shouldBe it }

}

private sealed class Literal(val type: Type?){
    constructor(type: String): this(fullParseType(type))
}
private class IntLiteral(val value: Int): Literal(Type.INT)
private class FunctionLiteral<T: Value>(
    type: Type?,
    val classname: Class<T>?
): Literal(type)

private fun lit(i: Int) = IntLiteral(i)

private infix fun Value.shouldEvaluateTo(literal: Literal) {
    val v = this.evaluate(VariableCollection(emptySet(), standardLibrary))
    literal.type?.let { lit -> v.type() shouldBe lit}
    when(literal) {
        is IntLiteral -> {
            (v as InstantValue).value as Int shouldBe literal.value
        }
        is FunctionLiteral<*> -> {
            v as FunctionalValue
            v.javaClass shouldBe literal.classname
        }
    }
}

private fun ar(expression: String): Value = fullParseValue(expression, standardLibrary.types)