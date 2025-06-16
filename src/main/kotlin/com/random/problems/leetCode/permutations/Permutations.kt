package com.random.problems.leetCode.permutations

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(1,2,3) to listOf(listOf(1,2,3),listOf(1,3,2),listOf(2,1,3),listOf(2,3,1),listOf(3,1,2),listOf(3,2,1)),
        intArrayOf(0,1) to listOf(listOf(0,1),listOf(1,0)),
        intArrayOf(1) to listOf(listOf(1))
    )

    input.forEach {
        val nums = it.first
        it.test { Solution1().permute(nums) }
    }
}