package com.random.problems.leetCode.jumpGame2

class Solution3 {
    fun jump(nums: IntArray): Int {
        return jumpGreedy(nums)
    }

    //  TC: O(n ^ 2)
    fun jumpTab(nums: IntArray): Int {
        val dp = Array<Int>(nums.size) { Int.MAX_VALUE }
        dp[0] = 0
        for(i in nums.indices) {
            for(step in 1..nums[i]) {
                if(i + step >= nums.size) break
                dp[i + step] = minOf(dp[i + step], dp[i] + 1)
            }
        }
        return dp[nums.size - 1]
    }

    //  TC: O(n)
    fun jumpGreedy(nums: IntArray): Int {
        var maxReachable = 0
        var nextSquare = 0
        var jumps = 0
        for(i in nums.indices) {
            maxReachable = maxOf(maxReachable, i + nums[i])
            if(i < nums.size - 1 && i == nextSquare) {
                nextSquare = maxReachable
                jumps++
            }
        }
        return jumps
    }
}