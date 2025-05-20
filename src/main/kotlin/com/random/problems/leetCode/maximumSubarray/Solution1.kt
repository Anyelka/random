package com.random.problems.leetCode.maximumSubarray

class Solution1 {
    fun maxSubArray(nums: IntArray): Int {
        return maxSubArray4(nums)
    }

    //  1. Memoization
    //  Exceeds memory limit !!!
    //  TC: O(n ^ 2)
    //  SC: O(n ^ 2)
    private fun maxSubArray1(nums: IntArray): Int {
        val memo = Array(nums.size) { Array<Int?>(nums.size) { null } }
        var max = Int.MIN_VALUE
        fun calculateMax(i: Int, j: Int): Int {
            if (i > j) return 0
            memo[i][j]?.let { return it }
            val one = nums[i] + calculateMax(i + 1, j)
            val two = nums[j] + calculateMax(i, j - 1)

            memo[i][j] = if (i == j) nums[i] else maxOf(one, two)
            max = maxOf(max, memo[i][j]!!)
            return memo[i][j]!!
        }
        calculateMax(0, nums.lastIndex)
        return max
    }

    //  2. Tabulation
    //  Exceeds memory limit !!!
    //  TC: O(n ^ 2)
    //  SC: O(n ^ 2)
    private fun maxSubArray2(nums: IntArray): Int {
        val dp = Array(nums.size) { Array<Int?>(nums.size) { null } }

        var max = nums[0]

        for(i in 0..<nums.lastIndex) {
            for(j in i..nums.lastIndex) {
                if(dp[i][j] != null) continue
                dp[i][j] = (if(i == j) 0 else dp[i][j-1]!!) + nums[j]
                max = maxOf(max, dp[i][j]!!)
            }
        }
        return max
    }

    //  3. Greedy: Kadane's Algorithm
    //  TC: O(n)
    //  SC: O(1)
    private fun maxSubArray3(nums: IntArray): Int {
        var currentMax = nums[0]
        var max = currentMax
        for(i in 1..nums.lastIndex) {
            currentMax = if(currentMax < 0 && nums[i] > currentMax) nums[i] else currentMax + nums[i]
            max = maxOf(max, currentMax)
            if(currentMax < 0) currentMax = 0
        }
        return max
    }

    //  4 Standard Kadane's Algorithm
    //  TC: O(n)
    //  SC: O(1)
    private fun maxSubArray4(nums: IntArray): Int {
        var currentMax = nums[0]
        var max = currentMax
        for(i in 1..nums.lastIndex) {
            currentMax = maxOf(nums[i], currentMax + nums[i])
            max = maxOf(max, currentMax)
        }
        return max
    }
}