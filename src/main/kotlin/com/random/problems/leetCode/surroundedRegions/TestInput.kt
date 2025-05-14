package com.random.problems.leetCode.surroundedRegions

import com.random.util.getResourceAsText

const val INPUT_FILE_PATH = "/leetcode/surroundedRegions/TestCase0_in"
const val OUTPUT_FILE_PATH = "/leetcode/surroundedRegions/TestCase0_out"

fun getBigInput(): Pair<Array<CharArray>, Array<CharArray>> = getInput() to getOutput()

private fun getInput() = getResourceAsText(INPUT_FILE_PATH)!!.lines()[0].split("],[")
    .map { charArray -> charArray.split(",").map { it.toCharArray()[0] }.toCharArray() }
    .toTypedArray()

private fun getOutput() = getResourceAsText(OUTPUT_FILE_PATH)!!.lines()[0].split("],[")
    .map { charArray -> charArray.split(",").map { it.toCharArray()[0] }.toCharArray() }
    .toTypedArray()