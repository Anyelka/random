package com.random.problems.leetCode.maximumNumberOfItemsFromSale

import com.random.util.test
import com.random.util.testWithMeasuredTime

fun main() {
    val input = listOf(
        (arrayOf(intArrayOf(6,2),intArrayOf(2,6),intArrayOf(3,4)) to 9) to 4,
        (arrayOf(intArrayOf(2,4),intArrayOf(3,2),intArrayOf(4,1),intArrayOf(6,4),intArrayOf(12,4)) to 8) to 10,
        (arrayOf(intArrayOf(1,1),intArrayOf(1,2)) to 3) to 4,
        (arrayOf(intArrayOf(44,1),intArrayOf(76,23),intArrayOf(17,1),intArrayOf(83,6),intArrayOf(1,16)) to 42) to 42

    )

    input.forEach {
        val (items, budget) = it.first
        it.testWithMeasuredTime { Solution1().maximumSaleItems(items, budget) }
    }
}