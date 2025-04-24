package com.random.problems.leetCode.mergeKSortedLists

import com.random.util.*

fun main() {

    val input = listOf(
        arrayOf(intArrayOf(1,4,5),intArrayOf(1,3,4),intArrayOf(2,6)) to intArrayOf(1,1,2,3,4,4,5,6),
        arrayOf<IntArray>() to intArrayOf(),
        arrayOf(intArrayOf()) to intArrayOf(),
    )

    val input2 = listOf(
        arrayOf(intArrayOf(1,4,5),intArrayOf(1,3,4)) to intArrayOf(1,1,3,4,4,5),
        arrayOf(intArrayOf(1,2,3,4,5),intArrayOf(1,3,3,4)) to intArrayOf(1,1,2,3,3,3,4,4,5)
    )

    input.forEach {
        val arrayOfIntArrays = it.first
        val arrays = arrayOfIntArrays.map { intArray -> convertIntArrayToListNode(intArray) }.toTypedArray()
        val result = convertListNodeToIntArray(Solution1().mergeKLists(arrays))
        println("Result for ${formatArrayOfIntArrays(arrayOfIntArrays)} is: ${shortFormatArrayIfNeeded(result)} - ${isCorrectStringWithExpected(result, it.second)}")
    }
}