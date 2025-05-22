package com.random.problems.leetCode.spiralMatrix

import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf(intArrayOf(1,2,3),intArrayOf(4,5,6),intArrayOf(7,8,9)) to listOf(1,2,3,6,9,8,7,4,5),
        arrayOf(intArrayOf(1,2,3,4),intArrayOf(5,6,7,8),intArrayOf(9,10,11,12)) to listOf(1,2,3,4,8,12,11,10,9,5,6,7),
        arrayOf(intArrayOf(1)) to listOf(1),
        arrayOf(intArrayOf(3), intArrayOf(2)) to listOf(3,2)
    )

    input.forEach {
        val matrix = it.first
        it.test { Solution1().spiralOrder(matrix) }
    }
}