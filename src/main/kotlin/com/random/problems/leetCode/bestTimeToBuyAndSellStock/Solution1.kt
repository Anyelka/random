package com.random.problems.leetCode.bestTimeToBuyAndSellStock

class Solution1 {
    fun maxProfit(prices: IntArray): Int {
        return maxProfit2(prices)
    }

    //  1. Two loops
    //  TC: O(n^2)
    //  SC: O(1)
    private fun maxProfit1(prices: IntArray): Int {
        var maxProfit = 0
        for (i in prices.indices) {
            for (j in i + 1 until prices.size) {
                maxProfit = maxOf(maxProfit, prices[j] - prices[i])
            }
        }
        return maxProfit
    }

    //  2. Keep track of min with 1 loop:
    //  TC: O(n)
    //  SC: O(1)
    private fun maxProfit2(prices: IntArray): Int {
        var min = Int.MAX_VALUE
        var maxProfit = 0

        for(i in prices) {
            maxProfit = maxOf(maxProfit, i - min)
            if(i < min) min = i
        }

        return maxProfit
    }
}