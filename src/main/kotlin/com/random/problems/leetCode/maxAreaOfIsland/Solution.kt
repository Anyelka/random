package com.random.problems.leetCode.maxAreaOfIsland

class Solution {
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        return maxAreaOfIsland1(grid)
    }

    //  1. DFS
    //  TC: O(m * n)
    //  SC: O(m * n)
    private fun maxAreaOfIsland1(grid: Array<IntArray>): Int {
        var maxArea = 0

        val visited = mutableListOf<Pair<Int, Int>>()

        fun Pair<Int, Int>.isOutOfBoard() = !(first in grid.indices && second in grid[0].indices)

        fun dfs(i: Int, j: Int): Int {
            if ((i to j).isOutOfBoard() || visited.contains(i to j)) return 0
            visited.add(i to j)
            if (grid[i][j] != 1) return 0
            var length = 1
            for (y in -1..1) {
                for (x in -1..1) {
                    if ((y + x) % 2 == 0) continue
                    length += dfs(i + y, j + x)
                }
            }
            maxArea = maxOf(maxArea, length)
            return length
        }

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                dfs(i, j)
            }
        }

        return maxArea
    }
}