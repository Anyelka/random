package com.random.problems.leetCode.houseRobber

class Solution1 {
    fun rob(nums: IntArray): Int {
        return rob4(nums)
    }

    //  1. Naive recursive:
    //      TC: O(2 ^ n)
    //      SC: O(2 ^ n)
    private fun rob1(nums: IntArray) = rob1(nums, 0)

    private fun rob1(nums: IntArray, start: Int): Int {
        if(start > nums.size - 1) return 0
        return maxOf(nums[start] + rob1(nums, start + 2), rob1(nums, start + 1))
    }

    // 2. Memoization:
    //      TC: O(n)
    //      SC: O(n)
    private fun rob2(nums: IntArray) = rob2(nums, 0, mutableMapOf())

    private fun rob2(nums: IntArray, start: Int, memo: MutableMap<Int, Int>): Int {
        if(memo.containsKey(start)) return memo[start]!!
        if(start > nums.size - 1) return 0
        return maxOf(nums[start] + rob2(nums, start + 2, memo), rob2(nums, start + 1, memo))
            .also { memo[start] = it }
    }

    // 3. Tabulation
    //      TC: O(n)
    //      SC: O(n)
    private fun rob3(nums: IntArray): Int {
        val dp = IntArray(nums.size) { 0 }
        for(i in nums.indices) {
            val take = nums[i] + if (i > 1) dp[i - 2] else 0
            val leave = if(i > 0) dp[i - 1] else 0
            dp[i] = maxOf(take, leave)
        }
        return dp[nums.size - 1]
    }

    // 3. Tabulation
    //  - with constant space
    //      TC: O(n)
    //      SC: O(1)
    private fun rob4(nums: IntArray): Int {
        var first = 0
        var second = 0
        var i = 0
        while(i < nums.size) {
            first = maxOf(first + nums[i], second)
            i++
            if(i >= nums.size) break
            second = maxOf(second + nums[i], first)
            i++
        }
        return if(nums.size % 2 == 0) second else first
    }
}