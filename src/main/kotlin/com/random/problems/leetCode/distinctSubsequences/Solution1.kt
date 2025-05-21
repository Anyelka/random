package com.random.problems.leetCode.distinctSubsequences

class Solution1 {
    fun numDistinct(s: String, t: String): Int {
        return numDistinct1(s, t)
    }

    //  1. Memoization
    //  TC: O(m * n)
    //  SC: O(m * n)
    private fun numDistinct1(s: String, t: String): Int {
        val dp = Array(s.length) { Array<Int?>(t.length) { null } }
        fun numDistinct(i: Int, j: Int): Int {
            if (j == t.length) return 1
            if (i == s.length) return 0
            if (dp[i][j] != null) return dp[i][j]!!
            var numDistinct = 0
            if (s[i] == t[j]) {
                numDistinct += numDistinct(i + 1, j + 1)
            }
            numDistinct += numDistinct(i + 1, j)
            dp[i][j] = numDistinct
            return numDistinct
        }

        return numDistinct(0, 0)
    }
}