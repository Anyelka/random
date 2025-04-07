package com.random.problems.leetCode.jumpGame2

class Solution2 {
    fun jump(nums: IntArray): Int {
        return jumpGreedy(nums)
    }

    private fun jumpTab(nums: IntArray): Int {
        val dp = IntArray(nums.size) { nums.size + 1 }
        dp[0] = 0

        for ((i, num) in nums.withIndex()) {
            for (j in 1..num) {
                if (i + j >= nums.size) break
                dp[i + j] = minOf(dp[i + j], dp[i] + 1)
            }
        }

        return dp[nums.size - 1]
    }

    // (3, 9, 7, 3, 1, 2, 1, 2, 2)
    //  0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18
    private fun jumpGreedy(nums: IntArray): Int {
        var position = 0
        var furthestReachable = 0
        var jumpCount = 0
        for(i in 0 until nums.size -1) {
            furthestReachable = maxOf(furthestReachable, i + nums[i])
            if(i == position) {
                jumpCount++
                position = furthestReachable
                if(position >= nums.size) break
            }
        }
        return jumpCount
    }
}