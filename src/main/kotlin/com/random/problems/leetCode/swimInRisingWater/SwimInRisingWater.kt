package com.random.problems.leetCode.swimInRisingWater

import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf(intArrayOf(0,2),intArrayOf(1,3)) to 3,
        arrayOf(intArrayOf(0,1,2,3,4),intArrayOf(24,23,22,21,5),intArrayOf(12,13,14,15,16),intArrayOf(11,17,18,19,20),intArrayOf(10,9,8,7,6)) to 16,
        arrayOf(intArrayOf(0,1,2,3,4),intArrayOf(5,23,22,21,24),intArrayOf(12,13,14,15,16),intArrayOf(11,17,18,19,20),intArrayOf(10,9,8,7,6)) to 12
    )

    input.forEach {
        val grid = it.first
        it.test { Solution1().swimInWater(grid) }
    }

}