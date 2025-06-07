package com.random.problems.leetCode.subsets

import com.random.util.testWithSet

fun main() {
    val input = listOf(
        intArrayOf(1,2,3) to listOf(listOf(),listOf(1),listOf(2),listOf(1,2),listOf(3),listOf(1,3),listOf(2,3),listOf(1,2,3)),
        intArrayOf(0) to listOf(listOf(), listOf(0)),
        intArrayOf() to listOf(listOf())
    )


    input.forEach {
        val nums = it.first
        it.testWithSet { Solution2().subsets(nums) }
    }
}