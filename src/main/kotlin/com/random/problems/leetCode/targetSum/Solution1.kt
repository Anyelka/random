package com.random.problems.leetCode.targetSum

class Solution1 {
    // 1. Memoization
    //  TC: O(n * (2 * sum + 1))
    //  SC: O(n * sum)
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val memo = mutableMapOf<Pair<Int,Int>, Int>()

        fun findTargetSumWays(i: Int, sum: Int): Int {
            memo[i to sum ]?.let { return it }
            if(i > nums.lastIndex) return if(sum == target) 1 else 0
            val result = findTargetSumWays(i + 1, sum + nums[i]) + findTargetSumWays(i + 1, sum - nums[i])
            memo[i to sum] = result
            return result
        }

        return findTargetSumWays(0, 0)
    }
}