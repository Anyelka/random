package com.random.problems.leetCode.burstBalloons

class Solution2 {
    fun maxCoins(nums: IntArray): Int {
        //  7   5   2   2   6   3
        //  we need to define a subproblem
        //  -> calculate the max coins by maxing the results of the subproblems
        //  subproblem: if we burst the i-th balloon LAST:
        //  i = 3
        //  7   5   2   2   6   3
        //  0   1   2   3   4   5
        // if we pop i=3 LAST:
        //       (1) * [3] * (1)
        // [7   5   2] (2) [6   3]
        // both the left subarray and the right subarray will be popped BEFORE the [3] subarray
        //  the will be popped INDEPENDENTLY
        //      -> we can calculate their max popping value by themselves
        //      -> pad them on the side with nums[i]
        //  - the poppin value of subarray 1:
        //  (1) [7  5   2]  (2)
        //  - the poppin value of subarray 2:
        //  (2) [6  3]  (1)
        // TO GET THE POPPING VALUE OF A (SUB)ARRAY = we iterate over the elements and calculate the max cost of popping each baloon LAST:
        //      the cost of popping a balloon last at position = i
        //      = THE SUM OF:
        //      - popping that balloon last = 1 * nums[i] * 1 = num[i]
        //      - max popping value of left subarray = maxCoins(nums<0-(i-1)>)
        //      - max popping value of right subarray = maxCoins(nums<(i-1)-end>)

        val extendedNums = intArrayOf(1) + nums + 1

        val dp = Array(extendedNums.size) { Array<Int?>(extendedNums.size) { null } }

        fun maxCoins(nums: IntArray, start: Int, end: Int): Int {
            if (start > end) return 0
            if(dp[start][end] != null) return dp[start][end]!!
            var maxCoins = 0
            for (i in start..end) {
                val maxCoinsByPoppingILast = (nums[start - 1] * nums[i] * nums[end + 1]) +
                        maxCoins(nums, start, i - 1) +
                        maxCoins(nums, i + 1, end)
                maxCoins = maxOf(maxCoins, maxCoinsByPoppingILast)
            }
            dp[start][end] = maxCoins
            return maxCoins
        }

        return maxCoins(extendedNums, 1, extendedNums.size - 2)
    }
}