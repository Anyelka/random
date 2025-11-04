package com.random.problems.leetCode.findXSumOfAllKLongSubarrays

import com.random.util.test

fun main() {
    val input = listOf(
        Triple(intArrayOf(1,1,2,2,3,4,2,3),6, 2) to intArrayOf(6,10,12),
        Triple(intArrayOf(3,8,7,8,7,5), 2, 2) to intArrayOf(11,15,15,15,12)
    )

    input.forEach {
        val input = it.first
        it.test {
            Solution1().findXSum(input.first, input.second, input.third)
        }
    }
}

