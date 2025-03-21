package com.random.problems.leetCode.longestIncreasingSubsequence

class Solution1 {
    fun lengthOfLIS(nums: IntArray) = lengthOfLISRec(nums)

    // 1. Naive recursive solution
    //
    //      check the longest increasing subsequence starting from each number in the array:
    //          - iterate over the numbers and get LIS for each of them
    //              - if we reached the end of array return 0 as we don't increase the length of LIS
    //              - go through each number after the current one
    //                  - if the next num is greater than current, calculate LIS from next one
    //              - get the max of the LISs calculated
    //          - get the max of LIS values for the numbers
    //
    //      Time complexity:    O(n^3)
    //      Space complexity:   O(n)
    //          - because of the recursive call stack
    //
    private fun lengthOfLISRec(nums: IntArray) = nums.indices.maxOfOrNull { lisFromFunctional(it, nums) } ?: 1

    private fun lisFromLoop(start: Int, nums: IntArray): Int {
        if (start >= nums.size) return 0

        var lisLength = 1
        for (i in start + 1..<nums.size) {
            if (nums[i] > nums[start]) {
                lisLength = maxOf(lisLength, 1 + lisFromLoop(i, nums))
            }
        }
        return lisLength
    }

    private fun lisFromFunctional(start: Int, nums: IntArray): Int {
        if (start >= nums.size) return 0

        return (start + 1..<nums.size)
            .filter { nums[it] > nums[start] }
            .map { 1 + lisFromFunctional(it, nums) }
            .maxByOrNull { it } ?: 1
    }

    // 2. Memoization:
    //      same as the naive recursive, but save the LIS for each index (the solution for each subproblem)
    //
    //      Time complexity:    O(n^2)
    //      Space complexity:   O(n)
    //
    private fun lengthOfLISMemo(nums: IntArray): Int {
        val memo = mutableMapOf<Int, Int>()
        return nums.indices.maxOfOrNull { lisFromMemo(it, nums, memo) } ?: 1
    }

    private fun lisFromMemo(start: Int, nums: IntArray, memo: MutableMap<Int, Int>): Int {
        if (start == nums.size - 1) return 1
        if (memo.containsKey(start)) return memo[start]!!

        for (i in start + 1..<nums.size) {
            if (nums[i] > nums[start]) {
                memo[start] = maxOf(memo[start] ?: 1, 1 + lisFromMemo(i, nums, memo))
            }
        }

        return memo[start] ?: 1
    }


    // 3. Tabulation:
    //  Bottom-up approach
    //
    //      Time complexity:    O(n^2)
    //      Space complexity:   O(n)
    //
    private fun lengthOfLISTab(nums: IntArray): Int {
        val dp = IntArray(nums.size) { 1 }

        var lisLength = 0

        for(i in nums.indices) {
            for(j in 0 until i) {
                if(nums[i] > nums[j]) {
                    dp[i] = maxOf(dp[i], 1 + dp[j])
                }
            }
            lisLength = maxOf(lisLength, dp[i])
        }

        return lisLength
    }

}