package com.random.problems.leetCode.houseRobber2

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(2,3,2) to 3,
        intArrayOf(1,2,3,1) to 4,
        intArrayOf(1,2,3) to 3,
        intArrayOf(114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240) to 4077
    )

    input.forEach {
        val nums = it.first
        it.test { Solution1().rob(nums) }
    }
}