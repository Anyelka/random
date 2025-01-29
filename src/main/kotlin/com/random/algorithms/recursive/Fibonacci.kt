package com.random.algorithms.recursive

import com.random.problems.adventOfCode.twentyFour.util.runAndLogTime
import java.math.BigInteger

fun main() {
    val number = 10000

    val fibonacciMethods = listOf(
        //FibonacciNaiveRecursion,
        FibonacciMemoization,
        FibonacciTabulation,
        FibonacciConstantSpace
    )

    fibonacciMethods.forEach {
        runAndLogTime(Runnable {
            it.run(number)
        })
    }
}

// Performance of the different algorithms:
//  number = 10:    - standard: 10ms;   memoization: 100us;     tabulation: 1ms     constant-space: 50us
//           50:    - standard: 1m;     memoization: 100us;     tabulation: 1ms     constant-space: 50us
//          100:    - standard: N/A;    memoization: 5ms;       tabulation: 1ms     constant-space: 100us
//         1000:    - standard: N/A;    memoization: 10ms;      tabulation: 2ms     constant-space: 200us
//        10000:    - standard: N/A;    memoization: 15ms;      tabulation: 10ms    constant-space: 5ms
//       100000:    - standard: N/A;    memoization: StackOF;   tabulation: 150ms   constant-space: 100ms
//      1000000:    - standard: N/A;    memoization: StackOF;   tabulation: OOM     constant-space: 6s


object FibonacciNaiveRecursion : Fibonacci("Standard") {
    override fun getFibonacci(number: Int): BigInteger {
        return if (number == 1 || number == 2)
            BigInteger.ONE
        else getFibonacci(number - 1) + getFibonacci(number - 2)
    }
}

object FibonacciMemoization : Fibonacci("Memoization") {
    override fun getFibonacci(number: Int): BigInteger = getFibonacci(number, mutableMapOf())

    private fun getFibonacci(number: Int, memo: MutableMap<Int, BigInteger>): BigInteger {
        memo[number]?.let { return it }
        return if (number == 1 || number == 2)
            BigInteger.ONE
        else
            (getFibonacci(number - 1, memo) + getFibonacci(number - 2, memo))
                .also { memo[number] = it }
    }
}

object FibonacciTabulation : Fibonacci("Tabulation") {
    override fun getFibonacci(number: Int): BigInteger {
        val tab = mutableMapOf(0 to BigInteger.ZERO, 1 to BigInteger.ONE)
        for (i in 2..number) {
            tab[i] = tab[i - 1]!! + tab[i - 2]!!
        }
        return tab[number]!!
    }

}

object FibonacciConstantSpace : Fibonacci("Constant Space") {
    override fun getFibonacci(number: Int): BigInteger {
        var previous = BigInteger.ZERO
        var current = BigInteger.ONE
        for (i in 2..number) {
            val temp = previous
            previous = current
            current += temp
        }
        return current
    }
}

abstract class Fibonacci(val name: String) {
    fun run(number: Int) {
        val result = getFibonacci(number)
        println("$name solution - The $number. fibonacci number: $result")
    }

    abstract fun getFibonacci(number: Int): BigInteger
}