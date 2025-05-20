package com.random.problems.leetCode.longestIncreasingPath

class Solution1 {
    //  1. DP with tabulation
    //  - 2 dimensional DP with storing i and j for starting square
    //      TC:     O(m * n)
    //      SC:     O(m * n)
    fun longestIncreasingPath(matrix: Array<IntArray>): Int {
        val dp = Array(matrix.size) { IntArray(matrix[0].size) { 0 } }

        var max = 0
        fun isOutOfBounds(i: Int, j: Int) = i < 0 || i > matrix.lastIndex || j < 0 || j > matrix[0].lastIndex

        fun calculateLIPStartingFrom(i: Int, j: Int): Int {
            if(dp[i][j] != 0) return dp[i][j]
            var result = 1
            for(dirI in -1..1) {
                for(dirJ in -1..1) {
                    if((dirI + dirJ) % 2 == 0) continue
                    val (nextI, nextJ) = (i + dirI) to (j + dirJ)
                    if(isOutOfBounds(nextI, nextJ) || matrix[nextI][nextJ] <= matrix[i][j]) continue
                    result = maxOf(result, 1 + calculateLIPStartingFrom(nextI, nextJ))
                }
            }
            dp[i][j] = result
            max = maxOf(max, result)
            return result
        }

        for(i in matrix.indices) {
            for(j in matrix[i].indices) {
                calculateLIPStartingFrom(i,j)
            }
        }

        return max
    }
}