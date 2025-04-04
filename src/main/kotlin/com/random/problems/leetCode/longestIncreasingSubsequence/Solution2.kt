package com.random.problems.leetCode.longestIncreasingSubsequence

class Solution2 {
    fun lengthOfLIS(nums: IntArray): Int {
        // max length of subsequence where each element is LARGER than the previous one
        // we need to break down the problem to sub-problems
        // sub-problem can be: the max length of increasing sub-sequence STARTING or ENDING at i
        return lis1(nums)
    }

    private fun lis1(nums: IntArray): Int {
        val memo = mutableMapOf<Int, Int>()
        return nums.indices.maxOf { lisStartingFrom(nums, it, memo) }
    }

    fun lisStartingFrom(nums: IntArray, i: Int, memo: MutableMap<Int, Int>): Int {
        if(i >= nums.size) return 0
        if(memo.containsKey(i)) return memo[i]!!

        var maxLis = 1
        for(j in i+1..<nums.size) {
            if(nums[j] > nums[i]) maxLis = maxOf(maxLis, 1  + lisStartingFrom(nums, j, memo))
        }
        memo[i] = maxLis
        return maxLis
    }

}