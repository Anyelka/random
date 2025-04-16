package com.random.problems.leetCode.longestIncreasingSubsequence

class Solution3 {
    fun lengthOfLIS(nums: IntArray): Int {
        return lengthOfLISBS(nums)
    }

    //  Solution 1: DP
    //      We store for each index what is the size of the longest increasing subsequence ending at that index
    //      We get the max of the values
    //
    //      TC:     O(n ^ 2)
    //      SC:     O(n)
    private fun lengthOfLISDP(nums: IntArray): Int {
        val dp = IntArray(nums.size) { 1 }
        for (i in nums.indices) {
            for (j in 0..i) {
                if (nums[j] < nums[i]) {
                    dp[i] = maxOf(dp[i], dp[j] + 1)
                }
            }
        }
        return dp.max()
    }

    // Solution 2: LIS algorithm
    // Save the numbers one-by-one into our lis by finding their insertion point with a binary search in the existing
    //  lis
    //  - iterate over each number
    //      - add them to the LIS:
    //          - if the LIS is empty - add them first
    //          - if the it's the biggest value in lis - add them at last position
    //          - otherwise = if they are between 0..lis.size-2 - add them at their insertion position:
    //              - the insertion position will be specified by binary search = it will be i, since it will overwrite the
    //                  number at i - if we overwrite the bigger number with a smaller one, we make more "room" for the next elements
    //                  to be inserted into our lis
    //
    //      TC:     O(n * log(n))
    //      SC:     O(n)
    fun lengthOfLISBS(nums: IntArray): Int {
        val lis = mutableListOf<Int>()
        for(num in nums) {
            if(lis.isEmpty()) {
                lis += num
            } else {
                var i = lis.binarySearch(num)
                if(i < 0) i = -i - 1
                if(i == lis.size) lis += num else lis[i] = num
            }
        }
        return lis.size
    }
}