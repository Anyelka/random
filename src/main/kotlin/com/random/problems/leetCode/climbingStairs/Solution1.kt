package com.random.problems.leetCode.climbingStairs

class Solution1 {
    fun climbStairs(n: Int): Int {
        return climbStairs2(n)
    }

    // 1. Solution: Tabulation with map
    //
    //  start from subproblem = 0
    //  get the solution for each level = each subproblem
    //  the solution of a subproblem will be:
    //      the number of ways you can get to subproblem - 2
    //          +
    //      the number of ways you can get to subproblem - 1
    //  get the solution for subproblem = n
    //
    //      Time complexity:    O(n)
    //      Space complexity:   O(n)
    //
    private fun climbStairs1(n: Int): Int {
        val dp = mutableMapOf<Int, Int>().also {
            it[0] = 1
            it[1] = 1
        }

        for (i in 2..n) {
            dp[i] = dp[i - 2]!! + dp[i - 1]!!
        }

        return dp[n]!!
    }

    // 2. Solution: Tabulation with two vars
    //
    //      Time complexity:    O(n)
    //      Space complexity:   O(1)
    //
    private fun climbStairs2(n: Int): Int {
        var first = 1
        var second = 1
        for (i in 2..n) {
            val (tempFirst, tempSecond) = (first to second)
            first = tempSecond
            second = tempFirst + tempSecond
        }

        return second
    }
}