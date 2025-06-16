package com.random.problems.leetCode.swimInRisingWater

import java.util.*
import kotlin.Comparator

class Solution1 {
    fun swimInWater(grid: Array<IntArray>): Int {
        return swimInWater1(grid)
    }

    // 1. MinHeap / Djikstra
    //  TC: O(n * m)
    //  SC: O(n * m)
    private fun swimInWater1(grid: Array<IntArray>): Int {
        val (n, m) = grid.lastIndex to grid[0].lastIndex
        val minHeap = PriorityQueue<Pair<Int, Pair<Int, Int>>>(Comparator.comparing { it.first })
        val visited = mutableSetOf<Pair<Int, Int>>()
        minHeap.offer(grid[0][0] to (0 to 0))
        visited.add(0 to 0)
        var minTime = 0
        while (minHeap.isNotEmpty()) {
            val (time, position) = minHeap.poll()
            minTime = maxOf(minTime, time)
            if (position == n to m) return minTime
            visited.add(position)
            for (i in -1..1) {
                for (j in -1..1) {
                    if ((i + j) % 2 == 0) continue
                    val (x, y) = (position.first + i to position.second + j)
                    if (x !in 0..n || y !in 0..m) continue
                    if (!visited.contains(x to y)) minHeap.offer(grid[x][y] to (x to y))
                }
            }
        }
        return 0
    }
}