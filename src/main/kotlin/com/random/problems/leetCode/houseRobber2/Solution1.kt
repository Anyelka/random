package com.random.problems.leetCode.houseRobber2

class Solution1 {
    fun rob(nums: IntArray): Int {
        return tab(nums)
    }

    // 1. Memo
    //  TC: O(n * 2)
    //  SC: O(n * 2)
    private fun memo(nums: IntArray): Int {
        val memo = Array(nums.size) { Array<Int?>(2) { null } }

        fun maxRobbery(index: Int, firstTaken: Int): Int {
            if (index > nums.lastIndex) return 0
            if (memo[index][firstTaken] != null) return memo[index][firstTaken]!!
            if (index == nums.lastIndex) return if (firstTaken == 1) 0 else nums[index]
            val nextFirstTaken = if (index == 0) 1 else firstTaken
            return maxOf(nums[index] + maxRobbery(index + 2, nextFirstTaken), maxRobbery(index + 1, firstTaken))
                .also { memo[index][firstTaken] = it }
        }

        return maxRobbery(0, 0)
    }

    private fun tab(nums: IntArray): Int {
        val dp = Array(nums.size) { Array<Int?>(2) { null } }
        dp[0][0] = 0
        dp[0][1] = nums[0]

        for(i in 1..nums.lastIndex) {
            for(firstTaken in 0..1) {
                val take = if(!(i == nums.lastIndex && firstTaken == 1)) ((if(i > 1) (dp[i - 2][firstTaken] ?: 0) else 0) + nums[i]) else 0
                val leave = dp[i - 1][firstTaken] ?: 0
                dp[i][firstTaken] = maxOf(take, leave)
            }
        }

        return maxOf(dp[nums.lastIndex][0]!!, dp[nums.lastIndex][1]!!)
    }
}