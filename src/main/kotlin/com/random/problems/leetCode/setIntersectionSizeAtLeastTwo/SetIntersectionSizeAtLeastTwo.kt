package com.random.problems.leetCode.setIntersectionSizeAtLeastTwo

import com.random.util.test

fun main() {
    val input =  listOf(
        arrayOf(intArrayOf(1,3),intArrayOf(3,7),intArrayOf(8,9)) to 5,
        arrayOf(intArrayOf(1,3),intArrayOf(1,4),intArrayOf(2,5),intArrayOf(3,5)) to 3,
        arrayOf(intArrayOf(1,2),intArrayOf(2,3),intArrayOf(2,4),intArrayOf(4,5)) to 5,
        arrayOf(intArrayOf(4,7), intArrayOf(2,4)) to 3,
        arrayOf(intArrayOf(1,7), intArrayOf(1,2)) to 2,
        arrayOf(intArrayOf(1,5),intArrayOf(2,5),intArrayOf(3,5),intArrayOf(8,9)) to 4,
        arrayOf(intArrayOf(1,3), intArrayOf(1,4), intArrayOf(7,9), intArrayOf(4,7)) to 5
    )

    input.forEach {
        val intervals = it.first
        it.test { Solution1().intersectionSizeTwo(intervals) }
    }
}