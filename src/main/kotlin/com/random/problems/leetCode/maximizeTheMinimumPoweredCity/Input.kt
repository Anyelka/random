package com.random.problems.leetCode.maximizeTheMinimumPoweredCity

import com.random.util.getResourceAsText

const val FILE_PATH = "/leetcode/maximizeTheMinimumPoweredCity/TestCase26"

fun testCase26(): Pair<Triple<IntArray, Int, Int>, Long> {
    return Triple(getResourceAsText(FILE_PATH)!!.lines()[0].split(",").map { it.toInt() }.toIntArray(), 17939, 519080802) to 1154799883L
}