package com.random.problems.leetCode.burstBalloons

class Solution3 {
    fun maxCoins(nums: IntArray): Int {
        return maxCoinsExtended(intArrayOf(1) + nums + intArrayOf(1))

    }

    fun maxCoinsExtended(nums: IntArray): Int {
        val dp = Array<IntArray>(nums.size) { IntArray(nums.size) { 0 } }

        fun fillMaxCoins(numbers: IntArray, start: Int, end: Int) : Int{
            if(dp[start][end] != 0) return dp[start][end]
            if(end < start) return 0
            var maxCoins = 0
            for(i in start..end) {
                // what happens if we pop nums[i] last
                val coins = numbers[start-1] * numbers[i] * numbers[end + 1]
                val currentMax = coins + fillMaxCoins(numbers, start, i - 1) + fillMaxCoins(numbers, i + 1, end)
                maxCoins = maxOf(maxCoins, currentMax)
            }
            dp[start][end] = maxCoins
            return maxCoins
        }
        fillMaxCoins(intArrayOf(1) + nums + intArrayOf(1), 1, nums.size - 2)
        return dp[1][nums.size - 2]
    }
}