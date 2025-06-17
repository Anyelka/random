package com.random.problems.leetCode.mergeTripletsToFormTargetTriplet

import com.random.util.test

fun main() {
    val input = listOf(
        (arrayOf(intArrayOf(2,5,3),intArrayOf(1,8,4),intArrayOf(1,7,5)) to intArrayOf(2,7,5)) to true,
        (arrayOf(intArrayOf(3,4,5),intArrayOf(4,5,6)) to intArrayOf(3,2,5)) to false,
        (arrayOf(intArrayOf(2,5,3),intArrayOf(2,3,4),intArrayOf(1,2,5),intArrayOf(5,2,3)) to intArrayOf(5,5,5)) to true,
        (arrayOf(intArrayOf(1,2,5),intArrayOf(2,3,7)) to intArrayOf(1,2,7)) to false
    )

    input.forEach {
        val (triplets, target) = it.first
        it.test { Solution().mergeTriplets(triplets, target) }
    }
}