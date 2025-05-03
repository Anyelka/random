package com.random.problems.leetCode.containerWithMostWater

import com.random.util.test

fun main() {

    val input = listOf(
        intArrayOf(1,8,6,2,5,4,8,3,7) to 49,
        intArrayOf(1,1) to 1,
        getTestInput()
    )

    input.forEach {
        val height = it.first
        it.test { Solution1().maxArea(height) }
    }
}