package com.random.problems.leetCode.jumpGame2

import com.random.problems.adventOfCode.twentyFour.util.runAndLogTime
import com.random.util.isCorrectString

fun main() {
    val input = listOf(
        intArrayOf(2,1) to 1,
        intArrayOf(2,3,1,1,4) to 2,
        intArrayOf(2,3,0,1,4) to 2,
        intArrayOf(5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5) to 5,
        intArrayOf(5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6) to 3
    )

    input.forEach { runAndLogTime { test(it) } }
}

fun test(it: Pair<IntArray, Int>) {
    val result = Solution1().jump(it.first)
    println("Result for ${it.first.contentToString()}: $result - ${isCorrectString(result, it.second)}")
}
