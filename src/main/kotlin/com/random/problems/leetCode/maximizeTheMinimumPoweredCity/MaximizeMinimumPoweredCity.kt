package com.random.problems.leetCode.maximizeTheMinimumPoweredCity

import com.random.util.test

fun main() {
    val input = listOf(
        Triple(intArrayOf(1,2,4,5,0), 1, 2) to 5L,
        Triple(intArrayOf(4,4,4,4), 0, 3) to 4L,
        Triple(intArrayOf(0, 0, 5, 1, 0), 1, 3) to 2L,
        Triple(intArrayOf(0, 0, 5, 1, 0), 1, 4) to 2L,
        Triple(intArrayOf(0, 0, 5, 1, 0), 2, 3) to 8L,
        Triple(intArrayOf(1,2,7,1,0,2,5,6), 0, 0) to 0L,
        Triple(intArrayOf(1,2,7,1,0,2,5,6), 0, 3) to 1L,
        Triple(intArrayOf(1,2,7,1,0,2,5,6), 0, 4) to 2L,
        Triple(intArrayOf(1,2,7,1,0,2,5,6), 1, 3) to 4L,
        Triple(intArrayOf(1,2,7,1,0,2,5,6), 1, 4) to 5L,
        Triple(intArrayOf(1,2,7,1,0,2,5,6), 1, 5) to 5L,
        testCase26()
    )

    input.forEach {
        val (stations, r, k) = it.first
        it.test { Solution2().maxPower(stations, r, k) }
    }
}