package com.random.problems.leetCode.minCostToConnectAllPoints

import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf(intArrayOf(0,0),intArrayOf(2,2),intArrayOf(3,10),intArrayOf(5,2),intArrayOf(7,0)) to 20,
        arrayOf(intArrayOf(3,12),intArrayOf(-2,5),intArrayOf(-4,1)) to 18,
        arrayOf(intArrayOf(2,2),intArrayOf(2,4),intArrayOf(3,3)) to 4,
        arrayOf(intArrayOf(2,2),intArrayOf(2,4),intArrayOf(12,12),intArrayOf(12,14)) to 22,
        arrayOf(intArrayOf(2,2),intArrayOf(2,4),intArrayOf(12,2),intArrayOf(12,4)) to 14
    )

    input.forEach {
        val points = it.first
        it.test { Solution1().minCostConnectPoints(points) }
    }
}