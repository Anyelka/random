package com.random.problems.leetCode.topKFrequentElements

import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(1,1,1,2,2,3) to 2) to intArrayOf(1,2),
        (intArrayOf(1) to 1) to intArrayOf(1),
        (intArrayOf(1, 3, 4, 2, 3, 4, 2, 1, 2, 1, 1) to 2) to intArrayOf(1, 2),
        (intArrayOf(1,2) to 2) to intArrayOf(1,2)
    )

    input.forEach {
        val (nums, k) = it.first
        it.test { Solution1().topKFrequent(nums, k) }
    }

}