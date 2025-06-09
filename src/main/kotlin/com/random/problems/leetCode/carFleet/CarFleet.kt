package com.random.problems.leetCode.carFleet

import com.random.util.test

fun main() {
    val input = listOf(
        Triple(12, intArrayOf(10,8,0,5,3), intArrayOf(2,4,1,1,3)) to 3,
        Triple(10, intArrayOf(3), intArrayOf(3)) to 1,
        Triple(100, intArrayOf(0,2,4), intArrayOf(4,2,1)) to 1,
        Triple(10, intArrayOf(3,5,7), intArrayOf(3,2,1)) to 1,
        Triple(10, intArrayOf(6,8), intArrayOf(3,2)) to 2
    )

    input.forEach {
        val (target, position, speed) = it.first
        it.test { Solution2().carFleet(target, position, speed) }
    }
}