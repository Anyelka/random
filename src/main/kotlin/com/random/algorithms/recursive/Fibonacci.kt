package com.random.algorithms.recursive

import com.random.problems.adventOfCode.twentyFour.util.runAndLogTime
import java.math.BigInteger

fun main() {
    val number = 1000000

    val fibonacciMethods = listOf(
        //FibonacciStandard,
        //FibonacciMemoization,
        FibonacciTabulation
    )

    fibonacciMethods.forEach {
        runAndLogTime(Runnable {
            it.run(number)
        })
    }
}

// Performance of the different algorithms:
//  number = 10:    - standard: 10.463ms;           memoization: 116us;                     tabulation: 53.208us
//           50:    - standard: 2m 8.359123042s;    memoization: 109.333us;                 tabulation: 85.75us
//          100:    - standard: N/A;                memoization: 9.634292ms;                tabulation: 246.75us
//         1000:    - standard: N/A;                memoization: 10.247166ms;               tabulation: 597.875us
//        10000:    - standard: N/A;                memoization: 18.549625ms;               tabulation: 7.858250ms
//       100000:    - standard: N/A;                memoization: StackOverflowError;        tabulation: 243.053792ms
//      1000000:    - standard: N/A;                memoization: StackOverflowError;        tabulation: OutOfMemoryError


object FibonacciStandard: Fibonacci("Standard") {
    override fun getFibonacci(number: Int): BigInteger {
        return if(number == 0 || number == 1)
            BigInteger.ONE
        else getFibonacci(number - 1) + getFibonacci(number - 2)
    }
}

object FibonacciMemoization: Fibonacci("Memoization") {
    override fun getFibonacci(number: Int): BigInteger = getFibonacci(number, mutableMapOf())

    private fun getFibonacci(number: Int, memo: MutableMap<Int, BigInteger>): BigInteger {
        memo[number]?.let { return it }
        return if(number == 0 || number == 1)
            BigInteger.ONE
        else (getFibonacci(number - 1, memo) + getFibonacci(number - 2, memo))
            .also { memo[number] = it }
    }
}

object FibonacciTabulation: Fibonacci("Tabulation") {
    override fun getFibonacci(number: Int): BigInteger {
        val tab = mutableMapOf<Int, BigInteger>()
        for(i in 0..number) {
            tab[i] = if(i == 0 || i == 1)
                BigInteger.ONE
            else (tab[i-1]!! + tab[i-2]!!)
        }
        return tab[number]!!
    }

}

abstract class Fibonacci(val name: String) {
    fun run(number: Int) {
        val result = getFibonacci(number)
        println("$name solution - The $number. fibonacci number: $result")
    }

    abstract fun getFibonacci(number: Int): BigInteger
}