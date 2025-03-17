package com.random.problems.leetCode.jumpGame2

import kotlin.math.min

class Solution1 {
    fun jump(nums: IntArray): Int {
        return jumpTab(nums)
    }

    // 1. Naive recursive solution
    //      - start from position 0
    //          - go through the possible steps
    //              - take all the numbers between 1 and arr[i] and for each of them:
    //                  - check the jump count from the next tile and add 1
    //                  - save the jump count from next tile if it is the smallest from the options
    //
    //      Time complexity:    O(2^n)
    //      Space complexity:   O(n)
    //          - due to the recursion stack
    //
    private fun jumpNaiveRecursion(nums: IntArray): Int {
        return jumpNaiveRecursion(nums, 0)
    }

    private fun jumpNaiveRecursion(nums: IntArray, i: Int): Int {
        if(i == nums.size - 1) return 0

        var minStepsFromCurrent = nums.size
        for (j in 1..nums[i]) {
            if(i + j >= nums.size) break
            val stepsFromNext = jumpNaiveRecursion(nums, i + j) + 1
            if (stepsFromNext < minStepsFromCurrent) minStepsFromCurrent = stepsFromNext
        }
        return minStepsFromCurrent
    }

    // 2. Memoized solution
    //      same approach as the naive recursive one, but save the number of steps till the end for each of the
    //          positions
    //
    //      Time complexity:    O(n^2)
    //      Space complexity:   O(n)
    //
    private fun jumpMemo(nums: IntArray): Int {
        return jumpMemo(nums, 0, mutableMapOf())
    }

    private fun jumpMemo(nums: IntArray, i: Int, memo: MutableMap<Int, Int>): Int {
        if(i == nums.size - 1) return 0

        if(memo.containsKey(i)) return memo[i]!!

        var minStepsFromCurrent = nums.size
        for (j in 1..nums[i]) {
            if(i + j >= nums.size) break
            val stepsFromNext = jumpMemo(nums, i + j, memo) + 1
            if (stepsFromNext < minStepsFromCurrent) minStepsFromCurrent = stepsFromNext
        }
        memo[i] = minStepsFromCurrent
        return minStepsFromCurrent
    }

    // 3. Tabulated solution
    //      bottom-up approach: go through the tiles 1-by-1 and calculate the minimum jumps for each from the previous
    //          options
    //
    //      Time complexity: O(n^2)
    //      Space complexity: O(n)
    //
    private fun jumpTab(nums: IntArray): Int {
        val dp = mutableMapOf<Int, Int>().also { it[0] = 0 }

        for(i in nums.indices) {
            for(j in 1..nums[i]) {
                if(i + j > nums.size) break
                dp[i + j] = listOfNotNull(dp[i + j], dp[i]?.plus(1)).min()
            }
        }

        return dp[nums.size - 1]!!
    }

}