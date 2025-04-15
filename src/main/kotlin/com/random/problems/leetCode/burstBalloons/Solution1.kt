package com.random.problems.leetCode.burstBalloons

class Solution1 {
    fun maxCoins(nums: IntArray): Int {
        return maxCoinsDPFun(nums)
        //return maxCoinsMemo(nums)
    }

    // 1. Solution: Memoization
    //
    //      TC:     O(n ^ 3)
    //      SC:     O(n ^ 2)
    private fun maxCoinsMemo(nums: IntArray): Int {
        val memo = mutableMapOf<String, Int>()
        return maxCoinsMemo(nums, memo)
    }

    private fun maxCoinsMemo(nums: IntArray, memo: MutableMap<String, Int>): Int {
        val numsKey = nums.joinToString(",")
        if (memo.containsKey(numsKey)) {
            return memo[numsKey]!!
        }
        var max = 0
        for ((i, num) in nums.withIndex()) {
            val left = if (i == 0) 1 else nums[i - 1]
            val right = if (i == nums.size - 1) 1 else nums[i + 1]
            val product = left * num * right
            val nextNums = nums.withIndex().filter { (index, it) -> index != i }.map { it.value }.toIntArray()

            max = maxOf(max, product + maxCoinsMemo(nextNums, memo))
        }
        memo[numsKey] = max
        return max
    }

    // 2. Solution: DP
    //  the subproblem is going to be an array from left to right
    //      we add two 1s to the edges of the array to handle the cases on the edge
    //      we check each element in the array and calculate how much it costs to pop that element LAST
    //          - if we pop it last, the price will be popping it + popping the two whole subarrays to the left and to
    //              the right of it - these two can be popped independently, because they do not overlap at all
    //
    //      TC:     O(n ^ 3)
    //      SC:     O(n ^ 2)

    fun maxCoinsDP(nums: IntArray): Int {
        val numbers = Array(nums.size + 2) { 1 }
        nums.withIndex().forEach { (i, num) -> numbers[i + 1] = num }

        val dp = mutableMapOf<Pair<Int, Int>, Int>()

        fun maxCoinsBetween(numbers: Array<Int>, left: Int, right: Int): Int {
            if (left > right) return 0
            if (dp.containsKey(left to right)) return dp[left to right]!!
            // we go through each element and get the cost for popping it last =
            for (i in left..right) {
                // if the current is popped last, it will always pop with the two neighboring values on the side of the
                //      subarray
                val currentPopCoins = numbers[left - 1] * numbers[i] * numbers[right + 1]
                // the cost of popping a balloon last is the cost of popping it + the max cost of popping the two subarrays
                //      on each side
                val maxCoinsWhenCurrentPoppedLast =
                    currentPopCoins + maxCoinsBetween(numbers, left, i - 1) + maxCoinsBetween(numbers, i + 1, right)
                dp[left to right] = maxOf(dp[left to right] ?: 0, maxCoinsWhenCurrentPoppedLast)
            }
            return dp[left to right]!!
        }

        return maxCoinsBetween(numbers, 1, numbers.size - 2)
    }

    // DP with more functional code
    fun maxCoinsDPFun(nums: IntArray): Int {
        val numbers = intArrayOf(1) + nums + intArrayOf(1)
        val dp = mutableMapOf<Pair<Int, Int>, Int>()

        fun maxCoinsBetween(left: Int, right: Int): Int {
            if (left > right) return 0
            if (dp.containsKey(left to right)) return dp[left to right]!!
            (left..right)
                .map { numbers[left - 1] * numbers[it] * numbers[right + 1] +
                        maxCoinsBetween(left, it - 1) +
                        maxCoinsBetween(it + 1, right) }
                .forEach { dp[left to right] = maxOf(dp[left to right] ?: 0, it) }
            return dp[left to right]!!
        }

        return maxCoinsBetween(1, numbers.size - 2)
    }

}