package com.random.problems.leetCode.minOperationsToConvertAllElementsToZero

import com.random.util.getResourceAsText


const val FILE_PATH = "/leetcode/minOperationsToConvertAllElementsToZero/TestCase955"


fun getBigInput(): Pair<IntArray, Int> {
    val lines = getResourceAsText(FILE_PATH)!!.lines()
    return lines[0].split(",").map { it.toInt() }.toIntArray() to 79998
}