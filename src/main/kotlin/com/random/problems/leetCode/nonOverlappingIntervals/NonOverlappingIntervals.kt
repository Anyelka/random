package com.random.problems.leetCode.nonOverlappingIntervals

import com.random.util.testArrayOfIntArrays

fun main() {
    val input = listOf(
        arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(1, 3)) to 1,
        arrayOf(intArrayOf(1, 2), intArrayOf(1, 2), intArrayOf(1, 2)) to 2,
        arrayOf(intArrayOf(1, 2), intArrayOf(2, 3)) to 0,
        arrayOf(intArrayOf(-52,31),intArrayOf(-73,-26),intArrayOf(82,97),intArrayOf(-65,-11),intArrayOf(-62,-49),intArrayOf(95,99),intArrayOf(58,95),intArrayOf(-31,49),intArrayOf(66,98),intArrayOf(-63,2),intArrayOf(30,47),intArrayOf(-40,-26)) to 7
    )

    input.forEach {
        val intervals = it.first
        it.testArrayOfIntArrays { Solution1().eraseOverlapIntervals(intervals) }
    }
}