package com.random.problems.leetCode.maximumSubarraySumWithLengthDivisibleByK

class Solution2 {
    fun maxSubarraySum(nums: IntArray, k: Int): Long {
        val n = nums.size
        val prefixSum = LongArray(n)
        for(i in nums.indices) {
            val prev = if(i != 0) prefixSum[i - 1] else 0
            prefixSum[i] = prev + nums[i]
        }

        var maxSum = Long.MIN_VALUE

        for(i in 0..<k) {
            // i = starting point
            // calculate the sum from i to i + k
            // currentSum = initial subarray with k length
            val prevSum = if(i == 0) 0 else prefixSum[i - 1]
            var currentSum = if(i + k <= n) prefixSum[i + k - 1] - prevSum else Long.MIN_VALUE
            maxSum = maxOf(maxSum, currentSum)

            var j = i + 2 * k - 1
            // j = ending point
            while(j < n) {
                // sum the addition subarray with k length
                val sum =  prefixSum[j] - prefixSum[j - k]

                // if we add up sum + currentSum, we get the current subarray with length divisible by K
                //  - we either take the whole subarray, or just the last k long part depending on which is bigger
                currentSum = maxOf(currentSum + sum, sum)
                maxSum = maxOf(maxSum, currentSum)

                j += k
            }
        }

        return maxSum
    }
}