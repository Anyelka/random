package com.random.problems.leetCode.maximumSubarray

class Solution2 {
    fun maxSubArray(nums: IntArray): Int {
        return maxSubArray1(nums)
    }

    // 1. keep track of current and max sum and always coerce sum to 0 if it is negative
    //      TC: O(n)
    //      SC: O(1)
    fun maxSubArray1(nums: IntArray): Int {
        var maxSum = nums[0]
        var sum = maxSum
        for(i in 1..nums.lastIndex) {
            sum = maxOf(sum, 0)
            sum += nums[i]
            maxSum = maxOf(maxSum, sum)
        }
        return maxSum
    }

}