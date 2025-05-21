package com.random.problems.leetCode.bestTimeToBuyAndSellStockWithCooldown

class Solution1 {
    /*
    * 1,2,6,2,5,6
    *
    * */
    fun maxProfit(prices: IntArray): Int {
        return maxProfit2(prices)
    }

    //  1. Naive recursion
    //  TC: O(n ^ 2)
    //  SC: O(n)
    private fun maxProfit1(prices: IntArray): Int {
        fun maxProfit(start: Int): Int {
            if (start >= prices.lastIndex) return 0
            var min = Int.MAX_VALUE
            var maxProfit = 0
            for (i in start..prices.lastIndex) {
                if (min > prices[i]) {
                    min = prices[i]
                } else {
                    val nextProfit = (prices[i] - min) + maxProfit(i + 2)
                    maxProfit = maxOf(maxProfit, nextProfit)
                }
            }
            return maxProfit
        }

        return maxProfit(0)
    }

    //  2. Memoization:
    //  TC: O(n)
    //  SC: O(n)
    //      runtime on LC:  79 ms
    private fun maxProfit2(prices: IntArray): Int {
        val memo = IntArray(prices.size) { 0 }

        fun maxProfit(start: Int): Int {
            if (start >= prices.lastIndex) return 0
            memo[start].takeIf { it > 0}?.let { return it }
            var min = Int.MAX_VALUE
            var maxProfit = 0
            for (i in start..prices.lastIndex) {
                if (min > prices[i]) {
                    min = prices[i]
                } else {
                    val nextProfit = (prices[i] - min) + maxProfit(i + 2)
                    maxProfit = maxOf(maxProfit, nextProfit)
                }
            }
            memo[start] = maxProfit
            return maxProfit
        }

        return maxProfit(0)
    }
}