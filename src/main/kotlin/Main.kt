package dev.mee42

import dev.mee42.parse.*
import java.util.*
import kotlin.system.exitProcess

fun main() {


    val test = """
id => a -> a
id = (x => a -> a \ x)
    """
    var variables = standardLibrary + fullParse(test, standardLibrary.typedVariables)
    
    val scanner = Scanner(System.`in`)
    var debug = false
    while(true) {
        print("> ") 
        val input = scanner.nextLine().trim()
        if(input.isBlank()) continue
        if(input.startsWith(".")){
            when(val sysCommand = input.substring(1).trim()) {
                "exit" -> exitProcess(0)
                "quit" -> exitProcess(0)
                "debug" -> debug = !debug
                "" -> print("")
                else -> System.err.println("unknown system command \"$sysCommand\"")
            }
            continue
        }
        if(input.startsWith("let")) {
            // it's a definition
            val realInput = input.substring(3).trim()
            val definition = fullParseDefinition(realInput, variables.typedVariables)
            variables += definition
        } else if(input.startsWith(":t")) {
            // :t 12
            // 01234
            //  ^\-/
            val realInput = input.substring(2).trim()
            val value = fullParseValue(realInput, variables)//.evaluate(realState)
            if(debug) println(value)
            println((if(realInput.length > 50) "*" else realInput) + " => " + value.type().toShowString())
        } else if(input.startsWith(":p")) {
            val realInput = input.substring(2).trim()
            val type = fullParseType(realInput).restructure()//.evaluate(realState)
            if(debug) println(type)
            println(type.toShowString())
        } else {
            // it's just a statement
            val value = fullParseValue(input, variables)
            if(debug) println(value)

            val end = value.evaluate(variables)
            if(end is InstantValue) {
                println(end.value)
            } else {
                println((if(input.length > 50) "*" else input) + " => " + end.type().restructure().toShowString())
            }
        }
    }
}