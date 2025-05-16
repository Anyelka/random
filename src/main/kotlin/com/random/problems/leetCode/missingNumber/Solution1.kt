package com.random.problems.leetCode.missingNumber

class Solution1 {
    //  TC: O(n)
    //  SC: O(1)
    fun missingNumber(nums: IntArray): Int {
        var result = nums.size
        for(i in nums.indices) {
            result += i
            result -= nums[i]
        }
        return result
    }
}