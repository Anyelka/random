package com.random.algorithms.recursive

import com.random.problems.adventOfCode.twentyFour.util.runAndLogTime
import java.math.BigInteger
import kotlin.random.Random

fun main() {
    //val inputData = listOf(100000)
    val inputData = InputDataGenerator.generateData(1000, 100000)

    val methods = listOf(
        //SimpleRecursive,
        //SimpleLoop,
        //Memoization,
        Tabulation
    )

    methods.forEach { method ->
        runAndLogTime(Runnable {
            method.run(inputData)
        })
    }
}

// Performance:
//  - for 1 number:
//      - number = 10       - Simple Recursive:     10ms     Simple loop:    100us   Memoization: 80us     Tabulation: 100us
//      -         100       - Simple Recursive:     10ms     Simple loop:    300us   Memoization: 300us    Tabulation: 300us
//      -         100       - Simple Recursive:     15ms     Simple loop:    2ms     Memoization: 2ms      Tabulation: 2ms
//      -        1000       - Simple Recursive:     90ms     Simple loop:    55ms    Memoization: 55ms     Tabulation: 55ms
//      -       10000       - Simple Recursive:  StackOF     Simple loop:    4s      Memoization: StackOF  Tabulation: 7s
//
// For multiple numbers: the memoized and tabulated solutions use a shared memo for the different solutions
//
//  - for 100 numbers:
//      - range = 0-1000    - Simple Recursive:     25ms     Simple loop:    15ms    Memoization: 1ms      Tabulation: 1ms
//      - range = 0-10000   - Simple Recursive:     1s       Simple loop:    1s      Memoization: 50ms     Tabulation: 130ms
//      - range = 0-100000  - Simple Recursive:  StackOF     Simple loop:    StackOF Memoization: StackOF  Tabulation: 30s
//  - for 1000 numbers:
//      - range = 0-1000    - Simple Recursive:    100ms     Simple loop:    100ms   Memoization: 1ms       Tabulation: 3ms
//      - range = 0-10000   - Simple Recursive:    10s       Simple loop:    10s     Memoization: 40ms      Tabulation: 230ms
//      - range = 0-100000  - Simple Recursive:  StackOF     Simple loop:    20min   Memoization: StackOF   Tabulation: 10s

abstract class Factorial(val name: String) {
    fun run(numbers: List<Int>) {
        println("$name method - Calculating the factorial of ${numbers.size} numbers:")
        val memo = mutableMapOf<Int, BigInteger>()
        numbers.forEach {
            val result = factorial(it, memo)
            //println("The factorial of $it is: $result")
        }
    }

    abstract fun factorial(number: Int, memo: MutableMap<Int, BigInteger>): BigInteger
}

object SimpleRecursive : Factorial("Simple recursive") {
    override fun factorial(number: Int, memo: MutableMap<Int, BigInteger>): BigInteger {
        return if (number <= 0)
            BigInteger.ONE
        else number.toBigInteger() * factorial(number - 1, memo)
    }

}

object SimpleLoop : Factorial("Simple loop") {
    override fun factorial(number: Int, memo: MutableMap<Int, BigInteger>): BigInteger {
        var result = BigInteger("1")
        for (i in 1..number) {
            result = result.times(i.toBigInteger())
        }
        return result
    }
}

object Memoization : Factorial("Memoization") {
    override fun factorial(number: Int, memo: MutableMap<Int, BigInteger>): BigInteger {
        memo[number]?.let { return it }
        return if (number <= 0)
            BigInteger.ONE
        else (number.toBigInteger() * factorial(number - 1, memo))
            .also { memo[number] = it }
    }
}

object Tabulation : Factorial("Tabulation") {
    override fun factorial(number: Int, memo: MutableMap<Int, BigInteger>): BigInteger {
        memo[0] = BigInteger.ONE
        memo[1] = BigInteger.ONE
        memo[number]?.let { return it }
        for (i in 1..number) {
            memo[i] = i.toBigInteger() * memo[i - 1]!!
        }
        return memo[number]!!
    }
}

object InputDataGenerator {
    fun generateData(size: Int, max: Int): List<Int> {
        return List(size) { Random.nextInt(0, max) }
    }
}
