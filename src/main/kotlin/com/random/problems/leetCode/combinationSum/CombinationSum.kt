package com.random.problems.leetCode.combinationSum

import com.random.util.testWithSet

fun main() {
    val input = listOf(
        (intArrayOf(10, 1, 2, 7, 6, 1, 5) to 8) to listOf(listOf(1, 1, 6), listOf(1, 2, 5), listOf(1, 7), listOf(2, 6)),
        (intArrayOf(2, 5, 2, 1, 2) to 5) to listOf(listOf(1, 2, 2), listOf(5)),
        (intArrayOf(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1) to 27) to emptyList(),
        (intArrayOf(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1) to 30) to listOf(listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)),
    )

    input.forEach {
        val (candidates, target) = it.first
        it.testWithSet { Solution1().combinationSum2(candidates, target) }
    }

}