package com.random.problems.leetCode.targetSum

import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(1,1,1,1,1) to 3) to 5,
        (intArrayOf(1) to 1) to 1,
        (intArrayOf(2,6,3,2,4) to 5) to 3,
        (intArrayOf(2,6,3,2,4) to 12) to 0,
        (intArrayOf(2,6,3,2,4) to 13) to 2,
        (intArrayOf(1,6,4,7,2,4,11,2,3,7,6,3,9,3,4,10) to 14) to 1825
    )

    input.forEach {
        val (nums, target) = it.first
        it.test { Solution1().findTargetSumWays(nums, target) }
    }
}