package com.random.problems.leetCode.slidingWindowMaximum

import com.random.util.getResourceAsText

const val INPUT_FILE_PATH = "/leetcode/slidingWindowMaximum/TestCase0_in"
const val OUTPUT_FILE_PATH = "/leetcode/slidingWindowMaximum/TestCase0_out"

fun getBigInput(): Pair<Pair<IntArray, Int>,IntArray> {
    return (getInput() to 50000) to getOutput()
}

private fun getInput() = getResourceAsText(INPUT_FILE_PATH)!!.lines()[0].split(",").map { it.toInt() }.toIntArray()

private fun getOutput() = getResourceAsText(OUTPUT_FILE_PATH)!!.lines()[0].split(", ").map { it.toInt() }.toIntArray()