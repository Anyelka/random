package com.random.problems.leetCode.coinChange

class Solution2 {
    fun coinChange(coins: IntArray, amount: Int): Int {
        return coinChange3(coins, amount) ?: -1
    }

    // Solution 1: naive recursive solution
    private fun coinChange1(coins: IntArray, amount: Int): Int {
        return coinChange11(coins, amount) ?: -1
    }

    private fun coinChange11(coins: IntArray, amount: Int): Int? {
        if(amount == 0) return 0
        var minCoinsNeeded: Int? = null
        for(coin in coins) {
            if(amount - coin < 0) break
            minCoinsNeeded = listOfNotNull(minCoinsNeeded, coinChange11(coins, amount - coin)?.plus(1)).minOrNull()
        }
        return minCoinsNeeded
    }

    // Solution 2: memoized solution
    private fun coinChange2(coins: IntArray, amount: Int): Int {
        return coinChange21(coins, amount, mutableMapOf()) ?: -1
    }

    private fun coinChange21(coins: IntArray, amount: Int, memo: MutableMap<Int, Int?>): Int? {
        if(amount == 0) return 0
        if(memo.containsKey(amount)) return memo[amount]
        var minCoinsNeeded: Int? = null
        for(coin in coins) {
            if(amount - coin < 0) continue
            minCoinsNeeded = listOfNotNull(minCoinsNeeded, coinChange21(coins, amount - coin, memo)?.plus(1)).minOrNull()
        }
        memo[amount] = minCoinsNeeded
        return minCoinsNeeded
    }

    // Solution 3: tabulated solution
    private fun coinChange3(coins: IntArray, amount: Int): Int {
        if(amount == 0) return 0
        val dp = Array<Int?>(amount+1) { null }.also { it[0] = 0 }

        for(i in 1..amount) {
            for(coin in coins) {
                if(i - coin < 0) continue
                dp[i] = listOfNotNull(dp[i], dp[i-coin]?.plus(1)).minOrNull()
            }
        }
        return dp[amount] ?: -1
    }
}