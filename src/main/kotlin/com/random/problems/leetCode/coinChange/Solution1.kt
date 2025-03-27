package com.random.problems.leetCode.coinChange

import com.random.problems.adventOfCode.twentyFour.util.runAndLogTime

class Solution1 {
    fun runAll(input: Pair<IntArray, Int>) = solutions.runAll(input)

    fun coinChange(coins: IntArray, amount: Int) = TabulationQueue().coinChange(coins, amount)

    private val solutions = listOf(
        /*NaiveRecursive(),
        Memoization(),
        Tabulation(),*/
        TabulationQueue()
    )

    private fun List<Solution>.runAll(input: Pair<IntArray, Int>) {
        println("Input: coins = ${input.first.contentToString()}, amount = ${input.second}")
        this.forEach{ it.run(input) }
        println("--------")
    }
}


class NaiveRecursive: Solution("Naive Recursive") {
    override fun coinChange(coins: IntArray, amount: Int): Int = coinChange(coins, amount, mutableMapOf())
        ?: -1

    private fun coinChange(coins: IntArray, amount: Int, memo: MutableMap<Int, Int?>): Int? {
        if (amount == 0) {
            return 0
        }
        if (amount < 0) {
            return null
        }
        return coins.map {
            coinChange(coins, amount - it, memo)?.plus(1)
        }.filterNotNull().minOrNull()
    }
}

class Memoization: Solution("Memoization") {
    override fun coinChange(coins: IntArray, amount: Int): Int = coinChange(coins, amount, mutableMapOf())
        ?: -1

    private fun coinChange(coins: IntArray, amount: Int, memo: MutableMap<Int, Int?>): Int? {
        memo[amount]?.also {
            return it
        }
        if (amount == 0) {
            return 0
        }
        if (amount < 0) {
            return null
        }
        return coins.map {
            coinChange(coins, amount - it, memo)?.plus(1)
        }.filterNotNull().minOrNull()?.also {
            memo[amount] = it
        }
    }
}

class Tabulation: Solution("Tabulation") {
    override fun coinChange(coins: IntArray, amount: Int): Int = coinChangeTab(coins, amount)

    // With using a very high default value (amount + 1) and
    //      simple minOf() function, the runtime can be very low
    //      14 ms on LeetCode
    fun coinChangeTab(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { amount + 1 }
        dp[0] = 0

        for (i in 1..amount) {
            for(coin in coins) {
                if (i - coin >= 0) {
                    dp[i] = minOf(dp[i], dp[i - coin] + 1)
                }
            }
        }
        return if(dp[amount] != amount +1) dp[amount] else -1
    }
}

class TabulationQueue: Solution("Tabulation with Queue") {
    override fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { if (it == 0) 0 else amount + 1 }
        val queue = ArrayDeque<Int>(listOf(0))
        val visited = mutableListOf(0)
        while (!queue.isEmpty()) {
            val current = queue.removeFirst()
            for (coin in coins) {
                val next = current + coin
                if(next > 0 && next <= amount) {
                    dp[next] = minOf(dp[next], dp[current] + 1)
                }
                // The next > 0 is only needed, because if we reach the max Int value,
                //      the next = current + coin can overflow and turn negative
                if (next > 0 && next <= amount && !visited.contains(next)) {
                    queue.add(next)
                    visited.add(next)
                }
            }
        }
        return if(dp[amount] != amount +1) dp[amount] else -1
    }
}

abstract class Solution(val name: String) {
    fun run(input: Pair<IntArray, Int>) {
        runAndLogTime(
            Runnable {  runAndLogResult(input)  }
        )
        /*runAndLogResult(input)*/
    }

    fun runAndLogResult(input: Pair<IntArray, Int>) {
        val result = coinChange(input.first, input.second)
        println("${name} Solution: ${result}")
    }

    abstract fun coinChange(coins: IntArray, amount: Int): Int
}