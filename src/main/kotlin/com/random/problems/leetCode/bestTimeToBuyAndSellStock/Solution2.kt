package com.random.problems.leetCode.bestTimeToBuyAndSellStock

class Solution2 {
    fun maxProfit(prices: IntArray): Int {
        // 1. brute force solution: check the difference between every (i,j) pair with 2 loops
        return maxProfit1(prices)
        return maxProfit2(prices)
    }

    // 1. Single loop keep track of min
    //      TC: O(n)
    private fun maxProfit1(prices: IntArray): Int {
        var min = Int.MAX_VALUE
        var maxProfit = 0
        for (price in prices) {
            min = minOf(min, price)
            maxProfit = maxOf(maxProfit, price - min)
        }
        return maxProfit
    }

    // 2. Sliding window:
    //      TC: O(n)
    //      SC: O(1)
    private fun maxProfit2(prices: IntArray): Int {
        var l = 0
        var r = 1
        var maxProfit = 0
        while (r < prices.size) {
            if (prices[l] < prices[r]) {
                maxProfit = maxOf(maxProfit, prices[r] - prices[l])
            } else {
                l = r
            }
            r++
        }
        return maxProfit
    }
}