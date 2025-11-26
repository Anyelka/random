package com.random.problems.leetCode.pathsInMatrixWhoseSumIsDivisibleByK

import com.random.util.pow

class Solution1 {
    fun numberOfPaths(grid: Array<IntArray>, k: Int): Int {
        val dp = Array(grid.size) { Array(grid[0].size) { Array<Int?>(k) { null } } }

        fun numberOfPaths(i: Int, j: Int, rem: Int): Int {
            val nextRem = (rem + grid[i][j]) % k
            if(i == 0 && j == 0) {
                return if(nextRem == 0) 1 else 0
            }
            if(dp[i][j][rem] != null) return dp[i][j][rem ]!!

            val top = if(i > 0) numberOfPaths(i - 1, j, nextRem) else 0
            val left = if(j > 0) numberOfPaths(i, j - 1, nextRem) else 0
            dp[i][j][rem] = (top + left) % (10.pow(9) + 7)
            return dp[i][j][rem]!!
        }

        return numberOfPaths(grid.lastIndex, grid[0].lastIndex, 0)
    }
}