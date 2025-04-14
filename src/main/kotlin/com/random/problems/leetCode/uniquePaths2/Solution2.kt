package com.random.problems.leetCode.uniquePaths2

class Solution2 {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        return uniquePathsMemo(obstacleGrid)
    }

    // Solution 1: Memoization
    private fun uniquePathsMemo(obstacleGrid: Array<IntArray>): Int {
        val dimensions = obstacleGrid.size - 1 to obstacleGrid[0].size - 1
        return uniquePathsTo(obstacleGrid, dimensions, mutableMapOf())
    }

    fun uniquePathsTo(obstacleGrid: Array<IntArray>, pos: Pair<Int, Int>, memo: MutableMap<Pair<Int,Int>, Int>): Int {
        if(memo.containsKey(pos)) return memo[pos]!!
        if(pos.first < 0 || pos.second < 0 ||
            pos.first >= obstacleGrid.size || pos.second >= obstacleGrid[0].size ||
            obstacleGrid[pos.first][pos.second] == 1) return 0
        if(pos.first == 0 && pos.second == 0) return 1

        return (uniquePathsTo(obstacleGrid, pos.up(), memo) + uniquePathsTo(
            obstacleGrid,
            pos.left(),
            memo
        )).also { memo[pos] = it }
    }

    fun Pair<Int,Int>.up() = first - 1 to second
    fun Pair<Int,Int>.left() = first to second - 1
}