package com.random.problems.leetCode.insertInterval

import com.random.util.test

fun main() {
    val input = listOf(
        (arrayOf(intArrayOf(1,3), intArrayOf(6,9)) to intArrayOf(2,5)) to arrayOf(intArrayOf(1,5), intArrayOf(6,9)),
        (arrayOf(intArrayOf(1,2), intArrayOf(3,5),intArrayOf(6,7),intArrayOf(8,10),intArrayOf(12,16)) to intArrayOf(4,8)) to arrayOf(intArrayOf(1,2),intArrayOf(3,10),intArrayOf(12,16)),
        (arrayOf(intArrayOf(0,1)) to intArrayOf(1,2)) to arrayOf(intArrayOf(0,2)),
        (arrayOf(intArrayOf(0,1)) to intArrayOf(2,3)) to arrayOf(intArrayOf(0,1), intArrayOf(2,3)),
        (arrayOf(intArrayOf(0,1), intArrayOf(4,5)) to intArrayOf(2,3)) to arrayOf(intArrayOf(0,1), intArrayOf(2,3), intArrayOf(4,5))
    )

    input.forEach {
        val (intervals, newInterval) = it.first
        it.test { Solution1().insert(intervals, newInterval) }
    }
}