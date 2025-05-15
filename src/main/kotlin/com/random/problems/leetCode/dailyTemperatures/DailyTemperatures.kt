package com.random.problems.leetCode.dailyTemperatures

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(73, 74, 75, 71, 69, 72, 76, 73) to intArrayOf(1, 1, 4, 2, 1, 1, 0, 0),
        intArrayOf(30, 40, 50, 60) to intArrayOf(1, 1, 1, 0),
        intArrayOf(30,60,90) to intArrayOf(1,1,0)
    )

    input.forEach {
        val temperatures = it.first
        it.test { Solution2().dailyTemperatures(temperatures) }
    }
}