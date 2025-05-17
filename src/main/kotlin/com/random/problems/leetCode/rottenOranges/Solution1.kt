package com.random.problems.leetCode.rottenOranges

class Solution1 {
    fun orangesRotting(grid: Array<IntArray>): Int {
        return orangesRotting1(grid)
    }

    //      TC: O(m * n)
    //      SC: O(m * n)
    private fun orangesRotting1(grid: Array<IntArray>): Int {
        fun Pair<Int, Int>.isOnBoard() = first in 0..grid.lastIndex && second in 0..grid[0].lastIndex

        var remainingOranges = 0
        val rottenOranges = ArrayDeque<Pair<Int, Int>>()

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == 1) remainingOranges++
                if (grid[i][j] == 2) {
                    rottenOranges.add(i to j)
                    remainingOranges++
                }
            }
        }
        var time = 0
        while (rottenOranges.isNotEmpty()) {
            val nextRotten = mutableSetOf<Pair<Int, Int>>()
            while (rottenOranges.isNotEmpty()) {
                val current = rottenOranges.removeFirst()
                remainingOranges--

                for (i in -1..1) {
                    for (j in -1..1) {
                        if ((i + j) % 2 == 0) continue
                        val (nextI, nextJ) = current + (i to j)
                        if ((nextI to nextJ).isOnBoard() && grid[nextI][nextJ] == 1) {
                            grid[nextI][nextJ] = 2
                            nextRotten.add(nextI to nextJ)
                        }
                    }
                }
            }
            rottenOranges.addAll(nextRotten)
            if (nextRotten.isNotEmpty()) time++
        }
        return if (remainingOranges != 0) -1 else time
    }

    operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> = first + other.first to second + other.second
}