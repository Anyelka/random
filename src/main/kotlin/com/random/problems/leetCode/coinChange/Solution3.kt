package com.random.problems.leetCode.coinChange

class Solution3 {
    fun coinChange(coins: IntArray, amount: Int): Int {
        if(amount == 0) return 0
        val dp = Array<Int?>(amount + 1) { null }.also { it[0] = 0 }
        for(i in 1..amount) {
            for(coin in coins) {
                if (i - coin >= 0) dp[i] = listOfNotNull(dp[i], dp[i - coin]?.plus(1)).minOrNull()
            }
        }
        return dp[amount] ?: -1
    }
}