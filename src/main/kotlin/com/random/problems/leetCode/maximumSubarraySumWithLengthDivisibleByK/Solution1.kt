package com.random.problems.leetCode.maximumSubarraySumWithLengthDivisibleByK

class Solution1 {
    fun maxSubarraySum(nums: IntArray, k: Int): Long {
        val n = nums.size
        val prefixSum = LongArray(n + 1)
        for (i in nums.indices) {
            prefixSum[i + 1] = prefixSum[i] + nums[i]
        }

        var result = Long.MIN_VALUE
        repeat(k) { start ->
            var current = if (start + k <= n) prefixSum[start + k] - prefixSum[start] else Long.MIN_VALUE
            result = maxOf(result, current)
            for (i in (start + 2 * k)..n step k) {
                val sum = prefixSum[i] - prefixSum[i - k]
                current = maxOf(current + sum, sum)
                result = maxOf(result, current)
            }
        }

        return result
    }
}