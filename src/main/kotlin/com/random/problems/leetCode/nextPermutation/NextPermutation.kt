package com.random.problems.leetCode.nextPermutation

import com.random.util.isCorrectStringWithExpected
import com.random.util.shortFormatArrayIfNeeded

fun main() {
    val input = listOf(
        intArrayOf(1,2,3) to intArrayOf(1,3,2),
        intArrayOf(3,2,1) to intArrayOf(1,2,3),
        intArrayOf(1,1,5) to intArrayOf(1,5,1),
        intArrayOf(1, 7, 6, 5, 7, 2) to intArrayOf(1, 7, 6, 7, 2, 5),
        intArrayOf(1, 9, 6, 5, 4, 2, 1) to intArrayOf(2, 1, 1, 4, 5, 6, 9),
        intArrayOf(1,1) to intArrayOf(1,1),
        intArrayOf(5,1,1) to intArrayOf(1,1,5),
        intArrayOf(1,4,5,8,7) to intArrayOf(1,4,7,5,8)
    )


    input.forEach {
        val nums = it.first.copyOf()
        val result = it.first.copyOf()
        Solution1().nextPermutation(result)
        println("Result for ${nums.contentToString()} is ${result.contentToString()} - ${isCorrectStringWithExpected(result, it.second)}")
    }
}