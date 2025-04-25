package com.random.problems.leetCode.uniquePaths

class Solution2 {
    fun uniquePaths(m: Int, n: Int): Int {
        val dp = Array(m) { i -> Array(n) { j -> if(i == 0 || j == 0) 1 else 0 } }

        for(i in 1 until m) {
            for(j in 1 until n) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1]
            }
        }
        return dp[m-1][n-1]
    }
}