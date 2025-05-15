package com.random.problems.leetCode.mergeKSortedLists

import com.random.util.*

fun main() {

    val input2 = listOf(
        arrayOf(intArrayOf(1,4,5),intArrayOf(1,3,4)) to intArrayOf(1,1,3,4,4,5),
        arrayOf(intArrayOf(1,2,3,4,5),intArrayOf(1,3,3,4)) to intArrayOf(1,1,2,3,3,3,4,4,5)
    )

    val input = listOf(
        arrayOf(intArrayOf(1,4,5),intArrayOf(1,3,4),intArrayOf(2,6)) to intArrayOf(1,1,2,3,4,4,5,6),
        arrayOf<IntArray>() to intArrayOf(),
        arrayOf(intArrayOf()) to intArrayOf(),
    ) + input2

    input.forEach {
        val arrayOfIntArrays = it.first
        it.test { convertListNodeToIntArray(Solution2().mergeKLists(convertArrayOfIntArraysToArrayOfListNodes(arrayOfIntArrays))) }
    }
}