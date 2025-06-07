package com.random.problems.leetCode.longestConsecutiveSequence

class Solution1 {
    fun longestConsecutive(nums: IntArray): Int {
        return longestConsecutive1(nums)
    }

    /*
        dp:
        iteration:  5
        value   1   0   2   2   0   1   0   2   2   0
        index   0   1   2   3   4   5   6   7   8   9
        iteration:  6: num = 4
        - dp[num] = dp[num - 1] + dp[num + 1] + 1 => dp[4] = 2 + 1 + 1
        V
        value   1   0   2   2   4   1   0   2   2   0
        index   0   1   2   3   4   5   6   7   8   9
        - dp[num - dp[num-1]] = dp[num] => dp[4-dp[3]] = dp[4-2] = dp[2] = 4
        V
        value   1   0   4   2   4   1   0   2   2   0
        index   0   1   2   3   4   5   6   7   8   9
        - dp[num + dp[num+1]] = dp[num] => dp[4+dp[5]] = dp[4+1] = dp[5] = 4
        V
        value   1   0   4   2   4   4   0   2   2   0
        index   0   1   2   3   4   5   6   7   8   9
    */

    //  1. Hashmap solution with islands
    //  TC: O(n)
    //  SC: O(n)
    private fun longestConsecutive1(nums: IntArray): Int {
        val dp = mutableMapOf<Int, Int>()
        var max = 0
        for (num in nums) {
            if (dp.contains(num)) continue
            val prev = dp[num - 1] ?: 0
            val next = dp[num + 1] ?: 0
            dp[num] = prev + 1 + next
            if (dp[num]!! > (dp[num - 1] ?: Int.MAX_VALUE)) dp[num - dp[num - 1]!!] = dp[num]!!
            if (dp[num]!! > (dp[num + 1] ?: Int.MAX_VALUE)) dp[num + dp[num + 1]!!] = dp[num]!!
            max = maxOf(max, dp[num]!!)
        }
        return max
    }
}