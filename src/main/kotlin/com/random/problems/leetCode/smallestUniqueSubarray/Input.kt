package com.random.problems.leetCode.smallestUniqueSubarray

import com.random.util.getResourceAsText

const val INPUT_FILE_PATH = "/leetcode/smallestUniqueSubarray/TestCase0_in"
const val OUTPUT_FILE_PATH = "/leetcode/smallestUniqueSubarray/TestCase0_out"

fun getBigInput(): Pair<IntArray, Int> {
    return getInput() to getTarget()
}

private fun getInput() = getResourceAsText(INPUT_FILE_PATH)!!.trim().split(",").map { it.toInt() }.toIntArray()

private fun getTarget() = getResourceAsText(OUTPUT_FILE_PATH)!!.trim().toInt()
