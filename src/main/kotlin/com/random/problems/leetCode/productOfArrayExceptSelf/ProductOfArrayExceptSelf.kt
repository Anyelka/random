package com.random.problems.leetCode.productOfArrayExceptSelf

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(1, 2, 3, 4) to intArrayOf(24, 12, 8, 6),
        intArrayOf(-1, 1, 0, -3, 3) to intArrayOf(0, 0, 9, 0, 0),
        intArrayOf(7, 2, 4, 1, 5) to intArrayOf(40, 140, 70, 280, 56),
        intArrayOf(1,-1) to intArrayOf(-1, 1)
    )

    input.forEach {
        val nums = it.first
        it.test { Solution1().productExceptSelf(nums) }
    }
}