package com.random.problems.leetCode.threeSumWithMultiplicity

import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(1,1,2,2,3,3,4,4,5,5) to 8) to 20,
        (intArrayOf(1,1,2,2,2,2) to 5) to 12,
        (intArrayOf(2,1,3) to 6) to 1
    )

    input.forEach {
        val (arr, target) = it.first
        it.test { Solution1().threeSumMulti(arr, target) }
    }
}