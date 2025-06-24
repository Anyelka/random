package com.random.problems.leetCode.combinationSum

import com.random.util.testWithSet

fun main() {
    val input = listOf(
        (intArrayOf(2,3,6,7) to 7) to listOf(listOf(2,2,3), listOf(7)),
        (intArrayOf(2,3,5) to 6) to listOf(listOf(2,2,2,2), listOf(2,3,3), listOf(3,5)),
        (intArrayOf(2) to 1) to emptyList<List<Int>>()
    )

    input.forEach {
        val (candidates, target) = it.first
        it.testWithSet { Solution1().combinationSum(candidates, target) }
    }
}