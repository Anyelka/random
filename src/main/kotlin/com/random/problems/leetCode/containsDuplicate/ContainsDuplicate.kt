package com.random.problems.leetCode.containsDuplicate

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(1,2,3,1) to true,
        intArrayOf(1,2,3,4) to false,
        intArrayOf(1,1,1,3,3,4,3,2,4,2) to true
    )

    input.forEach {
        val nums = it.first
        it.test { Solution().containsDuplicate(nums) }
    }

}