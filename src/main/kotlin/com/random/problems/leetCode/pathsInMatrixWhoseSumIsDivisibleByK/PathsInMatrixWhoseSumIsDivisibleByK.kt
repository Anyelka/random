package com.random.problems.leetCode.pathsInMatrixWhoseSumIsDivisibleByK

import com.random.util.test

fun main() {
    val input = listOf(
        (arrayOf(intArrayOf(5,2,4),intArrayOf(3,0,5),intArrayOf(0,7,2)) to 3) to 2,
        (arrayOf(intArrayOf(0,0)) to 5) to 1,
        (arrayOf(intArrayOf(7,3,4,9), intArrayOf(2,3,6,2),intArrayOf(2,3,7,0)) to 1) to 10,
        (arrayOf(intArrayOf(7, 3, 8, 6), intArrayOf(1, 9, 2, 4), intArrayOf(5, 0, 3, 7), intArrayOf(8, 6, 1, 2)) to 5) to 4,
        //largeInput1Negative()
        largeInput2()
    )

    input.forEach {
        val (grid, k) = it.first
        it.test { Solution1().numberOfPaths(grid, k) }
    }
}