package com.random.problems.leetCode.maximumSubarrayWithEqualProducts

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(1,2,1,2,1,1,1) to 5,
        intArrayOf(2,3,4,5,6) to 3,
        intArrayOf(1,2,3,1,4,5,1) to 5,
        intArrayOf(2,6) to 2
    )

    input.forEach {
        val nums = it.first
        it.test { Solution1().maxLength1(nums) }
    }
}