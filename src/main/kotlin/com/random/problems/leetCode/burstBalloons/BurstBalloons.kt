package com.random.problems.leetCode.burstBalloons

import com.random.problems.adventOfCode.twentyFour.day12.measureTime
import com.random.problems.adventOfCode.twentyFour.util.runAndLogTime
import com.random.util.test
import kotlin.time.measureTime

fun main() {
    val input = listOf(
        intArrayOf(3,1,5,8) to 167,
        intArrayOf(1,5) to 10,
        intArrayOf(7,9,8,0,7,1,3,5,5,2,3) to 1654,
        intArrayOf(8,3,4,3,5,0,5,6,6,2,8,5,6,2,3,8,3,5,1,0,2) to 3394
    )

    input.forEach {
        val nums = it.first
        runAndLogTime { it.test { Solution1().maxCoins(nums) } }
    }
}