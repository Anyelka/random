package com.random.problems.leetCode.jumpGame

class Solution2 {
    // TC:  O(n)
    // SC:  O(1)
    fun canJump(nums: IntArray): Boolean {
        if(nums.size == 1) return true

        var next = 0
        var maxReachable = 0

        for(i in 0 until nums.size - 1) {
            maxReachable = maxOf(maxReachable, i + nums[i])
            if(i == next) next = maxReachable
            if(next >= nums.size - 1) return true
        }
        return false
    }
}