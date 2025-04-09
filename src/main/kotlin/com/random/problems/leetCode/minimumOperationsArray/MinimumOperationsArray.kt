package com.random.problems.leetCode.minimumOperationsArray

import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(5,2,5,4,5) to 2) to 2,
        (intArrayOf(2,1,2) to 2) to -1,
        (intArrayOf(9,7,5,3) to 1) to 4
    )

    input.forEach {
        val (nums, k) = it.first
        it.test { Solution1().minOperations(nums, k) }
    }
}