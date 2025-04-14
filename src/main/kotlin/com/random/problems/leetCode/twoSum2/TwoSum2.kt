package com.random.problems.leetCode.twoSum2

import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(2,7,11,15) to 9) to intArrayOf(1,2),
        (intArrayOf(2,3,4) to 6) to intArrayOf(1,3),
        (intArrayOf(-1,0) to -1) to intArrayOf(1,2)
    )

    input.forEach {
        val (numbers, target) = it.first
        it.test { Solution1().twoSum(numbers, target) }
    }
}