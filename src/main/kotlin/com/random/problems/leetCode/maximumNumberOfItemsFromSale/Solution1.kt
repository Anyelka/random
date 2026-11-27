package com.random.problems.leetCode.maximumNumberOfItemsFromSale

class Solution1 {
    fun maximumSaleItems(items: Array<IntArray>, budget: Int): Int {
        val dp = IntArray(budget + 1)
        val freeCopies = IntArray(items.size) { 0 }

        for(i in items.indices) {
            for(j in items.indices) {
                if(i != j && items[j][0] % items[i][0] == 0) freeCopies[i]++
            }
        }

        for(i in items.indices) {
            val price = items[i][1]
            // first copy: the one that receives additional copies
            // 0/1 Knapsack: it needs iterating BACKWARDS, so it will 'not see' previous states
            for(b in budget downTo price) {
                dp[b] = maxOf(dp[b], dp[b-price] + 1 + freeCopies[i])
            }
            // all other copies: only get those and do not receive additional copies
            // unbounded knapsack: can take any amount from 1 item: iterating FORWARDS, so earlier states represent fully taken states
            for(b in price..budget) {
                dp[b] = maxOf(dp[b], dp[b-price] + 1)
            }
        }

        return dp[budget]
    }
}