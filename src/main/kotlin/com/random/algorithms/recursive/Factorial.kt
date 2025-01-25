package com.random.algorithms.recursive

import java.math.BigInteger
import kotlin.random.Random
import kotlin.time.measureTime

fun main() {
    val inputData = generateData(100000, 5000)

    var factorials = HashMap<Int, BigInteger>()

    val timeTaken = measureTime {
        inputData.forEach {
            println("calculating factorial of $it")

            val timeTaken = measureTime {
                // val result = factorial(it)
                val result = factorialMemoized(it, factorials)
                //print("       -> result: $result")
            }
            //print("         -> time taken: $timeTaken\n")
        }
    }
    println("Total time taken: $timeTaken")
}

fun generateData(size: Int, max: Int): List<Int> {
    return List(size) { Random.nextInt(0, max) }
}

fun factorial(number: Int): BigInteger {
    return factorialLoop(number)
}

//  For:    generateData(100000, 5000)
//          Total time taken: 2m 39.441653041s
fun factorialRecursively(number: Int): BigInteger {
    if (number <= 0) {
        return BigInteger("1")
    }
    return number.toBigInteger() * factorialRecursively(number - 1)
}

//  For:    generateData(100000, 5000)
//          Total time taken: 2m 35.139392667s
fun factorialLoop(number: Int): BigInteger {
    var result = BigInteger("1")
    for(i in 1.. number) {
        result = result.times(i.toBigInteger())
    }
    return result
}

fun factorialMemoized(number: Int, factorials: MutableMap<Int, BigInteger>): BigInteger {
    return factorialMemoizedLoop(factorials, number)
}

//  For:    generateData(100000, 5000)
//          Total time taken: 134.448583ms
private fun factorialMemoizedRecursively(factorials: MutableMap<Int, BigInteger>, number: Int): BigInteger {
    if (factorials[number] != null) {
        return factorials[number]!!
    }
    if (number <= 0) {
        return BigInteger("1")
    }
    val result = number.toBigInteger() * factorialMemoized(number - 1, factorials)
    factorials[number] = result
    return result
}

//  For:    generateData(100000, 5000)
//          Total time taken: 160.115ms
private fun factorialMemoizedLoop(factorials: MutableMap<Int, BigInteger>, number: Int): BigInteger {
    if (factorials[number] != null) {
        return factorials[number]!!
    }
    var result = BigInteger("1")
    for(i in 1.. number) {
        result = result.times(i.toBigInteger())
        factorials[i] = result
    }
    return result
}