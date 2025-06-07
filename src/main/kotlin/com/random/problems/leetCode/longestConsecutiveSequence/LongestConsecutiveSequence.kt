package com.random.problems.leetCode.longestConsecutiveSequence

import com.random.util.test

fun main () {
    val input = listOf(
        intArrayOf(100,4,200,1,3,2) to 4,
        intArrayOf(0,3,7,2,5,8,4,6,0,1) to 9,
        intArrayOf(1,0,1,2) to 3
    )

    input.forEach {
        val nums = it.first
        it.test { Solution1().longestConsecutive(nums) }
    }
}