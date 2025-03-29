package com.random.problems.leetCode.trappingRainWater

import com.random.util.test

fun main() {
    val input: List<Pair<IntArray, Int>> = listOf(
        intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1) to 6,
        intArrayOf(4,2,0,3,2,5) to 9,
        intArrayOf(4,2,3) to 1,
        TestInput1.getInput()
    )

    input.forEach {
        val height = it.first
        it.test { Solution1().trap(height) }
    }
}