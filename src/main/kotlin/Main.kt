package dev.mee42

import dev.mee42.parse.*
import java.util.*
import kotlin.system.exitProcess

fun main() {

    val test = """
        id => a -> a
        id = (x => a -> a \ x)
        
        count => Int -> Int -> Int
        count = (n i => Int -> Int -> Int \ if (max (sub 100 n) 0) (count (add n 1) (add n i)) (i))
        
        loop => Int -> Int
        loop = (x => Int -> Int \ if (sub x 100) (trace x (loop (add x 1))) 1)

        incr => Int -> Int
        incr = (x => Int -> Int \ add 1 x)
        
        incr2 => Int -> Int
        incr2 = (x => Int -> Int \ incr (add 1 x))
    """.trimIndent()

    var variables: VariableSet = standardLibrary + fullParse(test, standardLibrary.types)
    
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
                val definition = fullParseDefinition(realInput, variables.types)
                 variables += definition // TODO adding this to this only works once
            } else if(input.startsWith(":t")) {
                // :t 12
                // 01234
                //  ^\-/
                val realInput = input.substring(2).trim()
                val value = fullParseValue(realInput, variables.types)//.evaluate(realState)
                if(debug) println(value)
                println((if(realInput.length > 50) "*" else realInput) + " => " + value.type().toShowString())
            /*} else if(input.startsWith(":p")) {
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
            */} else {
                // it's just a statement
                var value = fullParseValue(input, variables.types)
                variables = variables +  ActualVariable(name = "last",value = value)
                if(debug) println(value.toString("","value"))
                var end = value.evaluate(VariableCollection(emptySet(), variables))

                if(end is InstantValue) {
                    println(end.value)
                } else {
                    println((if(input.length > 50) "*" else input) + " => " + end.type().restructure().toShowString())
                }
            }
        }catch(e: Exception) {
            e.printStackTrace()
            System.out.flush()
            System.err.flush()
        }
    }
}