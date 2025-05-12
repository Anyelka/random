package com.random.problems.leetCode.setMatrixZeroes

import com.random.util.formatArrayOfIntArrays
import com.random.util.isCorrectStringWithExpectedArrayOfIntArrays

fun main() {
    val input = listOf(
        arrayOf(intArrayOf(1,1,1),intArrayOf(1,0,1),intArrayOf(1,1,1)) to arrayOf(intArrayOf(1,0,1),intArrayOf(0,0,0),intArrayOf(1,0,1)),
        arrayOf(intArrayOf(0,1,2,0),intArrayOf(3,4,5,2),intArrayOf(1,3,1,5)) to arrayOf(intArrayOf(0,0,0,0),intArrayOf(0,4,5,0),intArrayOf(0,3,1,0)),
        arrayOf(intArrayOf(0,1,2,5),intArrayOf(3,4,0,2),intArrayOf(1,3,1,5)) to arrayOf(intArrayOf(0,0,0,0),intArrayOf(0,0,0,0),intArrayOf(0,3,0,5))
    )

    input.forEach {
        val input = it.first
        it.testInPlace { Solution1().setZeroes(input) }
    }
}

fun Pair<Array<IntArray>, Array<IntArray>>.testInPlace(method: (Array<IntArray>) -> Unit) {
    val input = first.toList().toTypedArray()
    method(first)
    println("Result for ${formatArrayOfIntArrays(input)} is: ${formatArrayOfIntArrays(first)} - ${isCorrectStringWithExpectedArrayOfIntArrays(first, second)}")
}