package com.random.problems.leetCode.missingNumber

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(3,0,1) to 2,
        intArrayOf(0,1) to 2,
        intArrayOf(9,6,4,2,3,5,7,0,1) to 8
    )

    input.forEach {
        val nums = it.first
        it.test { Solution1().missingNumber(nums) }
    }
}