package com.random.problems.leetCode.minOperationsToConvertAllElementsToZero

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(0, 2) to  1,
        intArrayOf(3,1,2,1) to 3,
        intArrayOf(1,2,1,2,1,2) to 4,
        intArrayOf(1, 3, 7, 3, 5, 5, 3, 2, 1, 0, 1) to 6,
        intArrayOf(1, 3, 7, 3, 5, 3, 5, 2, 1, 0, 1) to 7,
        intArrayOf(3,4,2,1,2,1,4,1) to 6,
        getBigInput(),
    )

    input.forEach {
        val nums = it.first
        it.test { Solution1().minOperations(nums) }
    }
}