package com.random.problems.leetCode.findTheDuplicateNumber

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(1,3,4,2,2) to 2,
        intArrayOf(3,1,3,4,2) to 3,
        intArrayOf(3,3,3,3,3) to 3,
        intArrayOf(2,1,2) to 2,
        intArrayOf(2,1,2,3,2) to 2,
    )

    input.forEach {
        val nums = it.first
        it.test { Solution1().findDuplicate(nums) }
    }
}