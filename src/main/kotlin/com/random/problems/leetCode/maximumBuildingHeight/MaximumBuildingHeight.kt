package com.random.problems.leetCode.maximumBuildingHeight

import com.random.util.test

fun main() {
    val input = listOf(
        (5 to arrayOf(intArrayOf(2,1),intArrayOf(4,1))) to 2,
        (6 to emptyArray<IntArray>()) to 5,
        (10 to arrayOf(intArrayOf(5,3),intArrayOf(2,5),intArrayOf(7,4),intArrayOf(10,3))) to 5,
        (10 to arrayOf(intArrayOf(5,2),intArrayOf(2,5),intArrayOf(7,4),intArrayOf(10,3))) to 5,
        (8 to arrayOf(intArrayOf(5,0))) to 3,
        (10 to arrayOf(intArrayOf(8,5),intArrayOf(9,0),intArrayOf(6,2),intArrayOf(4,0),intArrayOf(3,2),intArrayOf(10,0),intArrayOf(5,3),intArrayOf(7,3),intArrayOf(2,4))) to 2,
        (10 to arrayOf(intArrayOf(6,0),intArrayOf(5,2),intArrayOf(7,0),intArrayOf(9,1),intArrayOf(2,4),intArrayOf(3,4),intArrayOf(4,0),intArrayOf(8,2),intArrayOf(10,0))) to 1,
        bigInput()
    )

    input.forEach {
        val (n, restrictions) = it.first
        it.test { Solution1().maxBuilding(n, restrictions) }
    }
}