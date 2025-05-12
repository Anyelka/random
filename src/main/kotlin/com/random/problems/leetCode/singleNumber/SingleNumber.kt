package com.random.problems.leetCode.singleNumber

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(2,2,1) to 1,
        intArrayOf(4,1,2,1,2) to 4,
        intArrayOf(1) to 1
    )

    input.forEach {
        val nums = it.first
        it.test { Solution1().singleNumber(nums) }
    }
}