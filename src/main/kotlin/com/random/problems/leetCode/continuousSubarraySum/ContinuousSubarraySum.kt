package com.random.problems.leetCode.continuousSubarraySum

import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(23,2,4,6,7) to 6) to true,
        (intArrayOf(23,2,6,4,7) to 6) to true,
        (intArrayOf(23,2,6,4,7) to 13) to false,
        (intArrayOf(23,2,4,6,6) to 7) to true,
        (intArrayOf(0, 0) to 1) to true,
        (intArrayOf(0,1,0,3,0,4,0,4,0) to 5) to false,
        Input.getInput98()
    )

    input.forEach {
        val (nums, k) = it.first
        it.test { Solution1().checkSubarraySum(nums, k) }
    }
}