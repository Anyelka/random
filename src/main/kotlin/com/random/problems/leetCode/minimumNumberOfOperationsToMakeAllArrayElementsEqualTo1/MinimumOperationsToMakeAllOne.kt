package com.random.problems.leetCode.minimumNumberOfOperationsToMakeAllArrayElementsEqualTo1

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(2,6,3,4) to 4,
        intArrayOf(2,10,6,14) to -1,
        intArrayOf(3,6,15,5) to 5,
        intArrayOf(5, 15, 1, 4, 1) to 3,
        intArrayOf(2,6,3) to 4,
        intArrayOf(5, 100, 25, 40) to -1
    )

    input.forEach {
        val nums = it.first
        it.test { Solution1().minOperations(nums) }
    }
}