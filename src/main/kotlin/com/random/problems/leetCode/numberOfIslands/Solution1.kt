package com.random.problems.leetCode.numberOfIslands

class Solution1 {
    fun numIslands(grid: Array<CharArray>): Int {
        return numIslands2(grid)
    }

    //  TC: O(m * n)
    //  SC: O(m * n)
    private fun numIslands1(grid: Array<CharArray>): Int {
        var islands = 0

        val visited = mutableSetOf<Pair<Int, Int>>()

        val queue = ArrayDeque<Pair<Int, Int>>()

        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (visited.contains(i to j) || grid[i][j] != '1') continue

                islands++
                queue.add(i to j)

                while (queue.isNotEmpty()) {
                    val (currI, currJ) = queue.removeFirst()
                    visited.add(currI to currJ)
                    // -1 0, 1 0, 0 -1, 0 1
                    for (dirI in -1..1) {
                        for (dirJ in -1..1) {
                            if ((dirI + dirJ) % 2 == 0) continue
                            val (nextI, nextJ) = (currI + dirI) to (currJ + dirJ)
                            if (nextI < 0 || nextI > grid.lastIndex ||
                                nextJ < 0 || nextJ > grid[0].lastIndex ||
                                grid[nextI][nextJ] != '1'
                            ) continue
                            if (!visited.contains(nextI to nextJ) && !queue.contains(nextI to nextJ)) queue.add(nextI to nextJ)
                        }
                    }
                }


                visited.add(i to j)
            }
        }
        return islands
    }

    //  TC: O(m * n)
    //  SC: O(m * n)
    private fun numIslands2(grid: Array<CharArray>): Int {
        var islands = 0

        fun dfs(i: Int, j: Int) {
            grid[i][j] = '0'
            for (dirI in -1..1) {
                for (dirJ in -1..1) {
                    if ((dirI + dirJ) % 2 == 0) continue
                    val (nextI, nextJ) = (i + dirI) to (j + dirJ)
                    if (nextI < 0 || nextI > grid.lastIndex ||
                        nextJ < 0 || nextJ > grid[0].lastIndex ||
                        grid[nextI][nextJ] != '1'
                    ) continue
                    dfs(nextI, nextJ)
                }
            }

        }

        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if(grid[i][j] == '1') {
                    islands++
                    dfs(i, j)
                }
            }
        }

        return islands
    }
}