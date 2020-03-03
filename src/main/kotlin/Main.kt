package dev.mee42

import dev.mee42.parse.*
import java.util.*
import kotlin.system.exitProcess

fun main() {


    val test = """"""
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
        try {
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
            } else if(input.startsWith(":f")) {
                val realInput = input.substring(2).trim()
                val (typeStr, bindName, bindTypeStr) = realInput.split(":",limit = 3).map(String::trim)
                val type = fullParseType(typeStr).restructure()
                val bindType = fullParseType(bindTypeStr).restructure()
                println("type: " + type.toShowString())
                println("binding $bindName to " + bindType.toShowString())
                val binded = type.bind(bindName, bindType).restructure()
                println(binded.toShowString() + "    ($binded)")
            } else {
                // it's just a statement
                val value = fullParseValue(input, variables)
                if(debug) println(value)
                val end = value.evaluate(variables)
                variables += ActualVariable(name = "last",value = value)

                if(end is InstantValue) {
                    println(end.value)
                } else {
                    println((if(input.length > 50) "*" else input) + " => " + end.type().restructure().toShowString())
                }
            }
        }catch(e: Exception) {
            e.printStackTrace()
            System.out.flush()
        }
    }
}