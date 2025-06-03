package com.random.problems.leetCode.coinChange2

class Solution1 {
    fun change(amount: Int, coins: IntArray): Int {
        return change2(amount, coins)
    }


    fun change1(amount: Int, coins: IntArray): Int {
        val combinations = mutableSetOf<List<Int>>()

        fun change(amount: Int, combination: MutableList<Int>) {
            if(amount == 0) combinations.add(combination.sorted())
            for(coin in coins) {
                if(amount - coin < 0) continue
                change(amount - coin, (combination + listOf(coin)).toMutableList())
            }
        }

        change(amount, mutableListOf())

        return combinations.size
    }

    // Tabulation:
    //  TC: O(n * m)
    //  SC: O(n)
    fun change2(amount: Int, coins: IntArray): Int {
        val dp = IntArray(amount + 1)
        dp[0] = 1

        for (coin in coins) {
            for(i in coin..amount) {
                dp[i] += dp[i - coin]
            }
        }

        return dp[amount]
    }

}