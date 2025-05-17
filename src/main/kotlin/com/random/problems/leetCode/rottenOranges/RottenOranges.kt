package com.random.problems.leetCode.rottenOranges

import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf(intArrayOf(2,1,1),intArrayOf(1,1,0),intArrayOf(0,1,1)) to 4,
        arrayOf(intArrayOf(0,2)) to 0,
        arrayOf(intArrayOf(2,1,1),intArrayOf(0,1,1),intArrayOf(1,0,1)) to -1,
        arrayOf(intArrayOf(2,1,1),intArrayOf(1,1,0),intArrayOf(0,1,2)) to 2,
        arrayOf(intArrayOf(1),intArrayOf(2)) to 1,
        arrayOf(intArrayOf(2,2),intArrayOf(1,1),intArrayOf(0,0),intArrayOf(2,0)) to 1
    )

    input.forEach {
        val grid = it.first
        it.test { Solution1().orangesRotting(grid) }
    }
}